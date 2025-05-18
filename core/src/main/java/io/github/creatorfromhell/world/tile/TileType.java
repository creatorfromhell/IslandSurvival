package io.github.creatorfromhell.world.tile;


/**
 * TileType
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public enum TileType {
  GRASS(0),
  WATER(1),
  COAST(2),
  SAND(3),
  SNOW(4),
  FOREST_GRASS(5);

  final int id;

  TileType(final int id) {

    this.id = id;
  }


  public int id() {

    return id;
  }
}
