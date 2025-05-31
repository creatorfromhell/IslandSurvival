package io.github.creatorfromhell.light;
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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.creatorfromhell.util.location.Location;

/**
 * LightSource Represents a radial light source positioned by tile or pixel coordinates.
 * Supports construction from a Location object.
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class LightSource {

  private final int x;
  private final int y;
  private final float radius;
  private final Texture texture;

  /**
   * Constructs a new LightSource with an integer position and radius.
   *
   * @param texture The texture used for the radial light.
   * @param x       The X coordinate (usually in pixels or tile space).
   * @param y       The Y coordinate (usually in pixels or tile space).
   * @param radius  The radius of the light effect.
   */
  public LightSource(final Texture texture, final int x, final int y, final float radius) {
    this.texture = texture;
    this.x = x;
    this.y = y;
    this.radius = radius;
  }

  /**
   * Constructs a new LightSource at a given Location.
   *
   * @param texture  The texture used for the radial light.
   * @param location The Location of the light source.
   * @param radius   The radius of the light effect.
   */
  public LightSource(final Texture texture, final Location location, final float radius) {
    this(texture, location.x(), location.y(), radius);
  }

  /**
   * Renders the light using additive blending.
   *
   * @param batch The SpriteBatch used to draw the texture.
   */
  public void render(final SpriteBatch batch) {
    final float size = radius * 2;
    batch.setColor(1f, 1f, 1f, 1f);
    batch.draw(texture, x - radius, y - radius, size, size);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public float getRadius() {
    return radius;
  }
}
