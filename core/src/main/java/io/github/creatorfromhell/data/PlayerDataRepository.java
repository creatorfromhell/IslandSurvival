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
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.util.location.Location;
import org.bson.Document;

/**
 * PlayerDataRepository
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class PlayerDataRepository {

  private final MongoCollection<Document> players;

  public PlayerDataRepository() {
    this.players = MongoDatabaseManager.db().getCollection("players");
  }

  public void save(final Player player) {
    final Document doc = new Document("_id", player.identifier().toString())
      .append("x", player.location().x())
      .append("y", player.location().y())
      .append("health", player.health());

    players.replaceOne(
      Filters.eq("_id", player.identifier().toString()),
      doc,
      new ReplaceOptions().upsert(true)
                      );
  }

  public void load(final Player player) {
    final Document doc = players.find(Filters.eq("_id", player.identifier().toString())).first();
    if(doc != null) {
      final int x = doc.getInteger("x", 0);
      final int y = doc.getInteger("y", 0);
      final double health = doc.getDouble("health");

      player.move(new Location(x, y));
      player.health(Float.parseFloat(health + ""));
    }
  }
}
