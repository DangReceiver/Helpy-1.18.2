package de.tdf.helpy.methods;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Random;

public class BroadcastLoop {

	static int current = 0;

	public static void startLoop() {

		FileConfiguration con = Helpy.getPlugin().getConfig();
		long period = con.getLong("broadcast.delayInSeconds");
		boolean order = con.getBoolean("broadcast.inOrder");
		List<String> bc = Helpy.broadcasts;

		System.out.println(Eng.PRE + String.format("The broadcast loop was started. Set delay: %s", period));

		Bukkit.getScheduler().scheduleSyncRepeatingTask(Helpy.getPlugin(), () -> {
			if (order) {
				Bukkit.broadcastMessage(bc.get(current));
				current++;
				if (current >= bc.size()) current = 0;
			} else {
				Random r = new Random();
				Bukkit.broadcastMessage(bc.get(r.nextInt(bc.size())));
			}

		}, 10 * 20, period * 20);
	}
}
