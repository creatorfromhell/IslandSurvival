package io.github.creatorfromhell.world.generator;


import io.github.creatorfromhell.registry.BiomeRegistry;
import io.github.creatorfromhell.util.OpenSimplex2;
import io.github.creatorfromhell.world.biome.Biome;

/**
 * BiomeGenerator
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class BiomeGenerator {
  private final long seed;
  private final double scale;

  public BiomeGenerator(final long seed) {
    this(seed, 0.0005); // smoother, lower-frequency noise
  }

  public BiomeGenerator(final long seed, final double scale) {
    this.seed = seed;
    this.scale = scale;
  }

  public Biome getBiomeAt(final int x, final int y) {
    final double nx = x * scale;
    final double ny = y * scale;

    final float temperature = (float) ((OpenSimplex2.noise2(seed, nx, ny) + 1) / 2.0);
    final float moisture = (float) ((OpenSimplex2.noise2(seed + 42, nx, ny) + 1) / 2.0);

    Biome best = null;
    float bestDistance = Float.MAX_VALUE;

    for(final Biome biome : BiomeRegistry.getAllBiomes()) {
      final float tempDiff = temperature - biome.temperature();
      final float moistDiff = moisture - biome.moisture();
      final float dist = tempDiff * tempDiff + moistDiff * moistDiff;

      if(dist < bestDistance) {
        best = biome;
        bestDistance = dist;
      }
    }

    return best;
  }
}
