package fr.valentin.ktp2017.task;

import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.game.GamePlayersList;
import net.md_5.bungee.api.ChatColor;

/**
 * @author Val'entin.
 */
public class WaitingPlayer implements Task {

    private Game game;

    public WaitingPlayer(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        if (game.getGameStat().equals(GameManager.GameStat.WAITING_PLAYER)) {

            GamePlayersList playersList = game.getPlayers();
            int players = playersList.size();
            int minPlayers = game.getMinPlayers();

            if (players >= minPlayers){
                game.setGameStat(GameManager.GameStat.START_COOLDOWN);
            }
            else {
                playersList.sendActionBar(ChatColor.GOLD + "" + ChatColor.BOLD + "Il manque "
                        + ChatColor.LIGHT_PURPLE + "" +ChatColor.BOLD + (minPlayers - players)
                        + ChatColor.GOLD + "" + ChatColor.BOLD + " joueur(s)..");
            }
        }
    }
}
