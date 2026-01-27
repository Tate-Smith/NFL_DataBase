package com.Tate.NFL_db.Controller;

import com.Tate.NFL_db.Service.StatsService;
import com.Tate.NFL_db.dto.StatsDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatsDTO> getTeamById(@PathVariable int id) {
        return ResponseEntity.ok(statsService.getStatsById(id));
    }

    @GetMapping("/player/{externalId}")
    public ResponseEntity<List<StatsDTO>> getAllPlayersStats(@PathVariable String externalId) {
        return ResponseEntity.ok(statsService.getAllPlayersStats(externalId));
    }

    @GetMapping("/player/{externalId}/season/{season}")
    public ResponseEntity<StatsDTO> getPlayersStatsBySeason(@PathVariable String externalId, @PathVariable int season) {
        return ResponseEntity.ok(statsService.getPlayersStatsBySeason(externalId, Year.of(season)));
    }

    @PostMapping
    public ResponseEntity<StatsDTO> createStats(@Valid @RequestBody StatsDTO statsDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statsService.createStats(statsDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatsDTO> updateStats(@PathVariable int id, @Valid @RequestBody StatsDTO statsDTO) {
        return ResponseEntity.ok(statsService.updateStats(id, statsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int id) {
        statsService.deleteStats(id);
        return ResponseEntity.noContent().build();
    }
}