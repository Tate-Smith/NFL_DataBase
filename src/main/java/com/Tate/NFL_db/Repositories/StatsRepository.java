package com.Tate.NFL_db.Repositories;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface StatsRepository extends JpaRepository<Stats, Integer> {
    Optional<Stats> findByPlayerAndSeason(Player player, Year season);
    List<Stats> findByPlayer(Player player);
}
