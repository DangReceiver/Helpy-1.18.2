package de.tdf.helpy.commands.function;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		if (args.length >= 2) {
			boolean space = true;
			String s = "";
			if (args[0].equalsIgnoreCase("true")) {
				args[0].replace("true", "");
				space = true;
			} else if (args[0].equalsIgnoreCase("false")) {
				args[0].replace("false", "");
				space = false;
			}
			for (int i = 0; i < args.length; ++i)
				s = s + " " + args[i];
			s = s.replaceAll("&", "§").replaceAll("%%", "%").replaceAll("null", " ").replaceAll("  ", "");
			for (final Player ap : Bukkit.getOnlinePlayers()) {
				if (space) {
					ap.sendMessage(" §0 »");
					ap.sendMessage("§8§m§l------------------------§8§m§l------------------------");
					ap.sendMessage(" §0»");
				}
				ap.sendMessage(s);
				if (space) {
					ap.sendMessage(" §0» §0 ");
					ap.sendMessage("§8§m§l------------------------§8§m§l------------------------");
					ap.sendMessage("  §0» §0 ");
				}
			}
			System.out.println("Broadcast ''" + s + "'' was sent successfully. [Sender: " + sen.getName() + "]");
		} else
			Eng.argsUsage(sen, "/broadcast <Spacer: true/false> <Message>", true);
		return false;
	}
}
