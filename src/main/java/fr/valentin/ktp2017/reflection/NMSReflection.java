package fr.valentin.ktp2017.reflection;

import org.bukkit.Bukkit;

/**
 * @author Val'entin.
 */
public class NMSReflection extends Reflection {

    private NMSReflection() {
        super();
    }

    /**
     * Retourne la version du package net.minecraft.server.
     * @return
     */
    public static String getNMSVersion(){
        String version;
        String name = Bukkit.getServer().getClass().getPackage().getName();
        version = name.substring(name.lastIndexOf('.') + 1);
        return version;
    }

    /**
     * Retourne la class du package net.minecraft.server.VERSION.
     * @param name Le nom de la class
     * @return
     */
    public static Class getNMSClass(String name){
        String fullname = "net.minecraft.server." + getNMSVersion() + "." + name;
        return getClass(fullname);
    }
}
