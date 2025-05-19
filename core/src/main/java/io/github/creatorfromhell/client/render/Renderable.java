package io.github.creatorfromhell.client.render;
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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Interface representing an object that can be rendered on the screen.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Renderable {

  /**
   * This method is used to create a new instance of an object. It should be implemented in classes that require initialization.
   */
  void create();

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  void render(SpriteBatch batch);

  /**
   * Disposes of any resources held by the object implementing this method.
   * This method should be called when the object is no longer needed to release any resources it may hold.
   */
  void dispose();
}
