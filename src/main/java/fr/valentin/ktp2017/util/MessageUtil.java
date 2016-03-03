package fr.valentin.ktp2017.util;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.config.Config;
import org.bukkit.ChatColor;

/**
 * @author Val'entin.
 */
public class MessageUtil {

    private Ktp2017 ktp2017 = Ktp2017.getInstance();
    private static MessageUtil instance = new MessageUtil();
    public static MessageUtil getInstance(){return instance;}
    private MessageUtil(){}

    /**
     * @return "[players/max_player]"
     */
    public String getPlayers(int players, int maxPlayer){
        return ChatColor.LIGHT_PURPLE + "[" + ChatColor.GREEN + players + ChatColor.LIGHT_PURPLE + "/" + ChatColor.GREEN + maxPlayer + ChatColor.LIGHT_PURPLE + "]";
    }
}
