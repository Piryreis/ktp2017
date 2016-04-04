package fr.valentin.ktp2017.task;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.event.GameWonEvent;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.game.GamePlayersList;
import fr.valentin.ktp2017.util.BukkitCooldown;
import fr.valentin.ktp2017.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * @author Val'entin.
 */
public class Started implements Task {

    private Game game;
    private BukkitCooldown cooldown;

    public Started(Game game){
        this.game = game;
        cooldown = new BukkitCooldown(Ktp2017.getInstance(), 60 * game.getDuration());
    }

    @Override
    public void run() {
        if (game.getGameStat().equals(GameManager.GameStat.STARTED)){

            GamePlayersList playersList = game.getPlayers();

            if (!cooldown.isStarted()){
                startGame();
                cooldown.start();
            }

            int cooldownTime = cooldown.getTime();

            String time = MessageUtil.getDigitalTimeFormat(cooldownTime);

            playersList.sendActionBar(ChatColor.GREEN + "" + ChatColor.BOLD + time
                    + ChatColor.GRAY + "" + ChatColor.BOLD + " | "
                    + ChatColor.GOLD + "" +ChatColor.BOLD + "il reste "
                    + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + playersList.size()
                    + ChatColor.GOLD + "" + ChatColor.BOLD + " joueurs.");

            if(cooldownTime <= 0){
                cooldown.end();
                game.setGameStat(GameManager.GameStat.ENDED);
                game.getPlayers().clear();
                Ktp2017.getInstance().getPluginManager().callEvent(new GameWonEvent(game, null));
            }
            else {
                switch (cooldownTime) {
                    case 60 * 10:
                    case 60 * 5:
                    case 60 * 4:
                    case 60 * 3:
                    case 60 * 2:
                    case 60 * 1:
                    case 30:
                    case 20:
                    case 10:
                        Ktp2017.broadcastMessage("Fin de la partie dans "
                                + ChatColor.RED + MessageUtil.getTimeFormat(cooldownTime)
                                + ChatColor.YELLOW + ".");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void startGame(){
        for (Player player : game.getPlayers().get()){
            player.setGameMode(GameMode.ADVENTURE);
            player.setMaxHealth(1.0);
            player.setHealthScale(1);
            player.setHealth(1.0);
            player.setSaturation(20);
            player.getInventory().clear();
        }
        Ktp2017.broadcastMessage("La partie vien de démarrer, à l'attaque !");
    }

}
