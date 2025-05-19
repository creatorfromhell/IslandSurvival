package io.github.creatorfromhell.client.render.ui;
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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.world.biome.Biome;
import io.github.creatorfromhell.world.tile.Tile;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;

/**
 * WorldComponent
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class WorldComponent implements Renderable {

  /**
   * Creates a new UI component. This method is used to initialize a new instance of the UI
   * component.
   */
  @Override
  public void create() {

  }

  /**
   * Renders the UI component using the provided SpriteBatch.
   *
   * @param batch the SpriteBatch used to render the UI component
   */
  @Override
  public void render(final SpriteBatch batch) {

    final Player player = GameManager.instance().player();

    final int minX = (int)((player.location().x() - (Gdx.graphics.getWidth() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) - 2;
    final int minY = (int)((player.location().y() - (Gdx.graphics.getHeight() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) - 2;
    final int maxX = (int)((player.location().x() + (Gdx.graphics.getWidth() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) + 2;
    final int maxY = (int)((player.location().y() + (Gdx.graphics.getHeight() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) + 2;

    for(int x = minX; x <= maxX; x++) {
      for(int y = minY; y <= maxY; y++) {

        final Tile tile = GameManager.instance().worldGenerator().getTileType(x, y);
        final Biome biome = GameManager.instance().worldGenerator().getBiome(x, y);
        final float height = GameManager.instance().worldGenerator().terrainGenerator().getHeight(x, y);

        tile.renderer().render(batch, biome, tile, height, x, y);
      }
    }
  }

  /**
   * Releases any resources held by the UI component.
   */
  @Override
  public void dispose() {

  }
}
