package fr.valentin.ktp2017.config;

import fr.valentin.ktp2017.Ktp2017;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author Val'entin.
 */
public class Config {

    private Ktp2017 ktp2017 = Ktp2017.getInstance();
    private static Config instance = new Config();
    public static Config getInstance(){return instance;}
    private Config(){}

    public FileConfiguration getConfig(){
        return ktp2017.getConfig();
    }

    /*
     * slot:
     *    max_players:
     *    min:players
     */
    public Integer slot_max_players = getConfig().getInt("slot.max_players");
    public Integer slot_min_players = getConfig().getInt("slot.min_players");

    /*
     * arena:
     *    name:
     *    worldborder:
     *    world:
     *    size:
     *    center:
     *        x:
     *        y:
     *        z:
     */
    public String arena_name = getConfig().getString("arena.name");
    public boolean arena_worldborder = getConfig().getBoolean("arena.worldborder");
    public String arena_world = getConfig().getString("arena.world");
    public int arena_size = getConfig().getInt("arena.size");
    public double arena_center_x = getConfig().getDouble("arena.center.x");
    public double arena_center_y = getConfig().getDouble("arena.center.y");
    public double arena_center_z = getConfig().getDouble("arena.center.z");
}

