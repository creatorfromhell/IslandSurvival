package io.github.creatorfromhell.world.tile;
/*
 * IslandSurvival
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
 * TileData
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class WorldTileData {
  public final int x;
  public final int y;
  public final String biome;
  public final String tileType;

  public WorldTileData(final int x, final int y, final String biome, final String tileType) {
    this.x = x;
    this.y = y;
    this.biome = biome;
    this.tileType = tileType;
  }
}
