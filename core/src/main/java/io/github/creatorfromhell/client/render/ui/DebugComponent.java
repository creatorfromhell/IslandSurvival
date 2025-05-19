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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.world.biome.Biome;
import io.github.creatorfromhell.world.tile.Tile;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;

/**
 * DebugComponent
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class DebugComponent implements Renderable {

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

    font.setColor(Color.WHITE);

    final Player player = GameManager.instance().player();

    if(player.debug()) {

      // Text Overlay: Tile type, position, biome
      final int tileX = (int)(player.location().x() / TILE_SIZE);
      final int tileY = (int)(player.location().y() / TILE_SIZE);
      final Tile currentTile = GameManager.instance().worldGenerator().getTileType(tileX, tileY);
      final Biome currentBiome = GameManager.instance().worldGenerator().getBiome(tileX, tileY);
      font.draw(batch, "Coords: (" + tileX + ", " + tileY + ")", player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 10);
      font.draw(batch, "Tile: " + currentTile.id(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 30);
      font.draw(batch, "Biome: " + currentBiome.id(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 50);
      font.draw(batch, "Speed: " + player.speed(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 70);
      font.draw(batch, "Direction: " + player.direction().name(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 90);
      font.draw(batch, "Moving: " + player.moving(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 110);
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
