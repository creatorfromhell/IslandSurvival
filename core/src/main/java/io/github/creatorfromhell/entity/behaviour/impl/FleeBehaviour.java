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

/**
 * FleeBehaviour
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class FleeBehaviour implements Behaviour {

  private final Location threatPosition;

  public FleeBehaviour(final Location threatPosition) {
    this.threatPosition = threatPosition;
  }

  @Override
  public void update(final LivingEntity entity, final float delta) {
    if(!(entity instanceof final Moveable prey)) return;

    final Location current = entity.location();
    float dx = current.x() - threatPosition.x();
    float dy = current.y() - threatPosition.y();
    final float length = (float) Math.sqrt(dx * dx + dy * dy);

    if(length != 0) {
      dx /= length;
      dy /= length;
    }

    final float speed = prey.speed();
    final float newX = current.x() + dx * speed * delta;
    final float newY = current.y() + dy * speed * delta;

    entity.move(new Location((int)newX, (int)newY));
  }
}
