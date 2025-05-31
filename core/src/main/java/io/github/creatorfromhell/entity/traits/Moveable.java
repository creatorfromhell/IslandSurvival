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

import io.github.creatorfromhell.entity.LivingEntity;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Moveable represents an {@link LivingEntity} that can move/be moved.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Moveable {

  /**
   * Moves the Moveable entity to the specified location.
   *
   * @param location The new Location to move the entity to.
   */
  void move(@NotNull Location location);

  /**
   * Checks if the entity is currently moving.
   *
   * @return true if the entity is in motion, false otherwise
   */
  boolean isMoving();

  /**
   * Sets the moving status of the Moveable entity.
   *
   * @param moving A boolean value indicating whether the entity is currently moving.
   */
  void setMoving(boolean moving);

  /**
   * Calculates and retrieves the speed of the Moveable entity.
   *
   * @return The speed value of the Moveable entity as a float.
   */
  float speed();

  /**
   * Sets the speed of the Moveable entity to the specified value.
   *
   * @param speed The new speed value to set for the Moveable entity as a float.
   */
  void speed(final float speed);

  /**
   * Increases the speed of the Moveable entity by the given amount.
   *
   * @param speed The amount by which to increase the speed of the entity as a float.
   */
  void accelerate(final float speed);

  /**
   * Decelerates the speed of the Moveable entity by the given amount.
   *
   * @param speed The amount by which to decelerate the speed of the entity as a float.
   */
  void decelerate(final float speed);
}
