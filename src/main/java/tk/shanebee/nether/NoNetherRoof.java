package tk.shanebee.nether;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tk.shanebee.nether.config.Config;
import tk.shanebee.nether.listener.PlayerDeathListener;
import tk.shanebee.nether.task.PlayerChecker;
import tk.shanebee.nether.util.Util;

public class NoNetherRoof extends JavaPlugin {

    private Config config;
    private PlayerChecker playerChecker;

    @Override
    public void onEnable() {
        // Load config
        this.config = new Config(this);

        // Load Listener
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);

        // Load task
        this.playerChecker = new PlayerChecker(this);

        Util.log("Successfully enabled");
    }

    @Override
    public void onDisable() {
        this.playerChecker.cancel();
        Util.log("Successfully disabled");
    }

    public Config getPluginConfig() {
        return this.config;
    }

}
