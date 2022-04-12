package de.tdf.helpy.methods.generator.world;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.WorldCreator;

public class UseVoid {
	public static void createVoidWorld(final String name, boolean bedrockAtNull) {
		final WorldCreator worldCreator = new WorldCreator(name);
		worldCreator.generator((ChunkGenerator) new VoidGenerator());
		final World world = worldCreator.createWorld();
		if (bedrockAtNull) world.getBlockAt(0, 64, 0).setType(Material.BEDROCK);
	}
}
