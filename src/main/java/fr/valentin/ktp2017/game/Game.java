package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.arena.Arena;
import org.bukkit.Bukkit;

/**
 * @author Val'entin.
 */
public class Game {

    public Arena arena;
    public int min_players;
    public int max_players;

    private GamePlayersList gamePlayersList;
    private GameScheduler gameScheduler;
    public GameManager.GameStat gameStat = GameManager.GameStat.WAITING_PLAYER;

    public Game(Arena arena, int min_players, int max_players){
        this.arena = arena;
        this.min_players = min_players;
        this.max_players = max_players;

        gamePlayersList = new GamePlayersList();
        gameScheduler = new GameScheduler(this);
        Bukkit.getScheduler().runTaskTimer(Ktp2017.getInstance(), gameScheduler, 0L, 20L);
    }

    public void removeGame(){
        gameScheduler.cancel();
        arena.removeArena();
    }

    public GamePlayersList getPlayers(){
        return gamePlayersList;
    }
}
