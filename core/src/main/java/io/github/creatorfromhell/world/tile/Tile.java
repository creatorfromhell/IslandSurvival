package io.github.creatorfromhell.world.tile;


/**
 * Tile
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Tile {

  /**
   * Retrieves the identifier of the object.
   *
   * @return The identifier of the object.
   */
  String id();

  boolean solid();

  /**
   * Retrieves the renderer associated with the tile.
   *
   * @return The TileRenderer representing the visual appearance of the tile.
   */
  TileRenderer renderer();

  /**
   * Indicates whether the object is tintable based on the biome.
   *
   * @return true if the object is tintable, false otherwise
   */
  default boolean tintable() {

    return false;
  }
}
