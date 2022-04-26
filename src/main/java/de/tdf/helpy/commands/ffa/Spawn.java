package de.tdf.helpy.commands.ffa;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		if (sen instanceof Player p) {
			File file = new File("plugins/Helpy/Settings.yml");
			YamlConfiguration settings = YamlConfiguration.loadConfiguration(file);
			if (Helpy.spawmPermission)
				if (!sen.hasPermission("Helpy.Spawn")) {
					Eng.permissionShow(sen, "Helpy.Spawn");
					return false;
				}
			Location l = settings.getLocation("Settings.Spawn.Location"),
					pl = p.getLocation();
			if (args.length == 0) {
				if (l == null) {
					p.sendMessage(Eng.PRE + "You could not be teleported, the spawn was not found.");
					return true;
				}
				p.teleport(l);
				p.playSound(pl, Sound.ITEM_CHORUS_FRUIT_TELEPORT, 0.2f, 1.2f);
				if (settings.getBoolean("Settings.Spawn.Title"))
					p.sendTitle(Eng.PRE, "§7You were §asuccesfully §9teleported§8.", 5, 16, 8);
			} else if (args.length == 1) {
				if (p.hasPermission("Helpy.spawn.set")) {
					if (args[0].equals("set")) {
						settings.set("Settings.Spawn.Location", pl);
						Helpy.saveSettings(settings, file);
						Eng.newLocation(sen, pl);
					} else
						Eng.argsUsage(sen, "/spawn [<set>]", true);
				} else
					Eng.permissionShow(sen, "Helpy.spawn.set");
			} else
				Eng.argsUsage(sen, "/spawn", true);
		} else
			sen.sendMessage(Eng.CMD_NOT_PLAYER);
		return false;
	}
}
