package fr.valentin.ktp2017.util;

import fr.valentin.ktp2017.Ktp2017;
import org.bukkit.ChatColor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Val'entin.
 */
public class MessageUtil {

    private MessageUtil(){}
    /**
     * @return "[players/max_player]"
     */
    public static String getPlayersOnMaxplayersWithColors(int players, int maxPlayer){
        return ChatColor.LIGHT_PURPLE + "[" + ChatColor.GREEN + players + ChatColor.LIGHT_PURPLE + "/" + ChatColor.GREEN + maxPlayer + ChatColor.LIGHT_PURPLE + "]";
    }

    public static String getTimeFormat(int seconde){
        String timeFormat;
        int minute = seconde / 60;
        if (minute >= 1){
            int second = seconde % 60;
            if (second < 1){
                timeFormat = minute + " minute(s)";
            }
            else {
                timeFormat = minute + " minute(s) " + second + " seconde(s)";
            }
        }
        else {
            timeFormat = seconde + " seconde(s)";
        }
        return timeFormat;
    }

    public static String getDigitalTimeFormat(int seconde){
        String timeFormat;
        DecimalFormat decimalFormat = new DecimalFormat("00");
        float minute = seconde / 60f;
        int second = seconde % 60;
        if (minute < 1){
            minute = 0;
        }
        timeFormat = decimalFormat.format(minute) + ":" + decimalFormat.format(second);
        return timeFormat;
    }

    public static String createDeathMessage(String player, String cause){
        String message = "";
        message += ChatColor.YELLOW + "Le joueur ";
        message += ChatColor.RED + player;
        message += ChatColor.YELLOW + " a été tué par ";
        message += ChatColor.GREEN + cause;
        message += ChatColor.YELLOW + ".";
        return message;
    }
    
}
