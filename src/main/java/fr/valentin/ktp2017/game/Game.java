package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.arena.Arena;
import fr.valentin.ktp2017.event.GameWonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Val'entin.
 */
public class Game {

    private Arena arena;
    private int minPlayers;
    private int maxPlayers;
    private int duration;

    private GamePlayersList gamePlayersList;
    private GameScheduler gameScheduler;
    private GameManager.GameStat gameStat = GameManager.GameStat.WAITING_PLAYER;

    public Game(Arena arena, int minPlayers, int maxPlayers, int duration){
        this.arena = arena;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.duration = duration;

        gamePlayersList = new GamePlayersList(this);
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

    public boolean checkIfGameIsWon(){
        if (getPlayers().size() <= 1){
            Player winner = getPlayers().get(0);
            Ktp2017.getInstance().getPluginManager().callEvent(new GameWonEvent(this, winner));
            return true;
        }
        else return false;
    }

    public Arena getArena(){
        return arena;
    }

    public int getMaxPlayers(){
        return maxPlayers;
    }

    public int getMinPlayers(){
        return minPlayers;
    }

    public int getDuration(){
        return duration;
    }

    public GameManager.GameStat getGameStat(){
        return gameStat;
    }

    public void setGameStat(GameManager.GameStat gameStat){
        this.gameStat = gameStat;
    }
}
