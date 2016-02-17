package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.util.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Val'entin.
 */
public class PlayerJoin implements Listener {

    private Ktp2017 instance = Ktp2017.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String players_stats = MessageUtil.getInstance().getPlayers();
        event.setJoinMessage(Ktp2017.getTag() + "Le joueur " + event.getPlayer().getDisplayName() + " vient de se connecter. " + players_stats);
    }

}
