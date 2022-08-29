package tk.shanebee.nether.task;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import tk.shanebee.nether.NoNetherRoof;
import tk.shanebee.nether.config.Config;

public class PlayerChecker extends BukkitRunnable {

    private final double NETHER_HEIGHT;
    private final double DAMAGE_AMOUNT;

    public PlayerChecker(NoNetherRoof plugin) {
        Config config = plugin.getPluginConfig();
        NETHER_HEIGHT = config.NETHER_HEIGHT;
        DAMAGE_AMOUNT = config.DAMAGE_AMOUNT;
        this.runTaskTimer(plugin, -1, 20);
    }

    @Override
    public void run() {
        Bukkit.getWorlds().forEach(world -> {
            if (world.getEnvironment() != World.Environment.NETHER) return;
            world.getPlayers().forEach(player -> {
                GameMode mode = player.getGameMode();
                if (mode == GameMode.SURVIVAL || mode == GameMode.ADVENTURE) {
                    if (player.getLocation().getY() >= NETHER_HEIGHT) {
                        player.damage(DAMAGE_AMOUNT);
                    }
                }
            });
        });
    }

}
