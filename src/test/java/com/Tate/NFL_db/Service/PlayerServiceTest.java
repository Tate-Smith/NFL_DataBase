package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Position;
import com.Tate.NFL_db.Model.Status;
import com.Tate.NFL_db.Repositories.PlayerRepository;
import com.Tate.NFL_db.dto.PlayerDTO;
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
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player();
        testPlayer.setExternalId("ESPN-001");
        testPlayer.setFullName("Patrick Mahomes");
        testPlayer.setPosition(Position.QB);
        testPlayer.setStatus(Status.ACTIVE);
        testPlayer.setNumber("15");
        testPlayer.setTeamName("Chiefs");
    }

    // --- getAllPlayers ---

    @Test
    void getAllPlayers_returnsAllPlayersAsDTOs() {
        when(playerRepository.findAll()).thenReturn(List.of(testPlayer));

        List<PlayerDTO> result = playerService.getAllPlayers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFullName()).isEqualTo("Patrick Mahomes");
        assertThat(result.get(0).getExternalId()).isEqualTo("ESPN-001");
    }

    @Test
    void getAllPlayers_whenEmpty_returnsEmptyList() {
        when(playerRepository.findAll()).thenReturn(List.of());

        List<PlayerDTO> result = playerService.getAllPlayers();

        assertThat(result).isEmpty();
    }

    // --- getPlayerById ---

    @Test
    void getPlayerById_whenFound_returnsDTO() {
        when(playerRepository.findByExternalId("ESPN-001")).thenReturn(Optional.of(testPlayer));

        PlayerDTO result = playerService.getPlayerById("ESPN-001");

        assertThat(result.getFullName()).isEqualTo("Patrick Mahomes");
        assertThat(result.getPosition()).isEqualTo(Position.QB);
    }

    @Test
    void getPlayerById_whenNotFound_throwsEntityNotFoundException() {
        when(playerRepository.findByExternalId("FAKE-ID")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.getPlayerById("FAKE-ID"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("FAKE-ID");
    }

    // --- createPlayer ---

    @Test
    void createPlayer_savesAndReturnsDTO() {
        PlayerDTO dto = new PlayerDTO();
        dto.setExternalId("ESPN-002");
        dto.setFullName("Travis Kelce");
        dto.setPosition(Position.TE);
        dto.setStatus(Status.ACTIVE);
        dto.setNumber("87");
        dto.setTeamName("Chiefs");

        // when save is called with any Player, return testPlayer
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);

        PlayerDTO result = playerService.createPlayer(dto);

        assertThat(result).isNotNull();
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    // --- updatePlayer ---

    @Test
    void updatePlayer_whenFound_updatesAndReturnsDTO() {
        PlayerDTO updateDTO = new PlayerDTO();
        updateDTO.setExternalId("ESPN-001");
        updateDTO.setFullName("Patrick Mahomes II");
        updateDTO.setPosition(Position.QB);
        updateDTO.setStatus(Status.QUESTIONABLE);
        updateDTO.setNumber("15");
        updateDTO.setTeamName("Chiefs");

        when(playerRepository.findByExternalId("ESPN-001")).thenReturn(Optional.of(testPlayer));
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);

        PlayerDTO result = playerService.updatePlayer("ESPN-001", updateDTO);

        assertThat(result).isNotNull();
        verify(playerRepository).save(testPlayer);
    }

    @Test
    void updatePlayer_whenNotFound_throwsEntityNotFoundException() {
        PlayerDTO updateDTO = new PlayerDTO();

        when(playerRepository.findByExternalId("FAKE-ID")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.updatePlayer("FAKE-ID", updateDTO))
                .isInstanceOf(EntityNotFoundException.class);
    }

    // --- deletePlayer ---

    @Test
    void deletePlayer_whenFound_deletesAndReturnsMessage() {
        when(playerRepository.findByExternalId("ESPN-001")).thenReturn(Optional.of(testPlayer));

        String result = playerService.deletePlayer("ESPN-001");

        assertThat(result).contains("ESPN-001");
        verify(playerRepository, times(1)).delete(testPlayer);
    }

    @Test
    void deletePlayer_whenNotFound_throwsEntityNotFoundException() {
        when(playerRepository.findByExternalId("FAKE-ID")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.deletePlayer("FAKE-ID"))
                .isInstanceOf(EntityNotFoundException.class);

        verify(playerRepository, never()).delete(any());
    }
}