package io.github.creatorfromhell.world.generator;

import io.github.creatorfromhell.registry.TileTypeRegistry;
import io.github.creatorfromhell.util.location.Location;
import io.github.creatorfromhell.world.biome.Biome;
import io.github.creatorfromhell.world.tile.Tile;

/**
 * WorldGenerator
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class WorldGenerator {

  private final TerrainGenerator terrainGen;
  private final BiomeGenerator biomeGen;
  private final float seaLevel;      // height threshold for water
  private final float mountainLevel; // height threshold for mountain tops (optional)

  public WorldGenerator(final long seed) {
    // Configure terrain noise and biome generation
    //original values: seed, 0.01, 4, 0.5
    //this.terrainGen = new TerrainGenerator(seed, 0.008, 4, 0.5);
    this.biomeGen = new BiomeGenerator(seed + 42);
    this.terrainGen = new TerrainGenerator(seed, 0.0035, 4, 0.5);
    // Lower sea level = more land exposed
    this.seaLevel = -0.2f;
    this.mountainLevel = 0.8f;  // example threshold for mountains
  }

  public Tile getTileType(final Location location) {

    return getTileType(location.x(), location.y());
  }

  /**
   * Classify the tile at world coordinate (x,y) into a TileType for rendering.
   */
  public Tile getTileType(final int x, final int y) {
    //final float height = terrainGen.getHeight(x, y);
    // if (height < seaLevel) {
    //    return TileType.WATER;
    //}
    final float height = terrainGen.getHeight(x, y);
    final float river = terrainGen.getRiver(x, y);

    if(height < seaLevel - 0.02f) {
      return TileTypeRegistry.get("core:water");
    } else if(river > 0.9f && height > seaLevel + 0.02f) {
      return TileTypeRegistry.get("core:water");
    } else if(height < seaLevel + 0.02f) {
      if(river < 0.9f) {
        return TileTypeRegistry.get("core:coast");
      } else {
        return TileTypeRegistry.get("core:water");
      }
    }
    // Land tile:
    final Biome biome = biomeGen.getBiomeAt(x, y);
    final Tile tile = TileTypeRegistry.get(biome.surfaceTile());

    if (tile == null) {
      System.err.println("Unknown tile type for biome: " + biome.id() + " -> " + biome.surfaceTile());
      return TileTypeRegistry.get("core:grass"); // fallback
    }

    return tile;
  }

  public Biome getBiome(final Location location) {

    return biomeGen.getBiomeAt(location.x(), location.y());
  }

  public Biome getBiome(final int x, final int y) {

    return biomeGen.getBiomeAt(x, y);
  }

  public TerrainGenerator terrainGenerator() {

    return terrainGen;
  }
}
