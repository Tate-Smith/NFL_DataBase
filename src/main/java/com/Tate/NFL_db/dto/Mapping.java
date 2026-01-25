package com.Tate.NFL_db.dto;

import com.Tate.NFL_db.Model.Player;

public class Mapping {
    public static PlayerDTO playerToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPosition(player.getPosition());
        playerDTO.setStatus(player.getStatus());
        playerDTO.setExternalId(player.getExternalId());
        playerDTO.setFullName(player.getFullName());
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
}
