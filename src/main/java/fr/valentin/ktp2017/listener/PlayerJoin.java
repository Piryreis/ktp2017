package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.config.Config;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.nmsutil.ActionBar;
import fr.valentin.ktp2017.nmsutil.Title;
import fr.valentin.ktp2017.util.MessageUtil;
import net.minecraft.server.v1_8_R2.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Val'entin.
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        Title title = new Title(PacketPlayOutTitle.EnumTitleAction.TITLE, ChatColor.RED + "Ktp" + ChatColor.GREEN + "2017");
        Title subTitle = new Title(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, ChatColor.AQUA + "Le DoubleRunQuadrupleUchRunner !");

        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket((Packet) title.getPacket());
        playerConnection.sendPacket((Packet) subTitle.getPacket());

        if (GameManager.getGame() != null) {
            if(GameManager.getGame().gameStat.equals(GameManager.GameStat.WAITING_PLAYER)){
                GameManager.getGame().getPlayers().add(player);
                int players = GameManager.getGame().getPlayers().size();
                int maxPlayers = GameManager.getGame().max_players;
                String players_stats = MessageUtil.getInstance().getPlayers(players, maxPlayers);
                event.setJoinMessage(Ktp2017.getTag() + "Le joueur " + player.getDisplayName() + " vient de se connecter. " + players_stats);
            }
        }
        else {
            event.setJoinMessage(Ktp2017.getTag() + "Le joueur " + player.getDisplayName() + " vient de se connecter.");
        }
    }

}
