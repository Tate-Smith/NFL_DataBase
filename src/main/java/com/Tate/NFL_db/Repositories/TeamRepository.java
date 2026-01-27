package com.Tate.NFL_db.Repositories;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findByExternalId(String externalId);
}
