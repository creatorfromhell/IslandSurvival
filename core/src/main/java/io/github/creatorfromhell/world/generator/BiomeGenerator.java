package io.github.creatorfromhell.world.generator;


import io.github.creatorfromhell.registry.BiomeRegistry;
import io.github.creatorfromhell.util.OpenSimplex2;
import io.github.creatorfromhell.world.biome.Biome;

import java.util.Random;

/**
 * BiomeGenerator
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class BiomeGenerator {

  private final long seed;

  public BiomeGenerator(final long seed) {
    this.seed = seed;
  }

  public Biome getBiomeAt(final int x, final int y) {
    final float temperature = (float) ((OpenSimplex2.noise2(seed, x * 0.001, y * 0.001) + 1) / 2.0); // [0, 1]
    final float moisture = (float) ((OpenSimplex2.noise2(seed + 42, x * 0.001, y * 0.001) + 1) / 2.0);   // [0, 1]

    Biome best = null;
    float bestDistance = Float.MAX_VALUE;

    for (final Biome biome : BiomeRegistry.getAllBiomes()) {
      final float tempDiff = temperature - biome.temperature();
      final float moistDiff = moisture - biome.moisture();
      final float dist = tempDiff * tempDiff + moistDiff * moistDiff;

      if (dist < bestDistance) {
        best = biome;
        bestDistance = dist;
      }
    }

    return best;
  }
}
