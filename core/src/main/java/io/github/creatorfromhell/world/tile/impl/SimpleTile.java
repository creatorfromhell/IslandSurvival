package io.github.creatorfromhell.world.tile.impl;
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

import io.github.creatorfromhell.world.tile.Tile;
import io.github.creatorfromhell.world.tile.TileRenderer;

/**
 * SimpleTile
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class SimpleTile implements Tile {

  private final String id;
  private final boolean solid;
  private final TileRenderer renderer;
  private final boolean tintable;

  public SimpleTile(final String id, final boolean solid, final TileRenderer renderer, final boolean tintable) {

    this.id = id;
    this.solid = solid;
    this.renderer = renderer;
    this.tintable = tintable;
  }

  /**
   * Retrieves the identifier of the object.
   *
   * @return The identifier of the object.
   */
  @Override
  public String id() {

    return id;
  }

  @Override
  public boolean solid() {

    return solid;
  }

  /**
   * Retrieves the renderer associated with the tile.
   *
   * @return The TileRenderer representing the visual appearance of the tile.
   */
  @Override
  public TileRenderer renderer() {

    return renderer;
  }

  /**
   * Indicates whether the object is tintable based on the biome.
   *
   * @return true if the object is tintable, false otherwise
   */
  @Override
  public boolean tintable() {

    return tintable;
  }
}
