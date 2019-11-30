package tk.shanebee.nether.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Util {

    public static void log(String message) {
        String prefix = "&7[&bNoNetherRoof&7] ";
        Bukkit.getConsoleSender().sendMessage(getColString(prefix + message));
    }

    public static String getColString(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
