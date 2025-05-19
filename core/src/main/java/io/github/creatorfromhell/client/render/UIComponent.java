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
 * UIComponent represents a component for the UI, which will be used to render UI components.
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface UIComponent {

  /**
   * Creates a new UI component. This method is used to initialize a new instance of the UI component.
   */
  void create();

  /**
   * Renders the UI component using the provided SpriteBatch.
   *
   * @param batch the SpriteBatch used to render the UI component
   */
  void render(SpriteBatch batch);

  /**
   * Releases any resources held by the UI component.
   */
  void dispose();
}
