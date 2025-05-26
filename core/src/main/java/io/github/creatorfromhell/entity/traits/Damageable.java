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
import org.jetbrains.annotations.Nullable;

/**
 * Damageable represents an {@link LivingEntity} that can be damaged.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Damageable {

  /**
   * Checks if the entity is alive.
   *
   * @return true if the entity is alive, false otherwise
   */
  boolean isAlive();

  /**
   * Inflicts damage on the entity with the specified amount and source of damage.
   *
   * @param damage The amount of damage to inflict on the entity as a float.
   * @param damageSource The entity responsible for the damage, can be null if not applicable.
   */
  void damage(float damage, @Nullable LivingEntity damageSource);

  /**
   * Kills the living entity, marking it as dead in the game world.
   *
   * @param killer The entity that caused the death of the living entity, can be null if death was not caused by another entity.
   */
  void kill(@Nullable LivingEntity killer);
}
