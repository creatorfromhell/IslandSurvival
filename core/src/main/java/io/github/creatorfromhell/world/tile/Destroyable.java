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

import io.github.creatorfromhell.inventory.Item;

import java.util.Optional;

/**
 * Destroyable
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Destroyable {

  /**
   * Checks if the object drops an item when destroyed.
   *
   * @return true if the object drops an item when destroyed, false otherwise.
   */
  boolean dropsItem();

  /**
   * Returns an Optional<Item> representing the item dropped by the Destroyable object.
   *
   * @return an Optional<Item> that may contain the item dropped by the object when destroyed.
   */
  Optional<Item> itemDrop();

  /**
   * Destroys the object and returns an Optional<Tile> representing the replacement tile that should be placed in its location.
   *
   * @return an Optional<Tile> that may contain the replacement tile to be placed when this object is destroyed.
   */
  Optional<Tile> destroyReplacement();
}
