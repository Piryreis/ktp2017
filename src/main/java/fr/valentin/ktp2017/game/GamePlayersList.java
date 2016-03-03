package fr.valentin.ktp2017.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * @author Val'entin.
 */
public class GamePlayersList extends ArrayList<Player> {

    private Game game;

    public GamePlayersList(Game game){
        this.game = game;
    }

    public void sendMessage(){
        for (Player player : this){

        }
    }

}
