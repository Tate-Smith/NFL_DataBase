/*
This complex class's role is to fill in the entire database with player info
from the ESPN API
 */

package com.Tate.NFL_db.Seeder;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Position;
import com.Tate.NFL_db.Model.Status;
import com.Tate.NFL_db.Model.Team;
import com.Tate.NFL_db.Repositories.PlayerRepository;
import com.Tate.NFL_db.Repositories.TeamRepository;
import jakarta.transaction.Transactional;
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
        // if the table already contains data then this doesn't need to run
        if (playerRepository.count() > 0) return;

        List<Team> teams = teamRepository.findAll();

        // for every team in the eam repo add its players
        for (Team t : teams) {
            seed(t);
        }
    }

    @Transactional
    public void seed(Team team) {

        // go to a particular teams link on the ESPN API
        String url = "https://site.api.espn.com/apis/site/v2/sports/football/nfl/teams/"
                + team.getExternalId()
                + "/roster";

        // get the section with the info that is required
        Map response = restTemplate.getForObject(url, Map.class);
        List<Map> athletes = (List<Map>) response.get("athletes");

        for (Map group : athletes) {
            List<Map> players = (List<Map>) group.get("items");

            for (Map player : players) {
                // then create a new Player object and fill out its info
                Player newPlayer = new Player();
                newPlayer.setTeam(team);
                newPlayer.setExternalId(player.get("id").toString());
                newPlayer.setFullName(player.get(("fullName")).toString());
                Object jersey = player.get("jersey");
                newPlayer.setNumber((jersey != null ? jersey.toString() : "0"));
                newPlayer.setTeamName(team.getName());
                // get position and status in deeper nested loops
                newPlayer.setPosition(Position.valueOf(
                        ((Map) player.get("position")).get("abbreviation").toString())
                );
                // set the correct status
                if (((Map) player.get("status")).get("name").toString().equalsIgnoreCase("DAY-TO-DAY")) {
                    newPlayer.setStatus(Status.DTD);
                }
                else if (((Map) player.get("status")).get("name").toString().equalsIgnoreCase("PRACTICE SQUAD")) {
                    newPlayer.setStatus(Status.PRACTICE_SQUAD);
                }
                else {
                    newPlayer.setStatus(Status.valueOf(((Map) player.get("status")).get("name").toString().toUpperCase()));
                }
                // add the player to the data
                playerRepository.save(newPlayer);
            }
        }
    }
}
