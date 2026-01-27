package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Stats;
import com.Tate.NFL_db.Repositories.PlayerRepository;
import com.Tate.NFL_db.Repositories.StatsRepository;
import com.Tate.NFL_db.dto.Mapping;
import com.Tate.NFL_db.dto.StatsDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {
    private final StatsRepository statsRepository;
    private final PlayerRepository playerRepository;

    public StatsService(StatsRepository statsRepository, PlayerRepository playerRepository) {
        this.statsRepository = statsRepository;
        this.playerRepository = playerRepository;
    }

    public StatsDTO getStatsById(int id) {
        return Mapping.statsToDto(statsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stats with id: " + id + " not found")));
    }

    public StatsDTO getPlayersStatsBySeason(String externalId, Year season) {
        Player player = playerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Player with external id: " + externalId + " not found"));

        return Mapping.statsToDto(statsRepository.findByPlayerAndSeason(player, season)
                .orElseThrow(() -> new EntityNotFoundException("Stats with player external id: " + externalId + " not found")));
    }

    public List<StatsDTO> getAllPlayersStats(String externalId) {
        Player player = playerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Player with external id: " + externalId + " not found"));

        return statsRepository.findByPlayer(player).stream()
                .map(Mapping::statsToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public StatsDTO createStats(StatsDTO statsDTO) {
        Player player = playerRepository.findByExternalId(statsDTO.getPlayerExternalId())
                .orElseThrow(() -> new EntityNotFoundException("Player with external id: " + statsDTO.getPlayerExternalId() + " not found"));

        Stats stats = Mapping.dtoToStats(statsDTO);
        stats.setPlayer(player);
        return Mapping.statsToDto((statsRepository.save(stats)));
    }

    @Transactional
    public StatsDTO updateStats(int id, StatsDTO dto) {
        Stats stats = statsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stats with id: " + id + " not found"));

        stats.setCompletions(dto.getCompletions());
        stats.setCompletionPercentage(dto.getCompletionPercentage());
        stats.setFieldGoalAttempts(dto.getFieldGoalAttempts());
        stats.setFieldGoalsMade(dto.getFieldGoalsMade());
        stats.setForcedFumbles(dto.getForcedFumbles());
        stats.setHurries(dto.getHurries());
        stats.setFumbleRecoveries(dto.getFumbleRecoveries());
        stats.setGamesPlayed(dto.getGamesPlayed());
        stats.setInterceptions(dto.getInterceptions());
        stats.setKickoffReturnAverage(dto.getKickoffReturnAverage());
        stats.setPassDeflections(dto.getPassDeflections());
        stats.setYardsPerRush(dto.getYardsPerRush());
        stats.setPassingAttempts(dto.getPassingAttempts());
        stats.setPassingTouchdowns(dto.getPassingTouchdowns());
        stats.setPassingYards(dto.getPassingYards());
        stats.setPuntAverage(dto.getPuntAverage());
        stats.setPuntReturnAverage(dto.getPuntReturnAverage());
        stats.setQbr(dto.getQbr());
        stats.setQuarterbackHits(dto.getQuarterbackHits());
        stats.setReceivingTouchdowns(dto.getReceivingTouchdowns());
        stats.setReceivingYards(dto.getReceivingYards());
        stats.setReceptions(dto.getReceptions());
        stats.setReturnTouchdowns(dto.getReturnTouchdowns());
        stats.setRushingAttempts(dto.getRushingAttempts());
        stats.setRushingTouchdowns(dto.getRushingTouchdowns());
        stats.setRushingYards(dto.getRushingYards());
        stats.setSacks(dto.getSacks());
        stats.setSeason(dto.getSeason());
        stats.setSoloTackles(dto.getSoloTackles());
        stats.setTacklesForLoss(dto.getTacklesForLoss());
        stats.setTargets(dto.getTargets());
        stats.setTotalTackles(dto.getTotalTackles());
        stats.setUpdatedAt(LocalDateTime.now());

        return Mapping.statsToDto(statsRepository.save(stats));
    }

    @Transactional
    public void deleteStats(int id) {
        if (!statsRepository.existsById(id)) {
            throw new EntityNotFoundException("Stats with id: " + id + " not found");
        }
        statsRepository.deleteById(id);
    }
}
