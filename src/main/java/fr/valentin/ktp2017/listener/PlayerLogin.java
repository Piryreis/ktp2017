package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * @author Val'entin.
 */
public class PlayerLogin implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        Game game = GameManager.getGame();

        if (GameManager.gameIsEmpty()){
            if (!player.hasPermission("ktp2017.op")) {
                event.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatColor.RED + "La partie n'est pas configurer"
                        + ChatColor.GRAY + "\n veuillez contacter un administrateur");
            }
        }
        else {
            if (game.getGameStat().equals(GameManager.GameStat.WAITING_PLAYER) || game.getGameStat().equals(GameManager.GameStat.START_COOLDOWN)){
                Integer maxPlayer = game.getMaxPlayers();
                Integer players = game.getPlayers().size() + 1; // +1 pour compter le joueur en login
                if (maxPlayer <= players){
                    event.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatColor.RED + "La partie est remplie.");
                }
            }
            else if (game.getGameStat().equals(GameManager.GameStat.STARTED) || game.getGameStat().equals(GameManager.GameStat.ENDED)){
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "La partie est déjà commencée.");
            }

        }

    }

}
