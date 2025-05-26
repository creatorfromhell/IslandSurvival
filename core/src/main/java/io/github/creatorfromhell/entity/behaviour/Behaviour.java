package io.github.creatorfromhell.entity.behaviour;
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
 * Behaviour represents a behaviour action that an {@link LivingEntity} can do.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Behaviour {

  /**
   * Updates the given entity based on the specified time delta.
   *
   * @param entity the LivingEntity to be updated
   * @param delta the time elapsed since the last update
   */
  void update(LivingEntity entity, float delta);
}
