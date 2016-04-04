package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author Val'entin.
 */
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Game game = GameManager.getGame();
        Player player = event.getEntity();
        if (!GameManager.gameIsEmpty() && game.getGameStat().equals(GameManager.GameStat.STARTED)){
            game.getPlayers().killPlayer(player);
        }
        event.setDeathMessage(Ktp2017.getTag() + MessageUtil.createDeathMessage(player.getDisplayName(), player.getKiller().getDisplayName()));
        player.spigot().respawn();
    }
}
