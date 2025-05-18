package io.github.creatorfromhell.world.biome.impl;
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
import io.github.creatorfromhell.world.biome.Biome;

/**
 * SimpleBiome
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class SimpleBiome implements Biome {

  private final String id;
  private final float temperature;
  private final float moisture;
  private final String surface;
  private final String subLayer;
  private final Color baseColor;
  private final Color waterColor;
  private final float minThreshold;
  private final float maxThreshold;

  public SimpleBiome(final String id, final float temperature, final float moisture,
                     final String surface, final String subLayer, final Color baseColor,
                     final Color waterColor, final float minThreshold, final float maxThreshold) {

    this.id = id;
    this.temperature = temperature;
    this.moisture = moisture;
    this.surface = surface;
    this.subLayer = subLayer;
    this.baseColor = baseColor;
    this.waterColor = waterColor;
    this.minThreshold = minThreshold;
    this.maxThreshold = maxThreshold;
  }

  /**
   * Retrieves the id of the biome.
   *
   * @return The id of the biome as a String.
   */
  @Override
  public String id() {

    return id;
  }

  /**
   * Returns the temperature value of the biome.
   *
   * @return The temperature value as a floating-point number.
   */
  @Override
  public float temperature() {

    return temperature;
  }

  /**
   * Returns the moisture value of the biome.
   *
   * @return The moisture value of the biome as a floating-point number.
   */
  @Override
  public float moisture() {

    return moisture;
  }

  /**
   * Returns the surface tile associated with this biome.
   *
   * @return The surface tile for the biome.
   */
  @Override
  public String surfaceTile() {

    return surface;
  }

  /**
   * Retrieves the subtiles associated with this biome.
   *
   * @return Subtiles for the biome as a String.
   */
  @Override
  public String subTile() {

    return subLayer;
  }

  /**
   * Retrieves the minimum threshold value for the biome.
   *
   * @return The minimum threshold value.
   */
  @Override
  public float minThreshold() {

    return minThreshold;
  }

  /**
   * Gets the maximum threshold value for the biome.
   *
   * @return The maximum threshold value.
   */
  @Override
  public float maxThreshold() {

    return maxThreshold;
  }

  /**
   * Retrieves the base color of the biome.
   *
   * @return The base color of the biome.
   */
  @Override
  public Color baseColor() {

    return baseColor;
  }

  /**
   * Retrieves the base color of the biome.
   *
   * @return The base color of the biome.
   */
  @Override
  public Color waterColor() {

    return waterColor;
  }
}
