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
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.entity.behaviour.Behaviour;
import io.github.creatorfromhell.entity.behaviour.impl.FleeBehaviour;
import io.github.creatorfromhell.entity.traits.HerdLeader;
import io.github.creatorfromhell.entity.traits.Herdable;
import io.github.creatorfromhell.entity.traits.Predator;
import io.github.creatorfromhell.entity.traits.Prey;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

  private final Map<Class<? extends Predator>, Integer> predators = new HashMap<>();

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

    predators.put(Player.class, 5);
  }

  @Override
  public void update(final float delta) {

    //TODO: Fine tune the movement algorithms
    if(behavior != null) {

      behavior.update(this, delta);
    }

    final Optional<Predator> closePredator = nearPredator(location);
    if(closePredator.isPresent() && closePredator.get() instanceof final LivingEntity living) {

      float dx = internalX - living.location().x();
      float dy = internalY - living.location().y();
      final float len = (float) Math.sqrt(dx * dx + dy * dy);
      if(len > 0) {

        dx /= len;
        dy /= len;
      }

      //apply the movement speed, and make it faster since the entity is fleeing.
      internalX += dx * (speed + 70f) * delta;
      internalY += dy * (speed + 70f) * delta;

      //update the location and direction for the move if not the same tile
      final Location currentTile = location();
      final Location newLoc = new Location((int) internalX, (int) internalY);

      if(!newLoc.equals(currentTile)) {
        updateDirectionFromMove(currentTile, newLoc);
        move(newLoc);
      }
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
      if(wanderTime >= wanderDuration) {
        isPaused = true;
        pauseTime = 0;
      } else {
        internalX += dirX * speed * delta;
        internalY += dirY * speed * delta;

        //update the location and direction for the move if not the same tile
        final Location currentTile = location();
        final Location newLoc = new Location((int) internalX, (int) internalY);

        if(!newLoc.equals(currentTile)) {
          updateDirectionFromMove(currentTile, newLoc);
          move(newLoc);
        }
        this.moving = true;
      }
    }
  }

  @Override
  public void joinHerd(final @NotNull Herdable leader) {
    this.herdLeader = leader;
    if(leader instanceof final HerdLeader hl) {

      hl.getHerdMembers().add(this);
    }
  }

  @Override
  public void leaveHerd() {
    if(herdLeader instanceof final HerdLeader hl) {

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
    return herdMembers;
  }

  /**
   * Retrieves a map of predators and their associated flee distance.
   *
   * @return A map where the key is the class of the predator and the value is the distance which
   * the prey item will flee from the predator.
   */
  @Override
  public Map<Class<? extends Predator>, Integer> predators() {

    return predators;
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
