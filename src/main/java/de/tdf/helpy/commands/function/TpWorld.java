package de.tdf.helpy.commands.function;

import de.tdf.helpy.methods.lang.Eng;
import de.tdf.helpy.methods.worldGenerator.UseVoid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpWorld implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sen, Command cmd, String lab, String[] args) {

		if (!sen.hasPermission("Helpy.TpWorld")) {
			Eng.permissionShow(sen, "Helpy.TpWorld");
			return true;
		}

		if (sen instanceof Player p) {

			if (args.length <= 0) {
				p.sendMessage(Eng.CMD_ARGS_LENGHT);
				return true;
			}

			String s = args[0];

			for (int i = 1; i < args.length; i++) {
				s = s + "_" + args[i];
			}

			if (Bukkit.getWorld(s) != null) {
				p.teleport(new Location(Bukkit.getWorld(s), 0, 72, 0));
				p.playSound(p.getLocation(), Sound.ENTITY_SHULKER_SHOOT, 0.25f, 1.1f);
			} else {
				p.sendMessage(Eng.CMD_INVALID_WORLD_NAME);
			}
		}
		return false;
	}
}