package io.github.creatorfromhell.util.location;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import io.github.creatorfromhell.GameManager;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;

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

  public Location asTile() {

    return new Location(this.x / TILE_SIZE, this.y / TILE_SIZE);
  }

  @Override
  public String toString() {

    return "Location{" +
           "x=" + x +
           ", y=" + y +
           '}';
  }

  /**
   * Converts the current mouse position to tile coordinates.
   *
   * @return Location object representing the tile coordinates (tileX, tileY)
   */
  public static Location fromMouse() {

    // Get mouse screen position and unproject to world coordinates
    final Vector3 worldCoords = GameManager.instance().player().cameraController().camera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

    final int tileX = (int) Math.floor(worldCoords.x / TILE_SIZE);
    final int tileY = (int) Math.floor(worldCoords.y / TILE_SIZE);

    return new Location(tileX, tileY);
  }
}
