package de.tdf.helpy.commands.utils;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class God implements CommandExecutor {
	public static List<String> iva;

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (p.hasPermission("Helpy.god.self")) {
				if (args.length == 0) {
					if (!God.iva.contains(p.getName())) {
						God.iva.add(p.getName());
						Eng.atChange(p, "action", "§bInvulnerability", true, "false", "true", true, false);
						p.setInvulnerable(true);
					} else if (God.iva.contains(p.getName())) {
						God.iva.remove(p.getName());
						Eng.atChange(p, "action", "§bInvulnerability", true, "true", "false", true, false);
						p.setInvulnerable(false);
					}
				} else if (args.length == 1) {
					if (p.hasPermission("Helpy.god.others")) {
						final Player t = Bukkit.getPlayer(args[0]);
						if (t != null && t != p) {
							if (!God.iva.contains(t.getName())) {
								God.iva.add(t.getName());
								t.setInvulnerable(true);
								Eng.atChange(p, "action", "§bInvulnerability", true, "false", "true", true, true);
								Eng.atChange(t, "chat", "§bInvulnerability", true, "false", "ture", true, false);
							} else if (God.iva.contains(t.getName())) {
								God.iva.remove(t.getName());
								t.setInvulnerable(false);
								Eng.atChange(p, "action", "§bInvulnerability", true, "true", "false", true, true);
								Eng.atChange(t, "chat", "§bInvulnerability", true, "true", "false", true, false);
							}
						} else
							sender.sendMessage(Eng.CMD_TARGET_NOT_EXI_POSSIBLY);
					}
				} else {
					sender.sendMessage(Eng.CMD_ARGS_LENGHT);
					Eng.argsUsage(sender, "/god <player name>", true);
				}
			} else
				Eng.permissionShow(sender, "Helpy.god.self");

		} else
			sender.sendMessage(Eng.CMD_NOT_PLAYER);
		return false;
	}

	static {
		God.iva = new ArrayList<String>();
	}
}
