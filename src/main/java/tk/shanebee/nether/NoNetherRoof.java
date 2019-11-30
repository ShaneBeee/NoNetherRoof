package tk.shanebee.nether;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import tk.shanebee.nether.config.Config;
import tk.shanebee.nether.task.PlayerChecker;

public class NoNetherRoof extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        // Load config
        this.config = new Config(this);

        // Load task
        new PlayerChecker(this);

        log("Successfully enabled");
    }

    @Override
    public void onDisable() {
        log("Successfully disabled");
    }

    public Config getPluginConfig() {
        return this.config;
    }

    public static void log(String message) {
        String prefix = "&7[&bNoNetherRoof&7] ";
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

}
