package io.github.creatorfromhell.entity;


import io.github.creatorfromhell.world.Location;

/**
 * Entity
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class Entity {

  protected Location location = Location.ZERO;
  protected float speed = 150f;

  public Location location() {

    return location;
  }

  public void location(final Location location) {

    this.location = location;
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
