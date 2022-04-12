package de.tdf.helpy.methods;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class BroadcastLoop {

	static int current = 0;
	private static final String pre = Helpy.getPlugin().getConfig().getString("Broadcast.prefix");

	public static void startLoop() {

		FileConfiguration con = Helpy.getPlugin().getConfig();
		long period = con.getLong("broadcast.delayInSeconds");
		boolean order = con.getBoolean("broadcast.inOrder"),
				playersOnly = con.isSet("Broadcast.excludeConsole");

		List<String> bc = Helpy.broadcasts;

		System.out.println(Eng.PRE + String.format("The broadcast loop was started. Set delay: %s", period));

		Bukkit.getScheduler().scheduleSyncRepeatingTask(Helpy.getPlugin(), () -> {
			if (order) {

				broadcastType(bc.get(current), playersOnly);
				current++;
				if (current >= bc.size()) current = 0;
			} else {
				Random r = new Random();
				broadcastType(bc.get(r.nextInt(bc.size())), playersOnly);
			}

		}, 10 * 20, period * 20);
	}

	public static void broadcastType(String msg, boolean playersOnly) {
		if (playersOnly) {
			for (Player ap : Bukkit.getOnlinePlayers())
				ap.sendMessage(pre + msg);
		} else {
			Bukkit.broadcastMessage(pre + msg);
		}
	}
}
