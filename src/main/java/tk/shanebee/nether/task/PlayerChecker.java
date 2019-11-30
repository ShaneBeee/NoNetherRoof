package tk.shanebee.nether.task;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tk.shanebee.nether.NoNetherRoof;
import tk.shanebee.nether.config.Config;

public class PlayerChecker extends BukkitRunnable {

    private Config config;

    public PlayerChecker(NoNetherRoof plugin) {
        this.config = plugin.getPluginConfig();
        this.runTaskTimer(plugin, -1, 20);
    }

    @Override
    public void run() {
        for (World world : Bukkit.getWorlds()) {
            if (world.getEnvironment() != World.Environment.NETHER) continue;
            for (Player player : world.getPlayers()) {
                GameMode mode = player.getGameMode();
                if (mode == GameMode.SURVIVAL || mode == GameMode.ADVENTURE) {
                    if (player.getLocation().getY() >= config.NETHER_HEIGHT) {
                        player.damage(config.DAMAGE_AMOUNT);
                    }
                }
            }
        }
    }

}
