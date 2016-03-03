package fr.valentin.ktp2017;

import fr.valentin.ktp2017.arena.ArenaManager;
import fr.valentin.ktp2017.command.ktp2017Commands;
import fr.valentin.ktp2017.config.Config;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.listener.PlayerJoin;
import fr.valentin.ktp2017.listener.PlayerLogin;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Val'entin.
 */
public class Ktp2017 extends JavaPlugin {

    private PluginManager pluginManager;
    private static Ktp2017 instance;

    public static Ktp2017 getInstance() {
        if (instance == null) {
            instance = new Ktp2017();
        }
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;
        this.pluginManager = this.getServer().getPluginManager();

        // Events Listeners
        pluginManager.registerEvents(new PlayerJoin(), this);
        pluginManager.registerEvents(new PlayerLogin(), this);

        // Command
        getCommand("ktp2017").setExecutor(new ktp2017Commands());

        // Load
        ArenaManager.loadArena();
        GameManager.loadGame();
    }

    @Override
    public void onDisable(){
        GameManager.removeGame();
        ArenaManager.removeArena();
    }

    public static void log(String message){
        instance.getLogger().info(message);
    }

    public static String getTag(){
        return ChatColor.AQUA + "[" + ChatColor.LIGHT_PURPLE + "Ktp2017" + ChatColor.AQUA + "] " + ChatColor.YELLOW;
    }

    public Config getConfiguration(){
        return Config.getInstance();
    }
}