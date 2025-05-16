package io.github.creatorfromhell.util;


import io.github.creatorfromhell.world.Biome;

import java.util.Random;

/**
 * BiomeGenerator
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class BiomeGenerator {
    private final long worldSeed;
    private final int cellSize;
    private final Biome[] biomes;  // Array of possible biomes (land biomes)

    public BiomeGenerator(final long worldSeed, final int cellSize, final Biome[] biomes) {
        this.worldSeed = worldSeed;
        this.cellSize = cellSize;
        this.biomes = biomes;
    }

    /** Determine the biome at tile coordinate (x, y) using nearest Voronoi cell center. */
    public Biome getBiome(final int x, final int y) {
        // Identify which cell this tile is in
        // We use Math.floorDiv to handle negative coordinates correctly (floor toward -∞).
        final int cellX = Math.floorDiv(x, cellSize);
        final int cellY = Math.floorDiv(y, cellSize);

        Biome closestBiome = null;
        double closestDistSq = Double.MAX_VALUE;

        // Check this cell and neighboring cells for nearest center
        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                final int cx = cellX + offsetX;
                final int cy = cellY + offsetY;
                // Generate the pseudo-random center position and biome for cell (cx, cy)
                final long cellSeed = hashForCell(cx, cy);       // deterministic hash based on cell coords and worldSeed
                final Random rand = new Random(cellSeed);
                final double centerOffsetX = rand.nextDouble() * cellSize;
                final double centerOffsetY = rand.nextDouble() * cellSize;
                // Coordinates of this cell's biome center in world space
                final double centerX = cx * cellSize + centerOffsetX;
                final double centerY = cy * cellSize + centerOffsetY;
                // Compute squared distance from the tile to this center
                final double dx = centerX - x;
                final double dy = centerY - y;
                final double distSq = dx * dx + dy * dy;
                if (distSq < closestDistSq) {
                    closestDistSq = distSq;
                    // Determine biome type associated with this center
                    final int biomeIndex = rand.nextInt(biomes.length);
                    closestBiome = biomes[biomeIndex];
                }
            }
        }
        return closestBiome;
    }

    /** Hash function to get a deterministic seed for a given cell coordinate. */
    private long hashForCell(final int cx, final int cy) {
        // Use bit-mixing to create a pseudo-random 64-bit value from cell coords and worldSeed
        long hash = cx * 0x9E3779B97F4A7C15L ^ cy * 0xC2B2AE3D27D4EB4FL ^ worldSeed;
        // Scramble the bits a bit more
        hash ^= (hash >> 32);
        return hash;
    }
}
