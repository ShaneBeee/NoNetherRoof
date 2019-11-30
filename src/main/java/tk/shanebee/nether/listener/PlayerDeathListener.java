package tk.shanebee.nether.listener;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import tk.shanebee.nether.NoNetherRoof;
import tk.shanebee.nether.config.Config;
import tk.shanebee.nether.util.Util;

public class PlayerDeathListener implements Listener {

    private Config config;

    public PlayerDeathListener(NoNetherRoof plugin) {
        this.config = plugin.getPluginConfig();
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getWorld().getEnvironment() != World.Environment.NETHER) return;

        if (config.CLEAR_DROPS && player.getLocation().getY() >= config.NETHER_HEIGHT) {
            event.getDrops().clear();
        }
        if (!config.DEATH_MESSAGE.equals("none")) {
            String deathMessage = config.DEATH_MESSAGE.replace("%player%", player.getName());
            event.setDeathMessage(Util.getColString(deathMessage));
        }
    }

}
