package io.github.creatorfromhell.entity;
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

import io.github.creatorfromhell.entity.traits.Moveable;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

/**
 * LivingEntity
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public abstract class LivingEntity extends Entity implements Moveable {
  protected float speed = 150f;
  protected boolean moving = false;

  protected float maxHealth = 20.0f;
  protected float health = maxHealth;

  /**
   * Retrieves the maximum health value of the LivingEntity.
   *
   * @return The maximum health value of the LivingEntity as a float.
   */
  public float maxHealth() {

    return maxHealth;
  }

  /**
   * Sets the maximum health value of the LivingEntity.
   *
   * @param maxHealth The new maximum health value to set for the LivingEntity as a float.
   */
  public void maxHealth(final float maxHealth) {

    this.maxHealth = maxHealth;
  }

  /**
   * Retrieves the health value of the LivingEntity.
   *
   * @return The health value of the LivingEntity as a float.
   */
  public float health() {

    return health;
  }

  /**
   * Sets the health of the LivingEntity to the specified value.
   *
   * @param health The new health value to set for the LivingEntity as a float.
   */
  public void health(final float health) {

    this.health = health;
  }

  /**
   * Moves the Moveable entity to the specified location.
   *
   * @param location The new Location to move the entity to.
   */
  @Override
  public void move(@NotNull final Location location) {

    this.location = location;
  }

  /**
   * Checks if the entity is currently moving.
   *
   * @return true if the entity is in motion, false otherwise
   */
  @Override
  public boolean isMoving() {

    return moving;
  }

  /**
   * Sets the moving status of the Moveable entity.
   *
   * @param moving A boolean value indicating whether the entity is currently moving.
   */
  @Override
  public void setMoving(final boolean moving) {

    this.moving = moving;
  }

  /**
   * Calculates and retrieves the speed of the Moveable entity.
   *
   * @return The speed value of the Moveable entity as a float.
   */
  @Override
  public float speed() {

    return speed;
  }

  /**
   * Sets the speed of the Moveable entity to the specified value.
   *
   * @param speed The new speed value to set for the Moveable entity as a float.
   */
  @Override
  public void speed(final float speed) {

    this.speed = speed;
  }

  /**
   * Increases the speed of the Moveable entity by the given amount.
   *
   * @param speed The amount by which to increase the speed of the entity as a float.
   */
  @Override
  public void accelerate(final float speed) {

    this.speed += speed;
  }

  /**
   * Decelerates the speed of the Moveable entity by the given amount.
   *
   * @param speed The amount by which to decelerate the speed of the entity as a float.
   */
  @Override
  public void decelerate(final float speed) {

    this.speed -= speed;
  }
}
