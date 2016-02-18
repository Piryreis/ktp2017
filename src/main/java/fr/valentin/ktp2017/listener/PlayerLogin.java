package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * @author Val'entin.
 */
public class PlayerLogin implements Listener {

    private Ktp2017 ktp2017 = Ktp2017.getInstance();

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event){
        Integer max_player = ktp2017.getConfiguration().slot_max_players;
        Integer players = ktp2017.getServer().getOnlinePlayers().size() + 1; // +1 pour compter le joueur en login
        if (max_player <= players){
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatColor.RED + "La partie est remplie.");
        }
    }

}
