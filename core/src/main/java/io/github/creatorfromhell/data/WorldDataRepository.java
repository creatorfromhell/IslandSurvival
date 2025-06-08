package io.github.creatorfromhell.data;
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

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.creatorfromhell.world.tile.WorldTileData;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * WorldDataRepository
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class WorldDataRepository {

  private final MongoCollection<Document> tiles;

  public WorldDataRepository() {
    final MongoDatabase db = MongoDatabaseManager.db();
    this.tiles = db.getCollection("world_tiles");
  }

  public void saveTile(final WorldTileData data) {
    final Document doc = new Document("x", data.x)
      .append("y", data.y)
      .append("biome", data.biome)
      .append("tileType", data.tileType);

    tiles.replaceOne(
      and(eq("x", data.x), eq("y", data.y)),
      doc,
      new com.mongodb.client.model.ReplaceOptions().upsert(true)
                    );
  }

  public List<WorldTileData> loadAllTiles() {
    final List<WorldTileData> result = new ArrayList<>();
    for(final Document doc : tiles.find()) {
      result.add(new WorldTileData(
        doc.getInteger("x"),
        doc.getInteger("y"),
        doc.getString("biome"),
        doc.getString("tileType")
      ));
    }
    return result;
  }
}
