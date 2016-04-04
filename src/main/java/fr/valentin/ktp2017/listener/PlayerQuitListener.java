package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Val'entin.
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.setQuitMessage(Ktp2017.getTag() + "Le joueur " + player.getDisplayName() + " vient de se déconnecter.");

        Game game = GameManager.getGame();
        if (!GameManager.gameIsEmpty()){
            game.getPlayers().remove(player);
        }
    }
}
