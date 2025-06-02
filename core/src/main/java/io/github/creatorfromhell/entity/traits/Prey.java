package io.github.creatorfromhell.entity.traits;
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
import io.github.creatorfromhell.entity.Entity;
import io.github.creatorfromhell.entity.LivingEntity;
import io.github.creatorfromhell.util.location.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;

/**
 * Prey represents an {@link LivingEntity} that is classified as "prey" for at least one
 * {@link Predator} entity.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Prey {

  /**
   * Retrieves a map of predators and their associated flee distance.
   *
   * @return A map where the key is the class of the predator and the value is the distance which
   * the prey item will flee from the predator.
   */
  Map<Class<? extends Predator>, Integer> predators();

  /**
   * Triggers a flee response from the specified threat.
   *
   * @param threatLocation The location of the threat.
   */
  void fleeFrom(@NotNull Location threatLocation);


  default Optional<Predator> nearPredator(final Location location) {
    if(predators().isEmpty()) return Optional.empty();

    //Current tile location of this entity
    final Location preyTile = location.asTile();

    //Iterate over all entities in the world
    for(final Entity other : GameManager.instance().entityManager().entities().values()) {

      if(other == this || !(other instanceof final Predator predator)) continue;

      for(final Map.Entry<Class<? extends Predator>, Integer> entry : predators().entrySet()) {

        final Class<? extends Predator> predatorClass = entry.getKey();
        final int fleeDistance = entry.getValue();

        if(predatorClass.isAssignableFrom(other.getClass())) {
          final Location predatorTile = other.location().asTile();

          final int dx = predatorTile.x() - preyTile.x();
          final int dy = predatorTile.y() - preyTile.y();
          final float distance = (float) Math.sqrt(dx * dx + dy * dy);

          if(distance <= fleeDistance) {

            return Optional.of(predator); //A predator is close enough to flee from
          }
        }
      }
    }

    return Optional.empty(); //no predators detected
  }
}
