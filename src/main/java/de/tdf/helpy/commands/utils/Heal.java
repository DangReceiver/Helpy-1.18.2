package de.tdf.helpy.commands.utils;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender send, final Command cmd, final String label, final String[] args) {
		if (send.hasPermission("helpy.heal.self")) {
			if (args.length == 0) {
				if (send instanceof Player) {
					final Player p = (Player) send;
					p.setHealth(p.getMaxHealth());
					p.setFoodLevel(20);
					p.setSaturation(20.0f);
					p.setFireTicks(0);
					p.sendTitle(Eng.PRE, "§7You were §dhealed§8.", 5, 12, 20);
				} else
					send.sendMessage(Eng.CMD_NOT_PLAYER);
			} else if (args.length == 1) {
				final Player t = Bukkit.getPlayer(args[0]);
				if (send.hasPermission("helpy.heal.all")) {
					if (args[0].equals("*")) {
						for (final Player ap : Bukkit.getOnlinePlayers()) {
							ap.setHealth(ap.getMaxHealth());
							ap.setFoodLevel(20);
							ap.setSaturation(15.0f);
							ap.setFireTicks(0);
							ap.sendTitle(Eng.PRE, "§7You were §dhealed§8.", 5, 12, 20);
						}
						send.sendMessage(Eng.CMD_EVERYONE_HEALED);
					} else if (send.hasPermission("helpy.heal.others")) {
						if (t != null) {
							if (t == send) {
								send.sendMessage(Eng.CMD_EZ);
							} else {
								Eng.atChange(send, "chat", "§dhealth", true, t.getHealth() + "", "20", true, true);
								t.setHealth(t.getMaxHealth());
								t.setFoodLevel(20);
								t.setSaturation(15.0f);
								t.setFireTicks(0);
								t.sendTitle(Eng.PRE, "§7You were §dhealed§8.", 5, 12, 20);
							}
						} else
							send.sendMessage(Eng.CMD_TARGET_NOT_EXI_POSSIBLY);
					} else
						Eng.permissionShow(send, "helpy.heal.others");
				} else
					Eng.permissionShow(send, "helpy.heal.all");
			} else
				Eng.argsUsage(send, "/heal [<player name; *>]", true);
		} else
			Eng.permissionShow(send, "helpy.heal.self");
		return false;
	}
}
