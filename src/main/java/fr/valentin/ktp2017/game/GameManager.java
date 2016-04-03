package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.arena.Arena;
import fr.valentin.ktp2017.arena.ArenaManager;
import fr.valentin.ktp2017.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Val'entin.
 */
public class GameManager {

    private static Game game = null;

    public enum GameStat {
        WAITING_PLAYER, START_COOLDOWN, STARTED, ENDED
    }

    public static void loadGame(){
        if (ArenaManager.arenaIsEmpty()){
            Ktp2017.log("Aucune game n'a peut être démarré, veuillez configurer une arène.");
        } else {
            Arena arena = ArenaManager.getArena();
            int minPlayers = Config.getInstance().slot_min_players;
            int maxPlayers = Config.getInstance().slot_max_players;
            int duration = Config.getInstance().game_duration;

            newGame(arena, minPlayers, maxPlayers, duration);
        }
    }

    public static void newGame(Arena arena, int minPlayers, int maxPlayers, int duration){
        removeGame();
        game = new Game(arena, minPlayers, maxPlayers, duration);
        Ktp2017.log("Nouvelle game créée.");
    }

    public static void removeGame(){
        if (!GameManager.gameIsEmpty()){
            Ktp2017.log("La game va être supprimée.");
            game.removeGame();
        }
    }

    public static void restartGame(){
        if (!gameIsEmpty()) {
            Ktp2017.broadcastMessage(ChatColor.RED + "Redémarrage de la partie..");
            Ktp2017.log("Redémarrage de la partie.");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer(ChatColor.RED + "Redémarrage de la partie.");
            }
            removeGame();
            loadGame();
            Ktp2017.log("Le redémarrage c'est bien passé.");
        }
    }


    public static Game getGame(){
        return game;
    }

    public static boolean gameIsEmpty(){
        return game == null;
    }

}
