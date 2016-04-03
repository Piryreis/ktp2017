package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.nmsutil.ActionBar;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Val'entin.
 */
public class GamePlayersList extends LinkedList<Player> {

    private Game game;

    public GamePlayersList(Game game){
        this.game = game;
    }

    public List<Player> get(){
        return this;
    }

    public void sendMessage(String message){
        for (Player player : this){
            player.sendMessage(message);
        }
    }

    public void sendActionBar(String message){
        ActionBar actionBar = new ActionBar(message);
        Object packet = actionBar.getPacket();
        for (Player player : this){
            PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
            playerConnection.sendPacket((Packet) packet);
        }
    }

    public boolean killPlayer(Player player){
        if (this.contains(player)){
            player.setGameMode(GameMode.SPECTATOR);
            this.remove(player);
            player.sendMessage(Ktp2017.getTag() + ChatColor.RED + "Vous etes mort :( !");
            player.sendMessage(Ktp2017.getTag() + "Vous êtes maintenant en mode spectateur.");
            game.checkIfGameIsWon();
            return true;
        }
        return false;
    }
}
