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

    private final boolean CLEAR_DROPS;
    private final double NETHER_HEIGHT;
    private final String DEATH_MESSAGE;

    public PlayerDeathListener(NoNetherRoof plugin) {
        Config config = plugin.getPluginConfig();
        CLEAR_DROPS = config.CLEAR_DROPS;
        NETHER_HEIGHT = config.NETHER_HEIGHT;
        DEATH_MESSAGE = config.DEATH_MESSAGE;
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getWorld().getEnvironment() != World.Environment.NETHER) return;

        if (CLEAR_DROPS && player.getLocation().getY() >= NETHER_HEIGHT) {
            event.getDrops().clear();
        }
        if (!DEATH_MESSAGE.equals("none")) {
            String deathMessage = DEATH_MESSAGE.replace("%player%", player.getName());
            event.setDeathMessage(Util.getColString(deathMessage));
        }
    }

}
