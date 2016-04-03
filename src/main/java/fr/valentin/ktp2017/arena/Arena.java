package fr.valentin.ktp2017.arena;

import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

/**
 * @author Val'entin.
 */
public class Arena {

    private String name;
    private Location center;
    private int size;
    private boolean worldborder;

    private World world;

    public Arena(String name, Location center, int size, boolean worldborder){
        this.name = name;
        this.center = center;
        this.size = size;
        this.worldborder = worldborder;
        this.world = center.getWorld();

        loadWorld();
        loadWorldBorder();
    }

    private void loadWorld(){
        world.setSpawnLocation(center.getBlockX(),  center.getBlockY(), center.getBlockZ());
        world.setDifficulty(Difficulty.HARD);
        world.setTime(12000);
        world.setWeatherDuration(1000000);
    }

    private void loadWorldBorder(){
        if (worldborder) {
            WorldBorder wb = world.getWorldBorder();
            wb.setCenter(center);
            wb.setSize(size);
        }
    }

    public boolean isInArena(Location location){
        int x = location.getBlockX();
        int z = location.getBlockZ();

        int arenaNordX = center.getBlockX() + size / 2;
        int arenaNordZ = center.getBlockZ() + size / 2;
        int arenaSudX = center.getBlockX() - size / 2;
        int arenaSudZ = center.getBlockZ() - size / 2;

        return (x < arenaNordX && x > arenaSudX && z < arenaNordZ && z > arenaSudZ) ? true : false;
    }

    public void removeArena(){
        world.getWorldBorder().reset();
    }

    public String getName(){
        return name;
    }

    public Location getCenter(){
        return center;
    }

    public int getSize(){
        return size;
    }

    public boolean hasWorldborder(){
        return worldborder;
    }

    public World getWorld(){
        return world;
    }
}
