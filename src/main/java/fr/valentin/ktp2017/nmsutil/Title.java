package fr.valentin.ktp2017.nmsutil;

import fr.valentin.ktp2017.reflection.NMSReflection;
import fr.valentin.ktp2017.reflection.Reflection;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;

import java.lang.reflect.Constructor;

/**
 * @author Val'entin.
 */
public class Title {

    private PacketPlayOutTitle.EnumTitleAction action;
    private IChatBaseComponent text;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public Title(PacketPlayOutTitle.EnumTitleAction action, IChatBaseComponent text, int fadeIn, int stay, int fadeOut){
        this.action = action;
        this.text = text;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public Title(PacketPlayOutTitle.EnumTitleAction action, IChatBaseComponent text){
        this(action, text, -1, -1, -1);
    }

    public Title(int fadeIn, int stay, int fadeOut){
        this(PacketPlayOutTitle.EnumTitleAction.TIMES, (IChatBaseComponent)null, fadeIn, stay, fadeOut);
    }


    public Object getPacket(){
        Class clazz = NMSReflection.getNMSClass("PacketPlayOutTitle");
        Class[] type = new Class[]{PacketPlayOutTitle.EnumTitleAction.class, IChatBaseComponent.class, int.class, int.class, int.class};
        Constructor constructor = Reflection.getConstructor(clazz, type);
        Object packet = Reflection.getNewInstance(constructor, action, text, fadeIn, stay, fadeOut);
        return packet;
    }

}
