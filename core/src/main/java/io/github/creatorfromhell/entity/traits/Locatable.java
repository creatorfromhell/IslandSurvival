package io.github.creatorfromhell.entity.traits;
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

import io.github.creatorfromhell.util.location.Direction;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Locatable
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Locatable {

  /**
   * Retrieves the direction in which the Moveable entity is facing or moving.
   *
   * @return The direction the Moveable entity is facing/moving, represented by the Direction enum.
   */
  Direction direction();

  /**
   * Changes the direction that the Moveable entity is facing or moving.
   *
   * @param direction The new direction for the Moveable entity, represented by the Direction enum.
   */
  void face(@NotNull Direction direction);

  /**
   * Retrieves the location of the entity.
   *
   * @return The Location object representing the current coordinates (x, y).
   */
  Location location();

  /**
   * Updates the direction of the entity based on the move from one location to another.
   *
   * @param from The starting Location object.
   * @param to The target Location object to move to.
   */
  default void updateDirectionFromMove(final Location from, final Location to) {
    final int dx = to.x() - from.x();
    final int dy = to.y() - from.y();

    if(dx == 0 && dy == 0) return; // no movement

    if(Math.abs(dx) >= Math.abs(dy)) {
      face((dx > 0)? Direction.EAST : Direction.WEST);
    } else {
      face((dy > 0)? Direction.NORTH : Direction.SOUTH);
    }
  }
}
