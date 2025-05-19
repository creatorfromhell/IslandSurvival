package io.github.creatorfromhell.util.location;


/**
 * Location
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class Location {

  public static final Location ZERO = new Location(0, 0);

  private int x;
  private int y;

  public Location(final int x, final int y) {

    this.x = x;
    this.y = y;
  }

  public int x() {

    return x;
  }

  public void x(final int x) {

    this.x = x;
  }

  public int y() {

    return y;
  }

  public void y(final int y) {

    this.y = y;
  }

  public void addY(final int y) {

    this.y += y;
  }

  public void subY(final int y) {

    this.y -= y;
  }

  public void addX(final int x) {

    this.x += x;
  }

  public void subX(final int x) {

    this.x -= x;
  }
}
