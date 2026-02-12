/*
This is the service that is the in between of the Player controller
and the database, it takes the requests from the client, turns the DTOs
to real database objects with the mapping class then does whatever the
client has requested (GET, POST, UPDATE, DELETE)
 */

package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Repositories.PlayerRepository;
import com.Tate.NFL_db.dto.Mapping;
import com.Tate.NFL_db.dto.PlayerDTO;
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
                .orElseThrow(() -> new EntityNotFoundException("Player with external id: " + externalId + " not found")));
    }

    @Transactional
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        return Mapping.playerToDto(playerRepository.save(Mapping.dtoToPlayer(playerDTO)));
    }

    @Transactional
    public PlayerDTO updatePlayer(String externalId, PlayerDTO playerDTO) {
        Player cur = playerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Player with external id: " + externalId + " not found"));

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
                .orElseThrow(() -> new EntityNotFoundException("Player with external id: " + externalId + " not found"));

        playerRepository.delete(player);
        return "Player with externalId: " + externalId + " deleted.";
    }
}