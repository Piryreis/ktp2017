package fr.valentin.ktp2017.event;

import fr.valentin.ktp2017.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Val'entin.
 */
public class GameWonEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Game game;
    private Player player;

    public GameWonEvent(Game game, Player winner){
        this.game = game;
        this.player = winner;
    }

    /**
     *
     * @return la parti gagnée
     */
    public Game getGame(){
        return game;
    }

    /**
     *
     * @return le gagnant de la partie
     */
    public Player getPlayer(){
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
