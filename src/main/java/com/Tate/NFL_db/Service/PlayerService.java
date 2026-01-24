package com.Tate.NFL_db.Service;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.PlayerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player Not Found"));
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(int id, Player player) {
        Player cur = getPlayerById(id);

        cur.setFullName(player.getFullName());
        cur.setStatus(player.getStatus());
        cur.setPosition(player.getPosition());
        cur.setUpdatedAt(LocalDateTime.now());

        return playerRepository.save(cur);
    }

    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }
}
