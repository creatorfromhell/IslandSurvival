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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.world.biome.Biome;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;

/**
 * BiomeBordersComponent
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class BiomeBordersComponent implements UIComponent {

  private BitmapFont font;

  /**
   * Creates a new UI component. This method is used to initialize a new instance of the UI
   * component.
   */
  @Override
  public void create() {

    font = new BitmapFont();
  }

  /**
   * Renders the UI component using the provided SpriteBatch.
   *
   * @param batch the SpriteBatch used to render the UI component
   */
  @Override
  public void render(final SpriteBatch batch) {

    final Player player = GameManager.instance().player();

    if(player.debug()) {
      //reset batch color
      batch.setColor(Color.WHITE);

      final int minX = (int)((player.location().x() - (Gdx.graphics.getWidth() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) - 2;
      final int minY = (int)((player.location().y() - (Gdx.graphics.getHeight() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) - 2;
      final int maxX = (int)((player.location().x() + (Gdx.graphics.getWidth() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) + 2;
      final int maxY = (int)((player.location().y() + (Gdx.graphics.getHeight() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) + 2;

      // Draw biome borders (by comparing neighboring biome cells)
      for(int x = minX; x <= maxX; x++) {
        for(int y = minY; y <= maxY; y++) {
          final Biome current = GameManager.instance().worldGenerator().getBiome(x, y);
          boolean border = false;
          if(GameManager.instance().worldGenerator().getBiome(x + 1, y) != current) border = true;
          if(GameManager.instance().worldGenerator().getBiome(x - 1, y) != current) border = true;
          if(GameManager.instance().worldGenerator().getBiome(x, y + 1) != current) border = true;
          if(GameManager.instance().worldGenerator().getBiome(x, y - 1) != current) border = true;

          if(border) {
            font.setColor(Color.BLACK);
            font.draw(batch, "#", x * TILE_SIZE + TILE_SIZE / 4f, y * TILE_SIZE + TILE_SIZE * 0.75f);
          }
        }
      }
    }
  }

  /**
   * Releases any resources held by the UI component.
   */
  @Override
  public void dispose() {

    font.dispose();
  }
}
