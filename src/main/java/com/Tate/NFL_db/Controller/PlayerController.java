package com.Tate.NFL_db.Controller;

import com.Tate.NFL_db.Service.PlayerService;
import com.Tate.NFL_db.dto.PlayerDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{externalId}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable String externalId) {
        return ResponseEntity.ok(playerService.getPlayerById(externalId));
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerService.createPlayer(playerDTO));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable String externalId, @Valid @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.updatePlayer(externalId, playerDTO));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<String> deletePlayer(@PathVariable String externalId) {
        return ResponseEntity.ok(playerService.deletePlayer(externalId));
    }
}
