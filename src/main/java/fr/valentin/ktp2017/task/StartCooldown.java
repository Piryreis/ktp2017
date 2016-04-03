package fr.valentin.ktp2017.task;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.game.GamePlayersList;
import fr.valentin.ktp2017.util.Cooldown;
import net.md_5.bungee.api.ChatColor;

/**
 * @author Val'entin.
 */
public class StartCooldown implements Task {

    private Game game;
    private Cooldown cooldown;

    public StartCooldown(Game game){
        this.game = game;
        this.cooldown = new Cooldown(45);
    }

    @Override
    public void run() {
        if (game.getGameStat().equals(GameManager.GameStat.START_COOLDOWN)) {

            if (!cooldown.isStarted()){
                cooldown.start();
            }

            GamePlayersList playersList = game.getPlayers();
            int players = playersList.size();
            int minPlayers = game.getMinPlayers();

            if (players < minPlayers){
                game.setGameStat(GameManager.GameStat.WAITING_PLAYER);
                int defaultTime = cooldown.getDefaultTime();
                cooldown.end();
                cooldown = new Cooldown(defaultTime);
                Ktp2017.broadcastMessage("Il n'y a plus assez de joueur.");
                return;
            }
            else {
                if(cooldown.getTime() <= 0){
                    game.setGameStat(GameManager.GameStat.STARTED);
                    cooldown.end();
                    return;
                }
                else {
                    playersList.sendActionBar(ChatColor.GOLD + "" + ChatColor.BOLD + "Démarrage dans "
                            + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + cooldown.getTime()
                            + ChatColor.GOLD + "" + ChatColor.BOLD + " seconde(s)..");
                }
            }
        }
    }

    private void sendCooldown(String time){
        game.getPlayers().sendMessage(Ktp2017.getTag() + "Démarrage dans " + ChatColor.RED + time + ChatColor.YELLOW + ".");
    }

}