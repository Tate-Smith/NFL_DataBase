package Controller;

import Model.Player;
import Service.PlayerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    public Player getPlayerById(int id) {
        return playerService.getPlayerById(id);
    }
}
