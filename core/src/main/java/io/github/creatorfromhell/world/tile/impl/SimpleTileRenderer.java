package io.github.creatorfromhell.world.tile.impl;
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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.creatorfromhell.world.biome.Biome;
import io.github.creatorfromhell.world.tile.Tile;
import io.github.creatorfromhell.world.tile.TileRenderer;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;

/**
 * SimpleTileRenderer
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class SimpleTileRenderer implements TileRenderer {

  private final TextureRegion textureRegion;

  public SimpleTileRenderer(final TextureRegion textureRegion) {

    this.textureRegion = textureRegion;
  }

  /**
   * Retrieves the {@link TextureRegion texture region} associated with the TileRenderer.
   *
   * @return The texture region representing the visual appearance of the TileRenderer element.
   */
  @Override
  public TextureRegion textureRegion() {

    return textureRegion;
  }

  /**
   * Renders the visual appearance of a tile at the specified position.
   *
   * @param batch The sprite batch used for rendering.
   * @param biome The biome associated with the tile to be rendered.
   * @param height The height of the tile to be rendered.
   * @param x     The x-coordinate of the tile position.
   * @param y     The y-coordinate of the tile position.
   */
  @Override
  public void render(final SpriteBatch batch, final Biome biome, final Tile tile, final float height, final int x, final int y) {
    batch.setColor(Color.WHITE);
    if(tile.tintable()) {
      if(tile.id().equalsIgnoreCase("core:water")) {
        batch.setColor(biome.tintWater(height));
      } else {
        batch.setColor(biome.tintGrass(height));
      }
    } else {
      batch.setColor(Color.WHITE);
    }

    batch.draw(textureRegion, x * TILE_SIZE, y * TILE_SIZE);
  }
}
