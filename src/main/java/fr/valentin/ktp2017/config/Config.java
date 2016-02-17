package fr.valentin.ktp2017.config;

import fr.valentin.ktp2017.Ktp2017;
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

    /**
     * Slot:
     *    max_players:
     *    min:players
     */
    public Integer slot_max_players = getConfig().getInt("slot.max_players");
    public Integer slot_min_players = getConfig().getInt("slot.min_players");


}

