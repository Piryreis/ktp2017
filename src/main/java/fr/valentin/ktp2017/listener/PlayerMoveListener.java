package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.game.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author Val'entin.
 */
public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (!GameManager.gameIsEmpty() && GameManager.getGame().getPlayers().contains(player)){
            if (!GameManager.getGame().getArena().isInArena(event.getTo())){
                event.setCancelled(true);
                player.sendMessage(Ktp2017.getTag() + ChatColor.RED + "Limite de l'arène.");
            }
        }
    }
}
