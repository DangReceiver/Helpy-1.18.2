package de.tdf.helpy.commands.function;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.Other;
import de.tdf.helpy.methods.lang.Eng;
import de.tdf.helpy.methods.pConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EcClear implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		if (sen instanceof Player) {
			if (sen.hasPermission("Helpy.clearEc")) {
				final Player p = (Player) sen;
				final pConfig pc = pConfig.loadConfig(p, "Helpy");
				if (args.length == 0) {
					p.sendMessage(Eng.CMD_ECCLEAR_CONFIRM_SELF);
					p.sendMessage(Eng.CMD_ECCLEAR_TYPE_TO_CONFIRM);
					pc.set("Temp.cmd.EcClear", p.getName());
				} else if (args.length == 1 && args[0].equalsIgnoreCase("Confirm")) {
					if (!pc.isSet("Temp.cmd.EcClear")) {
						p.sendMessage(Eng.ANY_INPUT_DELAY_EXPIRED);
						Eng.argsUsage(sen, "/EcClear <Name; Confirm>", true);
						return true;
					}

					final Player t = (Player) Other.testForValidPlayer(p, false, pc.getString("Temp.cmd.EcClear"), true);
					if (t == null) {
						p.sendMessage(Eng.PRE + Eng.CMD_NOT_PLAYER);
						return true;
					}

					pc.set("Temp.cmd.EcClear", null);
					t.getEnderChest().clear();
					if (t == p) {
						p.sendMessage(Eng.CMD_ECCLEAR_CONFIRM_SELF);
					} else {
						p.sendMessage(Eng.CMD_ECCLEAR_CONFIRM_OTHER);
					}
					t.sendMessage(Eng.CMD_ECCLEAR_ANNOUNCE_SUCCES_TO_TARGET);
				} else if (args.length == 1) {
					if (!p.hasPermission("Helpy.clearEc.others")) {
						Eng.permissionShow(sen, "Helpy.clearEc.others");
						return true;
					}
					Player t = (Player) Other.testForValidPlayer(p, false, args[0], true);
					pc.set("Temp.cmd.EcClear", t.getName());
					p.sendMessage(Eng.CMD_ECCLEAR_CONFIRM_OTHER + "+ t.getName() + ");
					p.sendMessage(Eng.CMD_ECCLEAR_TYPE_TO_CONFIRM);
					Bukkit.getScheduler().runTaskLaterAsynchronously(Helpy.getHelpy(), () -> {
						if (pc.getString("Temp.cmd.EcClear") != null) {
							pc.set("Temp.cmd.EcClear", null);
							p.sendMessage(Eng.ANY_INPUT_DELAY_EXPIRED);
						}
					}, 200L);
				} else {
					p.sendMessage(" §d#Args§8: §5");
					Eng.argsUsage(sen, "/EcClear <Name; Confirm>", true);
				}
			} else {
				Eng.permissionShow(sen, "Helpy.clearEc");
			}
		} else {
			sen.sendMessage(Eng.CMD_NOT_PLAYER);
		}
		return false;
	}
}
