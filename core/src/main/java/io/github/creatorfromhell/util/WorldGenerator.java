package io.github.creatorfromhell.util;


import io.github.creatorfromhell.world.Biome;
import io.github.creatorfromhell.world.tile.TileType;

/**
 * WorldGenerator
 *
 * @author creatorfromhell
 * @since 1.0.0.0
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
        this.biomeGen   = new BiomeGenerator(seed + 42, 128,  // 128-tile cell size for biomes
                                             new Biome[]{Biome.DESERT, Biome.PLAINS, Biome.TUNDRA, Biome.JUNGLE});
        this.terrainGen = new TerrainGenerator(seed, 0.0035, 4, 0.5);
        // Lower sea level = more land exposed
        this.seaLevel = -0.2f;
        this.mountainLevel = 0.8f;  // example threshold for mountains
    }

    /** Classify the tile at world coordinate (x,y) into a TileType for rendering. */
    public TileType getTileType(final int x, final int y) {
        //final float height = terrainGen.getHeight(x, y);
       // if (height < seaLevel) {
        //    return TileType.WATER;
        //}
        final float height = terrainGen.getHeight(x, y);
        final float river = terrainGen.getRiver(x, y);

        if (height < seaLevel - 0.02f) {
            return TileType.WATER;
        } else if (river > 0.9f && height > seaLevel + 0.02f) {
            return TileType.WATER; // River overwrites land
        } else if (height < seaLevel + 0.02f) {
            if(river < 0.9f) {
                return TileType.COAST;
            } else {
                return TileType.WATER;
            }
        }
        // Land tile:
        final Biome biome = biomeGen.getBiome(x, y);
        switch (biome) {
            case DESERT:
                return TileType.SAND;
            case TUNDRA:
                // Tundra could be snow or bare ground; here we use snow for visual contrast
                return TileType.SNOW;
            case JUNGLE:
                return TileType.FOREST_GRASS;
            default:
                return TileType.GRASS;
        }
    }

    public Biome getBiome(final int x, final int y) {

        return biomeGen.getBiome(x, y);
    }

    public TerrainGenerator terrainGenerator() {
        return terrainGen;
    }
}
