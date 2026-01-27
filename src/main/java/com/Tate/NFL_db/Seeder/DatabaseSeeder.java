package com.Tate.NFL_db.Seeder;


import com.Tate.NFL_db.Service.PlayerService;
import com.Tate.NFL_db.Service.StatsService;
import com.Tate.NFL_db.Service.TeamService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("seed")
public class DatabaseSeeder {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final StatsService statsService;

    public DatabaseSeeder(TeamService teamService, PlayerService playerService, StatsService statsService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.statsService = statsService;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedTeams();
        seedPlayers();
        seedStats();
    }
}
