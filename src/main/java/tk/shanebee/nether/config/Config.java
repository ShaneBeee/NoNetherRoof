package tk.shanebee.nether.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tk.shanebee.nether.NoNetherRoof;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {

    private NoNetherRoof plugin;
    private File configFile;
    private FileConfiguration config;

    // Config options
    public double NETHER_HEIGHT;
    public double DAMAGE_AMOUNT;
    public boolean CLEAR_DROPS;
    public String DEATH_MESSAGE;

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
        matchConfig();
        loadConfigOptions();
    }

    // Used to update config
    @SuppressWarnings("ConstantConditions")
    private void matchConfig() {
        try {
            boolean hasUpdated = false;
            InputStream is = plugin.getResource(configFile.getName());
            assert is != null;
            InputStreamReader isr = new InputStreamReader(is);
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(isr);
            for (String key : defConfig.getConfigurationSection("").getKeys(true)) {
                if (!config.contains(key)) {
                    config.set(key, defConfig.get(key));
                    hasUpdated = true;
                }
            }
            for (String key : config.getConfigurationSection("").getKeys(true)) {
                if (!defConfig.contains(key)) {
                    config.set(key, null);
                    hasUpdated = true;
                }
            }
            if (hasUpdated)
                config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadConfigOptions() {
        this.NETHER_HEIGHT = config.getDouble("settings.damage-height");
        this.DAMAGE_AMOUNT = config.getDouble("settings.damage-amount");
        this.CLEAR_DROPS = config.getBoolean("settings.clear-death-drops");
        this.DEATH_MESSAGE = config.getString("settings.death-message");
    }

}
