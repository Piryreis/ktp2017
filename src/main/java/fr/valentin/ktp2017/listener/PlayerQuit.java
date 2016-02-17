package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Val'entin.
 */
public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage(Ktp2017.getTag() + "Le joueur " + event.getPlayer().getDisplayName() + " vient de se déconnecter.");
    }
}
