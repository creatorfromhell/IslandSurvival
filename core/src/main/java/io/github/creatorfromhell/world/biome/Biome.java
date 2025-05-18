package io.github.creatorfromhell.world.biome;
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

import com.badlogic.gdx.graphics.Color;

/**
 * Biome
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public interface Biome {


  /**
   * Retrieves the id of the biome.
   *
   * @return The id of the biome as a String.
   */
  String id();

  /**
   * Returns the temperature value of the biome.
   *
   * @return The temperature value as a floating-point number.
   */
  float temperature();

  /**
   * Returns the moisture value of the biome.
   *
   * @return The moisture value of the biome as a floating-point number.
   */
  float moisture();

  /**
   * Returns the surface tile associated with this biome.
   *
   * @return The surface tile for the biome.
   */
  String surfaceTile();

  /**
   * Retrieves the subtiles associated with this biome.
   *
   * @return Subtiles for the biome as a String.
   */
  String subTile();

  /**
   * Retrieves the minimum threshold value for the biome.
   *
   * @return The minimum threshold value.
   */
  float minThreshold();

  /**
   * Gets the maximum threshold value for the biome.
   *
   * @return The maximum threshold value.
   */
  float maxThreshold();

  /**
   * Retrieves the base color of the biome.
   *
   * @return The base color of the biome.
   */
  Color baseColor();

  /**
   * Retrieves the base color of the biome.
   *
   * @return The base color of the biome.
   */
  Color waterColor();

  /**
   * Interpolates a biome color based on height (adds green with altitude).
   *
   * @param height The height value to determine the tint color for.
   * @return The tint color based on the provided height.
   */
  default Color tintGrass(final float height) {

    final float mix = Math.min(Math.max(height + 0.2f, 0f), 1f); //clamp height

    //Blend green by elevation
    return baseColor().cpy().lerp(0.2f, 0.8f, 0.2f, 1f, mix);
  }

  /**
   * Interpolates the water color based on the provided height value.
   *
   * @param height The height value to determine the tint color for. Expected range: [-∞, ∞]
   * @return The tinted color based on the provided height. The resulting color is a blend of green based on the elevation.
   */
  default Color tintWater(final float height) {
    final float mix = Math.min(Math.max(height + 0.1f, 0f), 1f);
    return waterColor().cpy().lerp(waterColor().cpy().mul(0.8f), mix);
  }
}
