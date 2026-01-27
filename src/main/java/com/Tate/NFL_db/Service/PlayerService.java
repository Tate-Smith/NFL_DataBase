package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Stats;
import com.Tate.NFL_db.Repositories.PlayerRepository;
import com.Tate.NFL_db.dto.Mapping;
import com.Tate.NFL_db.dto.PlayerDTO;
import com.Tate.NFL_db.dto.StatsDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(Mapping::playerToDto)
                .collect(Collectors.toList());
    }

    public PlayerDTO getPlayerById(String externalId) {
        return Mapping.playerToDto(playerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Player Not Found")));
    }

    public List<StatsDTO> getPlayersStats(String externalId) {
        Player cur = playerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Player Not Found"));

        return cur.getStats().stream()
                .map(Mapping::statsToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        return Mapping.playerToDto(playerRepository.save(Mapping.dtoToPlayer(playerDTO)));
    }

    @Transactional
    public PlayerDTO updatePlayer(String externalId, PlayerDTO playerDTO) {
        Player cur = playerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Player Not Found"));

        cur.setFullName(playerDTO.getFullName());
        cur.setStatus(playerDTO.getStatus());
        cur.setPosition(playerDTO.getPosition());
        cur.setExternalId(playerDTO.getExternalId());
        cur.setUpdatedAt(LocalDateTime.now());

        return Mapping.playerToDto(playerRepository.save(cur));
    }

    @Transactional
    public String deletePlayer(String externalId) {
        Player player = playerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Player Not Found"));

        playerRepository.delete(player);
        return "Player: " + externalId + " deleted.";
    }
}
