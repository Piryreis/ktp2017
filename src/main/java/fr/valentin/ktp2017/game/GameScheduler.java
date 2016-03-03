package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.Ktp2017;
import net.md_5.bungee.api.ChatColor;

/**
 * @author Val'entin.
 */
public class GameScheduler implements Runnable {

    private Game game;

    public GameScheduler(Game game){
        this.game = game;
    }

    public void run() {
        Ktp2017.log(game.gameStat.toString());
        testMinPlayer();
    }

    public void cancel(){
        Thread.interrupted();
    }

    private void testMinPlayer(){
        if (game.gameStat.equals(GameManager.GameStat.WAITING_PLAYER)) {

            GamePlayersList playersList = game.getPlayers();
            int players = playersList.size();
            int min_players = game.min_players;

            if (players >= min_players){
                game.gameStat = GameManager.GameStat.START_COOLDOWN;
            }
            else {
                Ktp2017.log("salut");
                playersList.sendActionBar(ChatColor.GOLD + "" + ChatColor.BOLD + "Il manque "
                        + ChatColor.LIGHT_PURPLE + "" +ChatColor.BOLD + (min_players - players)
                        + ChatColor.GOLD + "" + ChatColor.BOLD + "joueur(s)..");
            }
        }
    }

}
