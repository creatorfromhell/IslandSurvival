package io.github.creatorfromhell;
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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.Entity;
import io.github.creatorfromhell.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * EntityManager
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class EntityManager {

  protected final Map<UUID, Entity> entities = new HashMap<>();

  private final Player player;

  public EntityManager() {

    this.player = new Player();

    entities.put(UUID.randomUUID(), player);
  }

  public Map<UUID, Entity> entities() {

    return entities;
  }

  public Player player() {

    return player;
  }
}
