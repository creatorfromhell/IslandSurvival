package io.github.creatorfromhell.entity.behaviour.impl;
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

import io.github.creatorfromhell.entity.LivingEntity;
import io.github.creatorfromhell.entity.behaviour.Behaviour;
import io.github.creatorfromhell.entity.traits.Moveable;
import io.github.creatorfromhell.util.location.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * WanderBehaviour
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class WanderBehaviour implements Behaviour {

  private static final float CHANGE_INTERVAL = 1.5f;

  private final Random random = new Random();
  private final Map<UUID, Float[]> positionCache = new HashMap<>();
  private final Map<UUID, Float> directionXMap = new HashMap<>();
  private final Map<UUID, Float> directionYMap = new HashMap<>();
  private final Map<UUID, Float> accumulatorMap = new HashMap<>();

  @Override
  public void update(final LivingEntity entity, final float delta) {
    if(!(entity instanceof final Moveable moveable)) return;

    final UUID id = entity.identifier();

    // Init cache
    positionCache.putIfAbsent(id, new Float[] {
      (float) entity.location().x(),
      (float) entity.location().y()
    });
    directionXMap.putIfAbsent(id, 0f);
    directionYMap.putIfAbsent(id, 0f);
    accumulatorMap.putIfAbsent(id, 0f);

    // Update timer
    float timer = accumulatorMap.get(id) + delta;
    if(timer >= CHANGE_INTERVAL) {

      float dx = random.nextFloat() * 2f - 1f;
      float dy = random.nextFloat() * 2f - 1f;
      final float len = (float) Math.sqrt(dx * dx + dy * dy);
      if(len != 0) {

        dx /= len;
        dy /= len;
      }
      directionXMap.put(id, dx);
      directionYMap.put(id, dy);
      timer = 0;
    }
    accumulatorMap.put(id, timer);

    // Move float position
    final Float[] pos = positionCache.get(id);
    final float dx = directionXMap.get(id) * moveable.speed() * delta;
    final float dy = directionYMap.get(id) * moveable.speed() * delta;
    pos[0] += dx;
    pos[1] += dy;

    // Commit int-cast location
    entity.move(new Location((int)pos[0].floatValue(), (int)pos[1].floatValue()));
  }
}
