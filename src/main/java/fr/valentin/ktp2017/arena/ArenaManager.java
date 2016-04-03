package fr.valentin.ktp2017.arena;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author Val'entin.
 */
public class ArenaManager {

    private static Ktp2017 ktp2017 = Ktp2017.getInstance();

    private static Arena arena;

    private ArenaManager(){}

    public static void loadArena(){
        try {

            String name = ktp2017.getConfiguration().arena_name;
            World world = Bukkit.getWorld(ktp2017.getConfiguration().arena_world);
            int size = ktp2017.getConfiguration().arena_size;
            boolean worldborder = ktp2017.getConfiguration().arena_worldborder;
            Location center = new Location(world, ktp2017.getConfiguration().arena_center_x,
                    ktp2017.getConfiguration().arena_center_y, ktp2017.getConfiguration().arena_center_z);

            newArena(name, center, size, worldborder);

        } catch (Exception e){
            Ktp2017.log("Aucune arène enregistrée dans le fichier configuration.");
        }
    }

    public static void newArena(String name, Location center, int size, boolean worldborder){
        removeArena();
        arena = new Arena(name, center, size, worldborder);
        Ktp2017.log("Nouvelle arène enregistré: " + arena.getName());
    }

    public static void removeArena(){
        if (!ArenaManager.arenaIsEmpty()){
            Ktp2017.log("L'arène " + arena.getName() + " va être supprimée.");
            arena.removeArena();
        }
    }

    public static Arena getArena(){
        return arena;
    }

    public static boolean arenaIsEmpty(){
        return arena == null;
    }

    public static boolean saveArenaInConfig(){
        if (!arenaIsEmpty()){
            Arena arena = getArena();
            FileConfiguration config = Config.getInstance().getConfig();
            config.set("arena.name", arena.getName());
            config.set("arena.worldborder", arena.hasWorldborder());
            config.set("arena.world", arena.getWorld().getName());
            config.set("arena.size", arena.getSize());
            config.set("arena.center.x", arena.getCenter().getBlockX());
            config.set("arena.center.y", arena.getCenter().getBlockY());
            config.set("arena.center.z", arena.getCenter().getBlockZ());
            Ktp2017.getInstance().saveConfig();
            return true;
        }
        return false;
    }
}
