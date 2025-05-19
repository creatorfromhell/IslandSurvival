package io.github.creatorfromhell.entity;


import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.util.location.Direction;
import io.github.creatorfromhell.util.location.Location;

import java.util.Optional;

/**
 * Entity
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class Entity {

  protected Location location = Location.ZERO;

  protected Direction direction = Direction.SOUTH;
  protected Renderable renderer = null;
  protected float speed = 150f;

  public Location location() {

    return location;
  }

  public void location(final Location location) {

    this.location = location;
  }

  public Direction direction() {

    return direction;
  }

  public void direction(final Direction direction) {

    this.direction = direction;
  }

  public Optional<Renderable> renderer() {

    return Optional.ofNullable(renderer);
  }

  public void renderer(final Renderable renderer) {

    this.renderer = renderer;
  }

  public float speed() {

    return speed;
  }

  public void speed(final float speed) {

    this.speed = speed;
  }

  public void accelerate(final float speed) {

    this.speed += speed;
  }

  public void decelerate(final float speed) {

    this.speed -= speed;
  }
}
