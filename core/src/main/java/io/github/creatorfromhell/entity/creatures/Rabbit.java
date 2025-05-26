package io.github.creatorfromhell.entity.creatures;
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
import io.github.creatorfromhell.entity.behaviour.impl.FleeBehaviour;
import io.github.creatorfromhell.entity.traits.HerdLeader;
import io.github.creatorfromhell.entity.traits.Herdable;
import io.github.creatorfromhell.entity.traits.Moveable;
import io.github.creatorfromhell.entity.traits.Prey;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Rabbit
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class Rabbit extends LivingEntity implements Prey, Moveable, Herdable, HerdLeader {

  private Herdable herdLeader;
  private final Set<Herdable> herdMembers = new HashSet<>();
  private Behaviour behavior;
  private float speed = 30f;

  @Override
  public void update(final float delta) {
    if(behavior != null) {

      behavior.update(this, delta);
    }
  }

  @Override
  public void joinHerd(final Herdable leader) {
    this.herdLeader = leader;
    if (leader instanceof final HerdLeader hl) {
      hl.getHerdMembers().add(this);
    }
  }

  @Override
  public void leaveHerd() {
    if (herdLeader instanceof final HerdLeader hl) {
      hl.getHerdMembers().remove(this);
    }
    this.herdLeader = null;
  }

  @Override
  public boolean isInHerd() {
    return herdLeader != null;
  }

  @Override
  public Optional<Herdable> getHerdLeader() {
    return Optional.ofNullable(herdLeader);
  }

  @Override
  public Set<Herdable> getHerdMembers() {
    return Collections.unmodifiableSet(herdMembers);
  }

  @Override
  public void fleeFrom(final @NotNull Location threatLocation) {
    // Set fleeing behavior dynamically
    this.behavior = new FleeBehaviour(threatLocation);
  }

  @Override
  public float speed() {
    return speed;
  }
}
