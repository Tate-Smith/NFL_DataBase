package com.Tate.NFL_db.Seeder;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Position;
import com.Tate.NFL_db.Model.Status;
import com.Tate.NFL_db.Model.Team;
import com.Tate.NFL_db.Repositories.PlayerRepository;
import com.Tate.NFL_db.Repositories.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Component
@Profile("seed")
public class PlayerSeeder implements CommandLineRunner {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final RestTemplate restTemplate;

    public PlayerSeeder(PlayerRepository playerRepository, TeamRepository teamRepository, RestTemplate restTemplate) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) {
        playerRepository.deleteAll();
        if (playerRepository.count() > 0) return;

        List<Team> teams = teamRepository.findAll();

        for (Team t : teams) {
            seed(t);
        }
    }

    public void seed(Team team) {

        String url = "https://site.api.espn.com/apis/site/v2/sports/football/nfl/teams/"
                + team.getExternalId()
                + "/roster";

        Map response = restTemplate.getForObject(url, Map.class);
        List<Map> athletes = (List<Map>) response.get("athletes");

        for (Map group : athletes) {
            List<Map> players = (List<Map>) group.get("items");

            for (Map player : players) {
                Player newPlayer = new Player();
                newPlayer.setTeam(team);
                newPlayer.setExternalId(player.get("id").toString());
                newPlayer.setFullName(player.get(("fullName")).toString());
                // get position and status in deeper nested loops
                newPlayer.setPosition(Position.valueOf(
                        ((Map) player.get("position")).get("abbreviation").toString())
                );
                if (((Map) player.get("status")).get("name").toString().equalsIgnoreCase("DAY-TO-DAY")) {
                    newPlayer.setStatus(Status.DTD);
                }
                else if (((Map) player.get("status")).get("name").toString().equalsIgnoreCase("PRACTICE SQUAD")) {
                    newPlayer.setStatus(Status.PRACTICE_SQUAD);
                }
                else {
                    newPlayer.setStatus(Status.valueOf(((Map) player.get("status")).get("name").toString().toUpperCase()));
                }
                playerRepository.save(newPlayer);
            }
        }
    }
}
