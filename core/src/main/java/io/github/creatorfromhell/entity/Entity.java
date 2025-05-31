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

import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.traits.Locatable;
import io.github.creatorfromhell.util.location.Direction;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

/**
 * Entity
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public abstract class Entity implements Locatable {

  protected UUID id = UUID.randomUUID();
  protected String type = "no-type";
  protected Renderable renderable = null;

  protected Location location = Location.ZERO;
  protected Direction direction = Direction.SOUTH;

  /**
   * This method returns a UUID that serves as the identifier for the implementing entity.
   *
   * @return a UUID representing the identifier of the entity
   */
  public UUID identifier() {

    return id;
  }

  /**
   * This method returns the type of the implementing entity.
   *
   * @return a String representing the type of the entity
   */
  public String type() {
    return type;
  }

  /**
   * This method generates a {@code Renderable} object that can be used to render the implementing entity on the screen.
   *
   * @return an {@code Optional} containing the {@code Renderable} object if it has been successfully created, or empty if an error occurred
   */
  public Optional<Renderable> renderer() {

    return Optional.ofNullable(renderable);
  }

  /**
   * This method is responsible for rendering the specified {@code Renderable} object on the screen.
   *
   * @param renderable the object that will be rendered on the screen
   */
  public void renderer(final Renderable renderable) {

    this.renderable = renderable;
  }

  /**
   * Retrieves the direction in which the Moveable entity is facing or moving.
   *
   * @return The direction the Moveable entity is facing/moving, represented by the Direction enum.
   */
  @Override
  public Direction direction() {

    return direction;
  }

  /**
   * Changes the direction that the Moveable entity is facing or moving.
   *
   * @param direction The new direction for the Moveable entity, represented by the Direction enum.
   */
  @Override
  public void face(@NotNull final Direction direction) {

    this.direction = direction;
  }

  /**
   * Retrieves the location of the entity.
   *
   * @return The Location object representing the current coordinates (x, y).
   */
  @Override
  public Location location() {

    return location;
  }

  /**
   * Update method called every game tick.
   *
   * @param delta time elapsed since last update (in seconds)
   */
  public abstract void update(float delta);
}
