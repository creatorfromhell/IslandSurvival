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
}
