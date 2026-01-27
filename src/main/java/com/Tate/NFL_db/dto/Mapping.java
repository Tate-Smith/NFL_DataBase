package com.Tate.NFL_db.dto;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Stats;
import com.Tate.NFL_db.Model.Team;

public class Mapping {
    public static PlayerDTO playerToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setStatus(player.getStatus());
        playerDTO.setExternalId(player.getExternalId());
        playerDTO.setFullName(player.getFullName());
        Team playerTeam = player.getTeam();
        if (playerTeam != null) playerDTO.setTeamExternalId(playerTeam.getExternalId());
        else playerDTO.setTeamExternalId(null);
        return playerDTO;
    }

    public static Player dtoToPlayer(PlayerDTO dto) {
        Player player = new Player();
        player.setExternalId(dto.getExternalId());
        player.setStatus(dto.getStatus());
        player.setPosition(dto.getPosition());
        player.setFullName(dto.getFullName());
        return player;
    }

    public static TeamDTO teamToDto(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setCity(team.getCity());
        teamDTO.setExternalId(team.getExternalId());
        teamDTO.setAbbreviation(team.getAbbreviation());
        return teamDTO;
    }

    public static Team dtoToTeam(TeamDTO dto) {
        Team team = new Team();
        team.setExternalId(dto.getExternalId());
        team.setName(dto.getName());
        team.setCity(dto.getCity());
        team.setAbbreviation(dto.getAbbreviation());
        return team;
    }

    public static StatsDTO statsToDto(Stats stats) {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setId(stats.getId());
        statsDTO.setPlayerExternalId(stats.getPlayer().getExternalId());
        statsDTO.setCompletions(stats.getCompletions());
        statsDTO.setCompletionPercentage(stats.getCompletionPercentage());
        statsDTO.setFieldGoalAttempts(stats.getFieldGoalAttempts());
        statsDTO.setFieldGoalsMade(stats.getFieldGoalsMade());
        statsDTO.setForcedFumbles(stats.getForcedFumbles());
        statsDTO.setHurries(stats.getHurries());
        statsDTO.setFumbleRecoveries(stats.getFumbleRecoveries());
        statsDTO.setGamesPlayed(stats.getGamesPlayed());
        statsDTO.setInterceptions(stats.getInterceptions());
        statsDTO.setKickoffReturnAverage(stats.getKickoffReturnAverage());
        statsDTO.setPassDeflections(stats.getPassDeflections());
        statsDTO.setYardsPerRush(stats.getYardsPerRush());
        statsDTO.setPassingAttempts(stats.getPassingAttempts());
        statsDTO.setPassingTouchdowns(stats.getPassingTouchdowns());
        statsDTO.setPassingYards(stats.getPassingYards());
        statsDTO.setPuntAverage(stats.getPuntAverage());
        statsDTO.setPuntReturnAverage(stats.getPuntReturnAverage());
        statsDTO.setQbr(stats.getQbr());
        statsDTO.setQuarterbackHits(stats.getQuarterbackHits());
        statsDTO.setReceivingTouchdowns(stats.getReceivingTouchdowns());
        statsDTO.setReceivingYards(stats.getReceivingYards());
        statsDTO.setReceptions(stats.getReceptions());
        statsDTO.setReturnTouchdowns(stats.getReturnTouchdowns());
        statsDTO.setRushingAttempts(stats.getRushingAttempts());
        statsDTO.setRushingTouchdowns(stats.getRushingTouchdowns());
        statsDTO.setRushingYards(stats.getRushingYards());
        statsDTO.setSacks(stats.getSacks());
        statsDTO.setSeason(stats.getSeason());
        statsDTO.setSoloTackles(stats.getSoloTackles());
        statsDTO.setTacklesForLoss(stats.getTacklesForLoss());
        statsDTO.setTargets(stats.getTargets());
        statsDTO.setTotalTackles(stats.getTotalTackles());
        return statsDTO;
    }

    public static Stats dtoToStats(StatsDTO dto) {
        Stats stats = new Stats();
        stats.setCompletions(dto.getCompletions());
        stats.setCompletionPercentage(dto.getCompletionPercentage());
        stats.setFieldGoalAttempts(dto.getFieldGoalAttempts());
        stats.setFieldGoalsMade(dto.getFieldGoalsMade());
        stats.setForcedFumbles(dto.getForcedFumbles());
        stats.setHurries(dto.getHurries());
        stats.setFumbleRecoveries(dto.getFumbleRecoveries());
        stats.setGamesPlayed(dto.getGamesPlayed());
        stats.setInterceptions(dto.getInterceptions());
        stats.setKickoffReturnAverage(dto.getKickoffReturnAverage());
        stats.setPassDeflections(dto.getPassDeflections());
        stats.setYardsPerRush(dto.getYardsPerRush());
        stats.setPassingAttempts(dto.getPassingAttempts());
        stats.setPassingTouchdowns(dto.getPassingTouchdowns());
        stats.setPassingYards(dto.getPassingYards());
        stats.setPuntAverage(dto.getPuntAverage());
        stats.setPuntReturnAverage(dto.getPuntReturnAverage());
        stats.setQbr(dto.getQbr());
        stats.setQuarterbackHits(dto.getQuarterbackHits());
        stats.setReceivingTouchdowns(dto.getReceivingTouchdowns());
        stats.setReceivingYards(dto.getReceivingYards());
        stats.setReceptions(dto.getReceptions());
        stats.setReturnTouchdowns(dto.getReturnTouchdowns());
        stats.setRushingAttempts(dto.getRushingAttempts());
        stats.setRushingTouchdowns(dto.getRushingTouchdowns());
        stats.setRushingYards(dto.getRushingYards());
        stats.setSacks(dto.getSacks());
        stats.setSeason(dto.getSeason());
        stats.setSoloTackles(dto.getSoloTackles());
        stats.setTacklesForLoss(dto.getTacklesForLoss());
        stats.setTargets(dto.getTargets());
        stats.setTotalTackles(dto.getTotalTackles());
        return stats;
    }
}
