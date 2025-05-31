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

import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.entity.RabbitRenderer;
import io.github.creatorfromhell.entity.LivingEntity;
import io.github.creatorfromhell.entity.behaviour.Behaviour;
import io.github.creatorfromhell.entity.behaviour.impl.FleeBehaviour;
import io.github.creatorfromhell.entity.behaviour.impl.WanderBehaviour;
import io.github.creatorfromhell.entity.traits.HerdLeader;
import io.github.creatorfromhell.entity.traits.Herdable;
import io.github.creatorfromhell.entity.traits.Moveable;
import io.github.creatorfromhell.entity.traits.Prey;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;

/**
 * Rabbit
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class Rabbit extends LivingEntity implements Prey, Herdable, HerdLeader {

  private Herdable herdLeader;
  private final Set<Herdable> herdMembers = new HashSet<>();
  private Behaviour behavior;
  private float speed = 50f;

  private float wanderTime = 0f;
  private float pauseTime = 0f;
  private float pauseDuration = 1.5f;
  private float wanderDuration = 2.5f;
  private boolean isPaused = true;

  private float internalX = 10;
  private float internalY = 10;
  private float dirX = 0;
  private float dirY = 0;

  private final Random random = new Random();

  public Rabbit() {

    this.renderable = new RabbitRenderer(id);

    this.location = new Location(10, 10);

    //this.behavior = new WanderBehaviour();
  }

  @Override
  public void update(final float delta) {
    if(behavior != null) {

      behavior.update(this, delta);
    }

    if(shouldFlee()) {

      final Location playerLoc = GameManager.instance().player().location();
      float dx = internalX - playerLoc.x();
      float dy = internalY - playerLoc.y();
      final float len = (float) Math.sqrt(dx * dx + dy * dy);
      if(len > 0) {

        dx /= len;
        dy /= len;
      }

      //apply the movement speed, and make it faster since the entity is fleeing.
      internalX += dx * (speed + 70f) * delta;
      internalY += dy * (speed + 70f) * delta;

      //update the location and direction for the move
      final Location newLoc = new Location((int) internalX, (int) internalY);
      updateDirectionFromMove(location(), newLoc);
      move(newLoc);

      this.moving = true;
      return;
    }

    if(isPaused) {

      pauseTime += delta;
      if(pauseTime >= pauseDuration) {
        isPaused = false;
        wanderTime = 0;

        // new random direction
        dirX = random.nextFloat() * 2f - 1f;
        dirY = random.nextFloat() * 2f - 1f;
        final float len = (float) Math.sqrt(dirX * dirX + dirY * dirY);
        if(len > 0) {

          dirX /= len;
          dirY /= len;
        }
        this.moving = false;
      }
    } else {
      wanderTime += delta;
      if (wanderTime >= wanderDuration) {
        isPaused = true;
        pauseTime = 0;
      } else {
        internalX += dirX * speed * delta;
        internalY += dirY * speed * delta;

        final Location newLoc = new Location((int) internalX, (int) internalY);
        updateDirectionFromMove(location(), newLoc);
        move(newLoc);
        this.moving = true;
      }
    }
  }

  private boolean shouldFlee() {

    // Convert rabbit's internal float position to tile coordinates
    final int rabbitTileX = (int) (internalX / TILE_SIZE);
    final int rabbitTileY = (int) (internalY / TILE_SIZE);

    // Convert player location to tile coordinates using built-in asTile()
    final Location playerTile = GameManager.instance().player().location().asTile();

    final int dx = playerTile.x() - rabbitTileX;
    final int dy = playerTile.y() - rabbitTileY;
    final float distance = (float) Math.sqrt(dx * dx + dy * dy);

    return distance <= 10;
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
