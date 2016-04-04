package fr.valentin.ktp2017;

import fr.valentin.ktp2017.arena.ArenaManager;
import fr.valentin.ktp2017.command.ktp2017Commands;
import fr.valentin.ktp2017.config.Config;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.listener.*;
import org.bukkit.Bukkit;
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
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;
        this.pluginManager = this.getServer().getPluginManager();

        // Load Config
        saveDefaultConfig();

        // Events Listeners
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerLoginListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new GameWonListener(), this);

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
        saveConfig();
    }

    public static void log(String message){
        instance.getLogger().info(message);
    }

    public static void broadcastMessage(String message){
        Bukkit.getServer().broadcastMessage(getTag() + message);
    }

    public static String getTag(){
        return ChatColor.AQUA + "[" + ChatColor.LIGHT_PURPLE + "Ktp2017" + ChatColor.AQUA + "] " + ChatColor.YELLOW;
    }

    public Config getConfiguration(){
        return Config.getInstance();
    }

    public PluginManager getPluginManager(){
        return pluginManager;
    }
}