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

import io.github.creatorfromhell.entity.traits.HerdLeader;
import io.github.creatorfromhell.entity.traits.Herdable;
import io.github.creatorfromhell.entity.traits.Moveable;
import io.github.creatorfromhell.util.location.Location;

import java.util.Set;

/**
 * HerdManager
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class HerdManager {
  /**
   * Move an entire herd by delta values along the x and y axes.
   *
   * @param leader the herd leader
   * @param deltaX movement in x direction
   * @param deltaY movement in y direction
   */
  public void moveHerd(final Herdable leader, final float deltaX, final float deltaY) {
    if(!(leader instanceof final HerdLeader herdLeader) || !(leader instanceof final Moveable moveable)) return;

    // Move leader
    final Location current = moveable.location();
    moveable.move(new Location((int)(current.x() + deltaX), (int)(current.y() + deltaY)));

    // Move followers
    final Set<Herdable> members = herdLeader.getHerdMembers();
    for(final Herdable member : members) {

      if(member == leader || !(member instanceof final Moveable moveMember)) continue;

      final Location loc = moveMember.location();
      moveMember.move(new Location((int)(loc.x() + deltaX), (int)(loc.y() + deltaY)));
    }
  }
}
