package io.github.creatorfromhell.registry;
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

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.world.tile.Tile;
import io.github.creatorfromhell.world.tile.impl.SimpleTile;
import io.github.creatorfromhell.world.tile.impl.SimpleTileRenderer;

import java.util.HashMap;
import java.util.Map;

/**
 * TileTypeRegistry
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class TileTypeRegistry {

  public static final int TILE_SIZE = 16;

  private static final Map<String, Tile> REGISTRY = new HashMap<>();

  /**
   * Registers a {@link Tile} in the tile map.
   *
   * @param tile The tile object to be registered.
   */
  public static void register(final Tile tile) {
    REGISTRY.put(tile.id(), tile);
  }

  /**
   * Retrieves the {@link Tile} object associated with the provided id.
   *
   * @param id The identifier of the Tile object to retrieve.
   * @return The Tile object corresponding to the provided id, or null if not found.
   */
  public static Tile get(final String id) {
    return REGISTRY.get(id);
  }

  /**
   * Checks if a {@link Tile} with the specified id exists in the tile map.
   *
   * @param id The identifier of the tile to check.
   * @return true if the tile exists in the map, false otherwise.
   */
  public static boolean exists(final String id) {
    return REGISTRY.containsKey(id);
  }

  public static void create() {

    final TextureRegion[][] split = TextureRegion.split(GameManager.instance().assetManager().tilesheet(), TILE_SIZE, TILE_SIZE);

    register(new SimpleTile("core:grass", false, new SimpleTileRenderer(split[0][2]), true));
    register(new SimpleTile("core:forest_grass", false, new SimpleTileRenderer(split[0][3]), true));
    register(new SimpleTile("core:sand", false, new SimpleTileRenderer(split[2][1]), false));
    register(new SimpleTile("core:snow", false, new SimpleTileRenderer(split[0][1]), false));
    register(new SimpleTile("core:ice", true, new SimpleTileRenderer(split[0][1]), false));
    register(new SimpleTile("core:dirt", false, new SimpleTileRenderer(split[0][0]), false));
    register(new SimpleTile("core:water", false, new SimpleTileRenderer(split[1][2]), false));
    register(new SimpleTile("core:lava", true, new SimpleTileRenderer(split[3][0]), false));
    register(new SimpleTile("core:stone", true, new SimpleTileRenderer(split[1][0]), false));
    register(new SimpleTile("core:coast", true, new SimpleTileRenderer(split[3][3]), false));
  }
}
