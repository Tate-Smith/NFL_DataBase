package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.PlayerRepository;
import com.Tate.NFL_db.dto.Mapping;
import com.Tate.NFL_db.dto.PlayerDTO;
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

    public PlayerDTO getPlayerById(int id) {
        return Mapping.playerToDto(playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player Not Found")));
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        return Mapping.playerToDto(playerRepository.save(Mapping.dtoToPlayer(playerDTO)));
    }

    public PlayerDTO updatePlayer(int id, PlayerDTO playerDTO) {
        Player cur = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player Not Found"));

        cur.setFullName(playerDTO.getFullName());
        cur.setStatus(playerDTO.getStatus());
        cur.setPosition(playerDTO.getPosition());
        cur.setExternalId(playerDTO.getExternalId());
        cur.setUpdatedAt(LocalDateTime.now());

        return Mapping.playerToDto(playerRepository.save(cur));
    }

    public String deletePlayer(int id) {
        playerRepository.deleteById(id);
        return "Player with id: " + id + " deleted.";
    }
}
