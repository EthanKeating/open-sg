package me.eths.opensg.reflection;

import lombok.SneakyThrows;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Reflection {

    // The NMS version the server is running
    public static String NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(23);

    /**
     * Gets a specified class
     * @param name Class name
     * @return Class
     */
    @SneakyThrows
    public static Class<?> getClass(String name) {
        return Class.forName(name);
    }

    /**
     * Sets a field on an object
     * @param object Object
     * @param fieldName Field name
     * @param value Value
     */
    @SneakyThrows
    public void setField(Object object, String fieldName, Object value) {
        Field field = object.getClass().getField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    /**
     * Sets a field on an object
     * @param object Object
     * @param fieldName Field name
     * @param value Value
     */
    @SneakyThrows
    public void setDeclaredField(Object object, String fieldName, Object value) {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }


    /**
     * Gets a field on an object
     * @param object Object
     * @param fieldName Field name
     */
    @SneakyThrows
    public Object getField(Object object, String fieldName) {
        Field field = object.getClass().getField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    /**
     * Calls a specified method
     * @param object Object
     * @param method Method
     * @return Method Return
     */
    @SneakyThrows
    public static Object callMethod(Object object, String method, Object... params) {
        List<Method> methodList = Arrays.stream(object.getClass().getMethods())
                .filter(m -> m.getName().equals(method))
                .filter(m -> m.getParameters().length == params.length)
                .collect(Collectors.toList());

        return methodList.get(0).invoke(object, params);
    }

    /**
     * Calls a specified method
     * @param object Object
     * @param method Method
     * @return Method Return
     */
    @SneakyThrows
    public static Object callMethod(Class<?> object, String method, Object... params) {
        List<Method> methodList = Arrays.stream(object.getDeclaredMethods())
                .filter(m -> m.getName().equals(method))
                .collect(Collectors.toList());

        return methodList.get(0).invoke(object, params);
    }


    /**
     * Calls a specified method
     * @param object Object
     * @param method Method
     * @return Method Return
     */
    @SneakyThrows
    public static Object callMethod(Object object, String method, Class<?>[] classes, Object... params) {
        return object.getClass().getMethod(method, classes).invoke(object, params);
    }

    /**
     * Initializes a specified class
     * @param c Class
     * @param params Params
     * @return Initialized class
     */
    @SneakyThrows
    public static Object initialize(Class<?> c, Object... params) {
        List<Constructor<?>> constructorList = Arrays.stream(c.getConstructors())
                .filter(constructor -> constructor.getParameters().length == params.length)
                .collect(Collectors.toList());

        return constructorList.get(0).newInstance(params);
    }

    public void sendPacket(Player player, Object packet) {
        callMethod(getConnection(player), "sendPacket", packet);
    }

    public PlayerConnection getConnection(Player player) {
        return ((CraftPlayer) player).getHandle().playerConnection;
    }

    public Object createObjectivePacket(int mode, String name, String displayName) {
        Object packet = new PacketPlayOutScoreboardObjective();
        setDeclaredField(packet, "a", name);

        // Mode
        // 0 - Crate
        // 1 - Delete
        // 2 - Update
        setDeclaredField(packet, "d", mode);

        if (mode == 0 || mode == 2) {
            setDeclaredField(packet, "b", displayName);
            setDeclaredField(packet, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
        }

        return packet;
    }

    public Object createObjectiveDisplay(String name, int display) {
        Object packet = new PacketPlayOutScoreboardDisplayObjective();
        // Slot
        setDeclaredField(packet, "a", display);
        setDeclaredField(packet, "b", name);

        return packet;
    }

    public Object createTeamPacket(String name, Collection<String> players, int mode, String prefix, String suffix) {
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam();
        setDeclaredField(packet, "a", name);
        setDeclaredField(packet, "h", mode);
        setDeclaredField(packet, "b", ""); //displayname
        setDeclaredField(packet, "c", ""); //prefix
        setDeclaredField(packet, "d", ""); //suffix
        setDeclaredField(packet, "i", 0);
        setDeclaredField(packet, "e", "always");
        setDeclaredField(packet, "f", 0);
        setDeclaredField(packet, "g", players);

        return packet;
    }

    public PacketPlayOutScoreboardScore createScorePacket(String dummy, String name, int score, int action) {
        PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(dummy);
        //setField(packet, "b", name);
        setField(packet, "c", score);

        setField(packet, "d", action == 0 ?
                PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE :
                PacketPlayOutScoreboardScore.EnumScoreboardAction.REMOVE);

        return packet;
    }

}
