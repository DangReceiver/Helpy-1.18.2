package de.tdf.helpy.commands.ffa;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpyHelp implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String label, final String[] args) {
		final Runtime r = Runtime.getRuntime();
		final int s = 1048576;
		final long l = r.maxMemory() / s - r.freeMemory() / s;
		sen.sendMessage("§0 \n §0 ");
		sen.sendMessage(Eng.PRE + "Author§8: §3TearsDontFall");
		sen.sendMessage(Eng.PRE + "Plugin version§8: §b" + Helpy.VERSION);
		sen.sendMessage(Eng.PRE + "Stable version§8: §d" + Helpy.stable);
		if ((Bukkit.getServer().getVersion().contains("1.16") || Bukkit.getServer().getVersion().contains("1.17") ||
				Bukkit.getServer().getVersion().contains("1.18")) && sen instanceof Player)
			sen.sendMessage(Eng.PRE + "Your Ping§8: §" + pingColor(((Player) sen)));
		sen.sendMessage(Eng.PRE + "§6RAM §7usage§8: §e" + l + " §7MB §8§l/ §c" + r.maxMemory() / s + " §7MB §8§l| §b" + r.freeMemory() / s + " §7MB free");
		sen.sendMessage("§0 \n §0 ");
		return true;
	}

	public static String pingColor(Player sen) {
		int ping = sen.getPing();
		String c = "4";

		if (ping <= 10)
			c = "b";
		else if (ping <= 20)
			c = "a";
		else if (ping <= 50)
			c = "e";
		else if (ping <= 100)
			c = "6";
		else if (ping <= 200)
			c = "c";

		return c + ping;
	}
}
