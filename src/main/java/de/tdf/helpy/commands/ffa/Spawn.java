package de.tdf.helpy.commands.ffa;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		if (sen instanceof Player) {
			final Player p = (Player) sen;
			if (Helpy.spawmPermission)
				if (!sen.hasPermission("Helpy.Spawn")) {
					Eng.permissionShow(sen, "Helpy.Spawn");
					return false;
				}
			final FileConfiguration con = Helpy.getPlugin().getConfig();
			Location s = (Location) con.get("Settings.Spawn.Permission");
			if (args.length == 0) {
				if (s == null) {
					p.sendMessage(Eng.PRE + "YOu could not be teleported, the spawn was not found.");
					return true;
				}
				p.teleport(s);
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.2f, 1.2f);
				if (con.getBoolean("Settings.Spawn.Title"))
					p.sendTitle(Eng.PRE, "§7You were §asuccesfully §9teleported§8.", 5, 16, 8);
			} else if (args.length == 1) {
				if (p.hasPermission("Helpy.spawn.set")) {
					if (args[0].equals("set")) {
						con.set("server.spawn", p.getLocation());
						Helpy.getPlugin().saveConfig();
						Eng.newLocation(sen, p.getLocation());
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
