package de.tdf.helpy.commands.ffa;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hunger implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		if (sen instanceof Player) {
			final Player p = (Player) sen;
			if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("help"))) {
				p.sendMessage(Eng.CMD_ARGS_LENGHT);
				Eng.argsUsage(sen, "/hunger [<s>]<0-20>", true);
			} else if (args.length == 1) {
				boolean s = false;
				String in = args[0];
				if (args[0].contains("s")) {
					s = true;
					final String[] s2 = args[0].split("s");
					in = s2[1];
				}
				try {
					final int i = Integer.parseInt(in);
					if (s)
						if (i <= p.getSaturation())
							p.setSaturation((float) i);
						else if (p.hasPermission("FA.bypass.hungerLimitation"))
							p.setSaturation((float) i);
						else
							Eng.permissionShow(sen, "FA.bypass.hungerLimitation");
					else if (i <= p.getFoodLevel())
						p.setFoodLevel(i);
					else if (p.hasPermission("FA.bypass.hungerLimitation"))
						p.setFoodLevel(i);
					else
						Eng.permissionShow(sen, "FA.bypass.hungerLimitation");
				} catch (NumberFormatException ignored) {
				}
			} else {
				p.sendMessage(Eng.CMD_ARGS_LENGHT);
				Eng.argsUsage(sen, "/hunger [<s>]<0-20>", true);
			}
		} else
			sen.sendMessage(Eng.CMD_NOT_PLAYER);
		return false;
	}
}
