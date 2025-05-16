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

import com.badlogic.gdx.graphics.Color;
import io.github.creatorfromhell.world.Biome;

/**
 * BiomeColors
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */

public class BiomeColors {

    public static Color getBaseColor(final Biome biome) {
        return switch (biome) {
            case PLAINS -> new Color(0.6f, 0.9f, 0.4f, 1f);
            case TUNDRA -> new Color(0.8f, 0.8f, 0.8f, 1f);
            case JUNGLE -> new Color(0.2f, 0.6f, 0.2f, 1f);
            case DESERT -> new Color(0.8f, 0.7f, 0.4f, 1f);
            default -> Color.WHITE;
        };
    }

    /** Interpolates a biome color based on height (adds green with altitude). */
    public static Color getTintedColor(final Biome biome, final float height) {
        final Color base = getBaseColor(biome);
        final float mix = Math.min(Math.max(height + 0.2f, 0f), 1f); // clamp height
        // Blend green by elevation
        return base.cpy().lerp(0.2f, 0.8f, 0.2f, 1f, mix);
    }
}
