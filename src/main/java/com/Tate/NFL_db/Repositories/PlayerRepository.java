package com.Tate.NFL_db.Repositories;

import com.Tate.NFL_db.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByExternalId(String externalId);
}
