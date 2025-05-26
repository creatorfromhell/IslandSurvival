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

/**
 * Predator represents an {@link LivingEntity} that is classified as a "predator" for at least one
 * {@link Prey} entity.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Predator {


  /**
   * Adds a prey to the predator with a specified priority.
   *
   * @param prey The LivingEntity to be added as prey.
   * @param priority The priority of the prey being added.
   */
  void addPrey(LivingEntity prey, int priority);

  /**
   * Removes a prey entity from this predator's list of prey.
   *
   * @param prey The LivingEntity to be removed as prey.
   */
  void removePrey(LivingEntity prey);
}
