package io.github.creatorfromhell.world.tile;
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
 * TileType
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public enum TileType {
    GRASS(0),
    WATER(1),
    COAST(2),
    SAND(3),
    SNOW(4),
    FOREST_GRASS(5);

    final int id;

    TileType(final int id) {
        this.id = id;
    }


    public int id() {
        return id;
    }
}
