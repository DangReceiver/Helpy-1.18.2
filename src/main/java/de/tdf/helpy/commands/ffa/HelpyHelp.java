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
		sen.sendMessage(Eng.PRE + "§3Author§8: §bTearsDontFall");
		sen.sendMessage(Eng.PRE + "§1Plugin §9version§8: §b" + Helpy.VERSION);
		sen.sendMessage(Eng.PRE + "§5§oStable §9version§8: §d" + Helpy.stable);
		if (Bukkit.getServer().getVersion().contains("1.16") && sen instanceof Player)
			sen.sendMessage(Eng.PRE + "Your §aPing§8: §2" + ((Player) sen).getPing());
		sen.sendMessage(Eng.PRE + "§6RAM §7usage§8: §e" + l + "§6MB §8§l/ §4" + r.maxMemory() / s + "§6MB §8§l| §a" + r.freeMemory() / s + "§6MB §7free§8.");
		sen.sendMessage("§0 \n §0 ");
		return true;
	}
}
