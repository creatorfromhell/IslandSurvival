package io.github.creatorfromhell.world.generator;


import io.github.creatorfromhell.util.OpenSimplex2;

/**
 * TerrainGenerator
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class TerrainGenerator {

  private final long seed;
  private final double scale;      // Controls size of features (lower = larger features)
  private final int octaves;
  private final double persistence; // amplitude multiplier for each successive octave

  public TerrainGenerator(final long seed, final double scale, final int octaves, final double persistence) {

    this.seed = seed;
    this.scale = scale;
    this.octaves = octaves;
    this.persistence = persistence;
  }

  /**
   * Returns a height value for the given tile coordinate (x,y).
   */
  public float getHeight(final int x, final int y) {
    // Use fractal noise (fBm) by combining multiple octaves of OpenSimplex2
    float amplitude = 1f;
    float frequency = 0.05f;
    float noiseSum = 0f;
    float maxValue = 0f;
    for(int octave = 0; octave < octaves; octave++) {
      // Sample noise at scaled coordinates. We divide by scale to enlarge features.
      final double nx = x * frequency * scale;
      final double ny = y * frequency * scale;
      noiseSum += OpenSimplex2.noise2(seed + octave, nx, ny) * amplitude;
      maxValue += amplitude;
      // Prepare next octave
      amplitude *= persistence;
      frequency *= 2.0;
    }
    // Normalize to [-1,1] (approximate) by dividing by maxValue of amplitudes
    final float heightValue = noiseSum / maxValue;
    return heightValue;
  }

  public float getRiver(final int x, final int y) {

    final double nx = x * scale * 0.5;
    final double ny = y * scale * 0.5;
    float noise = OpenSimplex2.noise2(seed + 9999, nx, ny); // different seed for river layer
    noise = Math.abs(noise); // symmetric around river center
    return 1.0f - Math.min(1.0f, noise * 5.0f); // sharpen the river edges
  }
}
