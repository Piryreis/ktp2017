package fr.valentin.ktp2017.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author Val'entin.
 */
public class BukkitCooldown implements Runnable {

    private Plugin plugin;
    private int defaultTime;
    private int time;

    private boolean exist = false;
    private boolean started = false;

    private int taskID;

    public BukkitCooldown(Plugin plugin, int seconde){
        this.plugin = plugin;
        this.time = seconde;
        this.defaultTime = seconde;
    }

    public void start(){
        if (!exist) {
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
            exist = true;
        }
        started = true;
    }

    @Override
    public void run(){
        if (isStarted()) {
            if (time > 0) {
                time--;
            } else end();
        }
    }

    public void pause(){
        started = false;
    }

    public void end(){
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public int getTime(){
        return time;
    }

    public int getDefaultTime(){
        return defaultTime;
    }

    public void reset(){
        time = defaultTime;
    }

    public boolean isStarted(){
        return started;
    }
}