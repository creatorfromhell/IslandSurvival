package io.github.creatorfromhell.entity.creatures;
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
import io.github.creatorfromhell.entity.behaviour.Behaviour;
import io.github.creatorfromhell.entity.traits.Damageable;
import io.github.creatorfromhell.entity.traits.Moveable;
import io.github.creatorfromhell.entity.traits.Predator;
import io.github.creatorfromhell.entity.traits.Prey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Wolf
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class Wolf extends LivingEntity implements Predator, Moveable {

  private final boolean isDecoy;
  private Behaviour behavior;
  private float speed = 40f;

  public Wolf(final boolean isDecoy) {
    this.isDecoy = isDecoy;
  }

  @Override
  public void update(final float delta) {
    if(behavior != null) {
      behavior.update(this, delta);
    }
  }

  /**
   * Adds a prey to the predator with a specified priority.
   *
   * @param prey     The LivingEntity to be added as prey.
   * @param priority The priority of the prey being added.
   */
  @Override
  public void addPrey(@NotNull final LivingEntity prey, final int priority) {

  }

  /**
   * Removes a prey entity from this predator's list of prey.
   *
   * @param prey The LivingEntity to be removed as prey.
   */
  @Override
  public void removePrey(@NotNull final LivingEntity prey) {

  }

  @Override
  public boolean canHunt(final LivingEntity target) {
    return !isDecoy && target instanceof Prey;
  }

  @Override
  public void hunt(final LivingEntity target) {
    if(target instanceof final Prey prey) {

      // Trigger the prey to flee from this wolf
      prey.fleeFrom(this.location());
    }
  }

  @Override
  public float speed() {
    return speed;
  }

  public boolean isDecoy() {
    return isDecoy;
  }

  public void setBehavior(final Behaviour behavior) {
    this.behavior = behavior;
  }
}
