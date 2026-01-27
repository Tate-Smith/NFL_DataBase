package com.Tate.NFL_db.Controller;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Service.PlayerService;
import com.Tate.NFL_db.dto.PlayerDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDTO> getPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable String externalId) {
        return playerService.getPlayerById(externalId);
    }

    @PostMapping
    public PlayerDTO createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return playerService.createPlayer(playerDTO);
    }

    @PutMapping("/{id}")
    public PlayerDTO updatePlayer(@PathVariable String externalId, @Valid @RequestBody PlayerDTO playerDTO) {
        return playerService.updatePlayer(externalId, playerDTO);
    }

    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable String externalId) {
        return playerService.deletePlayer(externalId);
    }
}
