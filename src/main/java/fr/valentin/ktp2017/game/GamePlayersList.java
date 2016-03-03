package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.nmsutil.ActionBar;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * @author Val'entin.
 */
public class GamePlayersList extends ArrayList<Player> {

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
}
