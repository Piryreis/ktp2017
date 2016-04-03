package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.event.GameWonEvent;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Val'entin.
 */
public class GameWon implements Listener {

    @EventHandler
    public void onGameWon(GameWonEvent event){
        Game game = event.getGame();
        game.setGameStat(GameManager.GameStat.ENDED);
        Player player = event.getPlayer();

        // Si il y a une égalité (temps écoulé)
        if (player == null){
            sendWinMessage(ChatColor.GOLD + "       Fin de la partie, temps écoulé :(.");
        }
        else {
            sendWinMessage(ChatColor.YELLOW + "       Le joueur " + ChatColor.GOLD  + "" + ChatColor.BOLD + player.getDisplayName()
                    + ChatColor.RESET + ChatColor.YELLOW + " remporte la partie !");
        }
    }

    private void sendWinMessage(String message){
        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  ----------------------------------------");
        Bukkit.broadcastMessage(message);
        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  ----------------------------------------");
    }
}
