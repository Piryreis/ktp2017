package fr.valentin.ktp2017.nmsutil;

import fr.valentin.ktp2017.reflection.NMSReflection;
import fr.valentin.ktp2017.reflection.Reflection;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;

import java.lang.reflect.Constructor;

/**
 * @author Val'entin.
 */
public class ActionBar {

    private IChatBaseComponent text;
    private byte position; //0: chat (chat box), 1: system message (chat box), 2: above hotbar

    public ActionBar(String text){
        this.text = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\"}");
        this.position = 2;
    }

    public Object getPacket(){
        Class clazz = NMSReflection.getNMSClass("PacketPlayOutChat");
        Class[] type = new Class[]{IChatBaseComponent.class, byte.class};
        Constructor constructor = Reflection.getConstructor(clazz, type);
        Object packet = Reflection.getNewInstance(constructor, text, position);
        return packet;
    }

}
