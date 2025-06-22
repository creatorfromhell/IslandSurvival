package io.github.creatorfromhell.world.object;
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

import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.creatorfromhell.util.location.Location;

/**
 * WorldObject
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface WorldObject {

  /**
   * Get the location of this WorldObject.
   *
   * @return Location object representing the x and y coordinates of the object
   */
  Location location();

  /**
   * Get the type of this WorldObject.
   *
   * @return A string representing the type of the WorldObject
   */
  String type();

  /**
   * Render the WorldObject using the specified Batch.
   *
   * @param batch Batch object to render the WorldObject
   */
  void render(Batch batch);

  /**
   * Check if the object is interactable.
   *
   * @return true if the object can be interacted with, false otherwise
   */
  boolean isInteractable();
}
