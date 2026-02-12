/*
This mapping class's job is to convert DTOs to normal objects
and vice versa it is used by the service classes to protect
internal data
 */

package com.Tate.NFL_db.dto;

import com.Tate.NFL_db.Model.Player;
import com.Tate.NFL_db.Model.Team;

public final class Mapping {
    public static PlayerDTO playerToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setStatus(player.getStatus());
        playerDTO.setExternalId(player.getExternalId());
        playerDTO.setFullName(player.getFullName());
        playerDTO.setTeamName(player.getTeamName());
        playerDTO.setNumber(player.getNumber());
        Team playerTeam = player.getTeam();
        // if the player doesn't have a team yet set it
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
        player.setTeamName(dto.getTeamName());
        player.setNumber(dto.getNumber());
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
}