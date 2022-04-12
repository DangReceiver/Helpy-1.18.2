package de.tdf.helpy.helpy;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

public class DisableEvent implements Listener {
	@EventHandler
	public void handleDisable(final PluginDisableEvent e) {
		if (e.getPlugin() == Helpy.getPlugin() && Helpy.kickAll) {
			final FileConfiguration c = Helpy.getPlugin().getConfig();
			if (!c.isSet("Settings.DisableEvent.KickMessage")) {
				c.set("Settings.DisableEvent.KickMessage", "You were §ckicked§8, §7since the §6server §7was §4stopped§8.");
				Helpy.getPlugin().saveConfig();
			}
			for (final Player ap : Bukkit.getOnlinePlayers()) {
				ap.playSound(ap.getLocation(), Sound.ENTITY_ITEM_FRAME_BREAK, 0.5f, 0.85f);
				ap.kickPlayer(" §0 \n §0 \n §0  §0 \n §0 \n §0 \n" + Eng.PRE + c.getString("Settings.DisableEvent.KickMessage"));
			}
		}
	}
}
