package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Position;
import com.Tate.NFL_db.Model.Status;
import com.Tate.NFL_db.Model.Team;
import com.Tate.NFL_db.Repositories.TeamRepository;
import com.Tate.NFL_db.dto.PlayerDTO;
import com.Tate.NFL_db.dto.TeamDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team testTeam;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        testTeam = new Team();
        testTeam.setExternalId("KC");
        testTeam.setName("Chiefs");
        testTeam.setCity("Kansas City");
        testTeam.setAbbreviation("KC");

        testPlayer = new Player();
        testPlayer.setExternalId("ESPN-001");
        testPlayer.setFullName("Patrick Mahomes");
        testPlayer.setPosition(Position.QB);
        testPlayer.setStatus(Status.ACTIVE);
        testPlayer.setNumber("15");
        testPlayer.setTeamName("Chiefs");
        testPlayer.setTeam(testTeam);
    }

    // --- getAllTeams ---

    @Test
    void getAllTeams_returnsAllTeamsAsDTOs() {
        when(teamRepository.findAll()).thenReturn(List.of(testTeam));

        List<TeamDTO> result = teamService.getAllTeams();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Chiefs");
        assertThat(result.get(0).getExternalId()).isEqualTo("KC");
    }

    @Test
    void getAllTeams_whenEmpty_returnsEmptyList() {
        when(teamRepository.findAll()).thenReturn(List.of());

        List<TeamDTO> result = teamService.getAllTeams();

        assertThat(result).isEmpty();
    }

    // --- getTeamById ---

    @Test
    void getTeamById_whenFound_returnsDTO() {
        when(teamRepository.findByExternalId("KC")).thenReturn(Optional.of(testTeam));

        TeamDTO result = teamService.getTeamById("KC");

        assertThat(result.getName()).isEqualTo("Chiefs");
        assertThat(result.getCity()).isEqualTo("Kansas City");
        assertThat(result.getAbbreviation()).isEqualTo("KC");
    }

    @Test
    void getTeamById_whenNotFound_throwsEntityNotFoundException() {
        when(teamRepository.findByExternalId("FAKE")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.getTeamById("FAKE"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("FAKE");
    }

    // --- getTeamsPlayers ---

    @Test
    void getTeamsPlayers_whenFound_returnsPlayerDTOs() {
        testTeam.getPlayers().add(testPlayer);
        when(teamRepository.findByExternalId("KC")).thenReturn(Optional.of(testTeam));

        List<PlayerDTO> result = teamService.getTeamsPlayers("KC");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFullName()).isEqualTo("Patrick Mahomes");
        assertThat(result.get(0).getPosition()).isEqualTo(Position.QB);
    }

    @Test
    void getTeamsPlayers_whenTeamHasNoPlayers_returnsEmptyList() {
        when(teamRepository.findByExternalId("KC")).thenReturn(Optional.of(testTeam));

        List<PlayerDTO> result = teamService.getTeamsPlayers("KC");

        assertThat(result).isEmpty();
    }

    @Test
    void getTeamsPlayers_whenTeamNotFound_throwsEntityNotFoundException() {
        when(teamRepository.findByExternalId("FAKE")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.getTeamsPlayers("FAKE"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("FAKE");
    }

    // --- createTeam ---

    @Test
    void createTeam_savesAndReturnsDTO() {
        TeamDTO dto = new TeamDTO();
        dto.setExternalId("SF");
        dto.setName("49ers");
        dto.setCity("San Francisco");
        dto.setAbbreviation("SF");

        when(teamRepository.save(any(Team.class))).thenReturn(testTeam);

        TeamDTO result = teamService.createTeam(dto);

        assertThat(result).isNotNull();
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    // --- updateTeam ---

    @Test
    void updateTeam_whenFound_updatesAndReturnsDTO() {
        TeamDTO updateDTO = new TeamDTO();
        updateDTO.setExternalId("KC");
        updateDTO.setName("Chiefs");
        updateDTO.setCity("Kansas City");
        updateDTO.setAbbreviation("KC");

        when(teamRepository.findByExternalId("KC")).thenReturn(Optional.of(testTeam));
        when(teamRepository.save(any(Team.class))).thenReturn(testTeam);

        TeamDTO result = teamService.updateTeam("KC", updateDTO);

        assertThat(result).isNotNull();
        verify(teamRepository).save(testTeam);
    }

    @Test
    void updateTeam_whenNotFound_throwsEntityNotFoundException() {
        TeamDTO updateDTO = new TeamDTO();

        when(teamRepository.findByExternalId("FAKE")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.updateTeam("FAKE", updateDTO))
                .isInstanceOf(EntityNotFoundException.class);
    }

    // --- deleteTeam ---

    @Test
    void deleteTeam_whenFound_deletesAndReturnsMessage() {
        when(teamRepository.findByExternalId("KC")).thenReturn(Optional.of(testTeam));

        String result = teamService.deleteTeam("KC");

        assertThat(result).contains("KC");
        verify(teamRepository, times(1)).delete(testTeam);
    }

    @Test
    void deleteTeam_whenNotFound_throwsEntityNotFoundException() {
        when(teamRepository.findByExternalId("FAKE")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.deleteTeam("FAKE"))
                .isInstanceOf(EntityNotFoundException.class);

        verify(teamRepository, never()).delete(any());
    }
}