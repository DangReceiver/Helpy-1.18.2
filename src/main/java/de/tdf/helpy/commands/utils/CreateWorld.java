package de.tdf.helpy.commands.utils;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import de.tdf.helpy.methods.worldGenerator.UseVoid;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class CreateWorld implements CommandExecutor {

	boolean creation = false;
	int c = 0;

	@Override
	public boolean onCommand(CommandSender sen, Command cmd, String lab, String[] args) {

		if (!sen.hasPermission("Helpy.createVoidWorld")) {
			Eng.permissionShow(sen, "Helpy.creteVoidWorld");
			return true;
		}

		if (sen instanceof Player p) {

			if (args.length <= 1) {
				p.sendMessage(Eng.CMD_ARGS_LENGHT);
				return true;
			}

			String s = args[1];
			boolean b;

			try {
				b = Boolean.parseBoolean(args[0]);
			} catch (Exception e) {
				Eng.entryType(p, "0", "boolean");
				return true;
			}

			if (args.length > 2)
				for (int i = 2; i < args.length; i++) {
					s = s + "_" + args[i];
				}

			s = s.replace("true", "").replace("false", "");//.replace(args[1] + "_", "")

			if (s.equals("_") || s.contains("null")) {
				p.sendMessage(Eng.CMD_INVALID_WORLD_NAME);
				return true;
			}

			p.sendMessage(Eng.PRE + "Starting creation of the world " + s + " with bedrock: " + b);
			UseVoid.createVoidWorld(s, b);

			File file = new File("plugins/Helpy/Settings.yml");
			YamlConfiguration settings = YamlConfiguration.loadConfiguration(file);

			List<String> vWorlds = settings.getStringList("Helpy.voidWorlds");
			if (!vWorlds.contains(s))
				vWorlds.add(s);
			settings.set("Helpy.voidWorlds", vWorlds);
			Helpy.getHelpy().saveConfig();

			final String wName = s;
			checkCreationStatus(p, wName);
		}
		return false;
	}

	public void checkCreationStatus(Player p, String s) {

		Bukkit.getScheduler().runTaskLater(Helpy.getHelpy(), () -> {
			if (Bukkit.getWorld(s) != null) {
				p.sendMessage(Eng.PRE + "The world was created successfully!");
			} else {
				c++;
				if (c >= 15) {
					p.sendMessage(Eng.PRE + "World check timed out. The world was not created in time!");
					return;
				}
				checkCreationStatus(p, s);
			}
		}, 10);

	}
}
