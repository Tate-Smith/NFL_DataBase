package com.Tate.NFL_db.Controller;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public Player createPlayer(@Valid @RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @Valid @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
    }
}
