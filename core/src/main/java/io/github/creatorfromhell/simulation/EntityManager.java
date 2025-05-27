package io.github.creatorfromhell.simulation;
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

import io.github.creatorfromhell.entity.Entity;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.entity.creatures.Rabbit;

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

    entities.put(player.identifier(), player);

    final Rabbit rabbit = new Rabbit();
    entities.put(rabbit.identifier(), rabbit);
  }

  public Map<UUID, Entity> entities() {

    return entities;
  }

  /**
   * Call update on all managed entities.
   *
   * @param delta time since last frame
   */
  public void updateAll(final float delta) {
    for(final Entity entity : entities.values()) {

      entity.update(delta);
    }
  }

  public Player player() {

    return player;
  }
}
