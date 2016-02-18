package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.nmsutil.ActionBar;
import fr.valentin.ktp2017.nmsutil.Title;
import fr.valentin.ktp2017.util.MessageUtil;
import net.minecraft.server.v1_8_R2.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Val'entin.
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String players_stats = MessageUtil.getInstance().getPlayers();
        event.setJoinMessage(Ktp2017.getTag() + "Le joueur " + event.getPlayer().getDisplayName() + " vient de se connecter. " + players_stats);
        Title title = new Title(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(ChatColor.RED + "Ktp" + ChatColor.GREEN + "2017"));
        ActionBar actionBar = new ActionBar(ChatColor.GOLD + "" + ChatColor.BOLD + "La partie va bientôt commencer..");
        PlayerConnection playerConnection = ((CraftPlayer) event.getPlayer()).getHandle().playerConnection;
        playerConnection.sendPacket((Packet) title.getPacket());
        playerConnection.sendPacket((Packet) actionBar.getPacket());
    }

}
