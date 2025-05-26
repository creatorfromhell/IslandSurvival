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

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Interface representing an animal that can be herded.
 * Implementing classes should provide functionality to join a herd led by another Herdable instance,
 * and to check if the animal is currently part of a herd.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Herdable {

  /**
   * Join a herd under the specified leader.
   *
   * @param leader the herd leader
   */
  void joinHerd(@NotNull Herdable leader);

  /**
   * Leave the current herd.
   */
  void leaveHerd();

  /**
   * Check if the entity is currently part of a herd.
   *
   * @return true if the entity is in a herd, false otherwise
   */
  boolean isInHerd();

  /**
   * Get the leader of the herd this entity belongs to.
   *
   * @return the herd leader or empty optional if not in a herd
   */
  Optional<Herdable> getHerdLeader();
}
