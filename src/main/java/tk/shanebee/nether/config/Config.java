package tk.shanebee.nether.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tk.shanebee.nether.NoNetherRoof;

import java.io.File;

public class Config {

    private NoNetherRoof plugin;
    private File configFile;
    private FileConfiguration config;

    // Config options
    public double NETHER_HEIGHT;
    public double DAMAGE_AMOUNT;

    public Config(NoNetherRoof plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        loadConfigOptions();
    }

    private void loadConfigOptions() {
        this.NETHER_HEIGHT = config.getDouble("settings.damage-height");
        this.DAMAGE_AMOUNT = config.getDouble("settings.damage-amount");
    }

}
