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

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * MongoDatabaseManager
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class MongoDatabaseManager {

  private static final String WORLD_TILES_COLLECTION = "world_tiles";
  private static final String PLAYER_DATA_COLLECTION = "players";

  private static MongoClient client;
  private static MongoDatabase database;

  public static void connect() {
    client = MongoClients.create("mongodb://localhost:27017");
    database = client.getDatabase("survival_game");
  }

  public static MongoDatabase db() {
    if(client == null || database == null) {
      client = MongoClients.create("mongodb://localhost:27017");
      database = client.getDatabase("survival_game");
    }
    return database;
  }

  public static void close() {
    if(client != null) client.close();
  }

  public static void initialize() {
    final MongoDatabase db = db();

    // Ensure world_tiles collection exists
    if(!collectionExists(db, WORLD_TILES_COLLECTION)) {
      db.createCollection(WORLD_TILES_COLLECTION);
      System.out.println("[MongoInit] Created collection: world_tiles");
    }

    // Ensure players collection exists
    if(!collectionExists(db, PLAYER_DATA_COLLECTION)) {
      db.createCollection(PLAYER_DATA_COLLECTION);
      System.out.println("[MongoInit] Created collection: players");
    }

    // Add index to world_tiles if it doesn't exist
    final MongoCollection<Document> worldTiles = db.getCollection(WORLD_TILES_COLLECTION);
    worldTiles.createIndex(new Document("x", 1).append("y", 1)); // compound index on x + y

    // Add default player doc if needed
    final MongoCollection<Document> players = db.getCollection(PLAYER_DATA_COLLECTION);
    if(players.countDocuments(eq("uuid", "default")) == 0) {
      final Document defaultPlayer = new Document("uuid", "default")
        .append("name", "DefaultPlayer")
        .append("x", 0)
        .append("y", 0)
        .append("health", 100)
        .append("inventory", new Document());
      players.insertOne(defaultPlayer);
      System.out.println("[MongoInit] Inserted default player document.");
    }
  }

  private static boolean collectionExists(final MongoDatabase db, final String name) {
    for(final String s : db.listCollectionNames()) {
      if(s.equalsIgnoreCase(name)) return true;
    }
    return false;
  }
}
