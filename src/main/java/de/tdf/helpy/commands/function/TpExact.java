package de.tdf.helpy.commands.function;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;

public class TpExact implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		if (sen instanceof LivingEntity) {
			final LivingEntity e = (LivingEntity) sen;
			if (e.hasPermission("Helpy.exactTp")) {
				Location loc;
				if (args.length != 0) {
					sen.sendMessage(Eng.CMD_ARGS_LENGHT);
					Eng.argsUsage(sen, "/TpExact [<Use Yaw & Pitch - boolean>]", true);
					return true;
				}
				loc = new Location(e.getWorld(), e.getLocation().getBlockX(), e.getLocation().getBlockY(), e.getLocation().getBlockZ());
				loc.setPitch((float) Math.round(loc.getPitch()));
				loc.setYaw((float) Math.round(loc.getYaw()));
				loc.add(0.5, 0.0, 0.5);
				e.teleport(loc);
				sen.sendMessage(Eng.PRE + "§7You were §asuccesfully §9teleported§8.");
			} else
				sen.sendMessage(Eng.CMD_NOT_PLAYER);
		} else
			Eng.permissionShow(sen, "Helpy.exactTp");
		return false;
	}
}
