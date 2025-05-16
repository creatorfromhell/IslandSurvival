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

import io.github.creatorfromhell.world.tile.TileType;

/**
 * TileAutotiler
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class TileAutotiler {

    // 4-bit mask: Top = 1, Right = 2, Bottom = 4, Left = 8
    public static int getBitmask(final TileType[][] world, final int x, final int y) {
        final TileType center = world[x][y];
        int mask = 0;

        if (y < world[0].length - 1 && world[x][y + 1] == center) mask |= 1; // top
        if (x < world.length - 1 && world[x + 1][y] == center) mask |= 2;    // right
        if (y > 0 && world[x][y - 1] == center) mask |= 4;                   // bottom
        if (x > 0 && world[x - 1][y] == center) mask |= 8;                   // left

        return mask;
    }

    /**
     * For a given bitmask, return the tile index in the autotile sprite sheet.
     * You can map this to an array of TextureRegion[16] representing the 4-bit combinations.
     */
    public static int resolveTileIndex(final int bitmask) {
        return bitmask;
    }
}
