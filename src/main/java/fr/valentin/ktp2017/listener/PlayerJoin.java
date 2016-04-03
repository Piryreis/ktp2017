package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.nmsutil.Title;
import fr.valentin.ktp2017.util.MessageUtil;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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

        if (!GameManager.gameIsEmpty()) {
            Game game = GameManager.getGame();
            if(game.getGameStat().equals(GameManager.GameStat.WAITING_PLAYER)){
                game.getPlayers().add(player);
                int players = GameManager.getGame().getPlayers().size();
                int maxPlayers = GameManager.getGame().getMaxPlayers();
                String playersStats = MessageUtil.getPlayersOnMaxplayersWithColors(players, maxPlayers);
                event.setJoinMessage(Ktp2017.getTag() + "Le joueur " + player.getDisplayName() + " vient de se connecter. " + playersStats);
                player.teleport(GameManager.getGame().getArena().getCenter());
                player.sendMessage(Ktp2017.getTag() + "Bienvenue sur l'arene: " + ChatColor.AQUA + game.getArena().getName() + ChatColor.YELLOW + ".");

                player.setGameMode(GameMode.ADVENTURE);
                player.setMaxHealth(1.0);
                player.setHealthScale(1);
                player.setHealth(1.0);
                player.setSaturation(20);
                player.getInventory().clear();

            }
        }
        else {
            event.setJoinMessage(Ktp2017.getTag() + "Le joueur " + player.getDisplayName() + " vient de se connecter.");
        }
    }

}
