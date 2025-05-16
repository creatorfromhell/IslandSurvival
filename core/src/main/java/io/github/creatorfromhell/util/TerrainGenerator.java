package io.github.creatorfromhell.util;
/*
 * The New Economy
 * Copyright (C) 2025 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * TerrainGenerator
 *
 * @author creatorfromhell
 * @since 1.0.0.0
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

    /** Returns a height value for the given tile coordinate (x,y). */
    public float getHeight(final int x, final int y) {
        // Use fractal noise (fBm) by combining multiple octaves of OpenSimplex2
        float amplitude = 1f;
        float frequency = 0.05f;
        float noiseSum = 0f;
        float maxValue = 0f;
        for (int octave = 0; octave < octaves; octave++) {
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
