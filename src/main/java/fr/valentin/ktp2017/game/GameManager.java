package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.arena.Arena;
import fr.valentin.ktp2017.arena.ArenaManager;
import fr.valentin.ktp2017.config.Config;

/**
 * @author Val'entin.
 */
public class GameManager {

    private static Game game;

    public enum GameStat {
        WAITING_PLAYER, START_COOLDOWN, STARTED, ENDED
    }

    public static void loadGame(){
        if (ArenaManager.getArena() == null){
            Ktp2017.log("Aucune game n'a peut être démarré, veuillez configurer une arène.");
        } else {
            Arena arena = ArenaManager.getArena();
            int min_players = Config.getInstance().slot_min_players;
            int max_players = Config.getInstance().slot_max_players;

            newGame(arena, min_players, max_players);
        }
    }

    public static void newGame(Arena arena, int min_players, int max_players){
        removeGame();
        game = new Game(arena, min_players, max_players);
        Ktp2017.log("Nouvelle game créée.");
    }

    public static void removeGame(){
        if (game != null){
            Ktp2017.log("La game va être supprimée.");
            game.removeGame();
        }
    }

    public static Game getGame(){
        return game;
    }

}
