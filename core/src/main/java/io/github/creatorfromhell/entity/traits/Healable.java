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

import io.github.creatorfromhell.entity.Entity;
import io.github.creatorfromhell.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

/**
 * Healable represents an {@link LivingEntity} that can be healed.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Healable {

  /**
   * Heals the entity for the specified amount of health.
   *
   * @param health the amount of health to heal the entity by
   * @param healSource the entity that is the source of the healing (optional)
   * @return true if the healing is successful, false otherwise
   */
  boolean heal(float health, @Nullable Entity healSource);
}
