/*
This complex class's role is to fill in the entire database with team info
from the ESPN API
 */

package com.Tate.NFL_db.Seeder;

import com.Tate.NFL_db.Model.Team;
import com.Tate.NFL_db.Repositories.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@Profile("seed")
public class TeamSeeder implements CommandLineRunner {

    private final RestTemplate restClient;
    private final TeamRepository teamRepository;

    public TeamSeeder(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.restClient = new RestTemplate();
    }

    @Override
    public void run(String... args) {
        // if there are already teams then this doesn't need to run
        if (teamRepository.count() > 0) return;

        // otherwise go to the ESPN API for teams
        String url = "https://site.api.espn.com/apis/site/v2/sports/football/nfl/teams";
        Map response = restClient.getForObject(url, Map.class);

        List<Map> teams = (List<Map>) ((Map)
                ((List<?>) response.get("sports")).get(0))
                .get("leagues");

        List<Map> teamList = (List<Map>) teams.get(0).get("teams");

        for (Map t : teamList) {
            Map team = (Map) t.get("team");

            // create a new team object and fill out its fields
            Team newTeam = new Team(
              team.get("id").toString(),
              team.get("name").toString(),
              team.get("location").toString(),
              team.get("abbreviation").toString()
            );

            // add the team to the database
            teamRepository.save(newTeam);
        }
    }
}
