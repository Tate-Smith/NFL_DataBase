package com.Tate.NFL_db.Controller;

import com.Tate.NFL_db.Service.TeamService;
import com.Tate.NFL_db.dto.PlayerDTO;
import com.Tate.NFL_db.dto.TeamDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{externalId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable String externalId) {
        return ResponseEntity.ok(teamService.getTeamById(externalId));
    }

    @GetMapping("/{externalId}/players")
    public ResponseEntity<List<PlayerDTO>> getTeamsPlayers(@PathVariable String externalId) {
        return ResponseEntity.ok(teamService.getTeamsPlayers(externalId));
    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teamService.createTeam(teamDTO));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable String externalId, @Valid @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.updateTeam(externalId, teamDTO));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<String> deleteTeam(@PathVariable String externalId) {
        return ResponseEntity.ok(teamService.deleteTeam(externalId));
    }
}
