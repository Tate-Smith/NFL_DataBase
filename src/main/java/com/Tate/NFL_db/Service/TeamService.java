package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Team;
import com.Tate.NFL_db.Repositories.TeamRepository;
import com.Tate.NFL_db.dto.Mapping;
import com.Tate.NFL_db.dto.PlayerDTO;
import com.Tate.NFL_db.dto.TeamDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(Mapping::teamToDto)
                .collect(Collectors.toList());
    }

    public TeamDTO getTeamById(String externalId) {
        return Mapping.teamToDto(teamRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Team with external id: " + externalId + " not found")));
    }

    public List<PlayerDTO> getTeamsPlayers(String externalId) {
        Team cur = teamRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Team with external id: " + externalId + " not found"));

        return cur.getPlayers().stream()
                .map(Mapping::playerToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamDTO createTeam(TeamDTO teamDTO) {
        return Mapping.teamToDto(teamRepository.save(Mapping.dtoToTeam(teamDTO)));
    }

    @Transactional
    public TeamDTO updateTeam(String externalId, TeamDTO teamDTO) {
        Team cur = teamRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Team with external id: " + externalId + " not found"));

        cur.setName(teamDTO.getName());
        cur.setCity(teamDTO.getCity());
        cur.setAbbreviation(teamDTO.getAbbreviation());
        cur.setExternalId(teamDTO.getExternalId());
        cur.setUpdatedAt(LocalDateTime.now());

        return Mapping.teamToDto(teamRepository.save(cur));
    }

    @Transactional
    public String deleteTeam(String externalId) {
        Team team = teamRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Team with external id: " + externalId + " not found"));

        teamRepository.delete(team);
        return "Team with externalId: " + externalId + " deleted.";
    }
}