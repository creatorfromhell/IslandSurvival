package io.github.creatorfromhell.world;


import io.github.creatorfromhell.data.WorldDataRepository;
import io.github.creatorfromhell.util.location.Location;
import io.github.creatorfromhell.world.tile.WorldTileData;

import java.util.HashMap;
import java.util.Map;

/**
 * World
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class World {

  private final Map<Location, String> biomeMap = new HashMap<>();
  private final Map<Location, String> tileTypeMap = new HashMap<>();
  private final WorldDataRepository repository = new WorldDataRepository();

  public void load() {
    for(final WorldTileData data : repository.loadAllTiles()) {

      final Location loc = new Location(data.x, data.y);
      biomeMap.put(loc, data.biome);
      tileTypeMap.put(loc, data.tileType);
    }
  }

  public String getBiome(final Location loc) {
    return biomeMap.get(loc);
  }

  public String getTileType(final Location loc) {
    return tileTypeMap.get(loc);
  }

  public void set(final Location loc, final String biome, final String tileType) {
    biomeMap.put(loc, biome);
    tileTypeMap.put(loc, tileType);
    repository.saveTile(new WorldTileData(loc.x(), loc.y(), biome, tileType));
  }

  public boolean hasTile(final Location loc) {
    return biomeMap.containsKey(loc) && tileTypeMap.containsKey(loc);
  }
}
