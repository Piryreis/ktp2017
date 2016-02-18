package fr.valentin.ktp2017.reflection;

import java.lang.reflect.Constructor;

/**
 * @author Val'entin.
 */
public class Reflection {

    /**
     * Retourne une class spécifiée.
     * @param name avec le chemin d'accès
     * @return une Class.
     * @throws Exception
     */
    public static Class getClassWithExeption(String name) throws Exception {
        return Class.forName(name);
    }

    /**
     * Retourne une class spécifiée.
     * @param name avec le chemin d'accès.
     * @return une Class ou null si elle n'est pas trouvé.
     */
    public static Class getClass(String name){
        Class clazz = null;
        try {
            clazz = getClassWithExeption(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * Retourne le constructeur de la class avec les arguments spécifiés
     * @param clazz
     * @param args
     * @return
     * @throws Exception
     */
    public static Constructor getConstructorWithExeption(Class clazz, Class... args) throws Exception {
        return clazz.getConstructor(args);
    }

    /**
     * Retourne le constructeur de la class avec les arguments spécifiés
     * @param clazz
     * @param args
     * @return
     */
    public static Constructor getConstructor(Class clazz, Class... args){
        Constructor constructor = null;
        try {
            constructor = getConstructorWithExeption(clazz, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constructor;
    }

    /**
     * Retourne un objet d'une nouvelle instance d'une class
     * @param constructor
     * @param initargs
     * @return
     * @throws Exception
     */
    public static Object getNewInstanceWithExeption(Constructor constructor, Object... initargs) throws Exception {
        return constructor.newInstance(initargs);
    }

    /**
     * Retourne un objet d'une nouvelle instance d'une class
     * @param constructor
     * @param initargs
     * @return
     */
    public static Object getNewInstance(Constructor constructor, Object... initargs){
        Object object = null;
        try {
            object = getNewInstanceWithExeption(constructor, initargs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
