package de.tdf.helpy.methods.worldGenerator;

import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;

public class UseVoid {

	/**
	 * OLD:
	 * <p>
	 * public static void createVoidWorld(final String name, boolean bedrockAtNull) {
	 * final WorldCreator worldCreator = new WorldCreator(name);
	 * worldCreator.generator(new VoidGenerator());
	 * final World world = worldCreator.createWorld();
	 * <p>
	 * if (bedrockAtNull && world != null)
	 * world.getBlockAt(0, 64, 0).setType(Material.BEDROCK);
	 * }
	 */

	public static void createSteams(String name) {
		if (Bukkit.getWorld(name) == null) {
			WorldCreator creator = new WorldCreator(name);
			creator.generator(new VoidGenerator());
			creator.createWorld();
		} else {
			// Exists already
		}
	}

}
