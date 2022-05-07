package de.tdf.helpy.methods.worldGenerator;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

public class VoidGenerator extends ChunkGenerator {

	/**
	 * 	OLD:
	 *
	 * 	public ChunkGenerator.@NotNull ChunkData generateChunkData(final @NotNull World world, final @NotNull Random random, final int x, final int z, final ChunkGenerator.@NotNull BiomeGrid biome) {
	 * 		return this.createChunkData(world);
	 * 	}
 	 */

	@SuppressWarnings("deprecation")
	@Override
	public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ,
	                                            @NotNull BiomeGrid biome) {

		ChunkData chunkData = super.createChunkData(world);
		for (int x = 0; x < 16; x++)
			for (int z = 0; z < 16; z++)
				biome.setBiome(x, z, Biome.PLAINS);

		return chunkData;
	}

}
