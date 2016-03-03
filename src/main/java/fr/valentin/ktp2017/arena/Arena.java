package fr.valentin.ktp2017.arena;

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

        loadWorldBorder();
    }

    private void loadWorldBorder(){
        if (worldborder) {
            WorldBorder wb = world.getWorldBorder();
            wb.setCenter(center);
            wb.setSize(size);
        }
    }

    public void removeArena(){
        world.getWorldBorder().reset();
    }

    public String getName(){
        return name;
    }

}
