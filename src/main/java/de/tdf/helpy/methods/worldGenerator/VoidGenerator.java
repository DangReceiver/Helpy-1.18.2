package de.tdf.helpy.methods.worldGenerator;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class VoidGenerator extends ChunkGenerator {

	public ChunkGenerator.ChunkData generateChunkData(final World world, final Random random, final int x, final int z, final ChunkGenerator.BiomeGrid biome) {
		return this.createChunkData(world);
	}
}
