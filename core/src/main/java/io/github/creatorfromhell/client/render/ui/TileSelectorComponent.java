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
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.util.location.Location;

import static io.github.creatorfromhell.registry.TileTypeRegistry.TILE_SIZE;


/**
 * TileSelectorComponent
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class TileSelectorComponent implements Renderable {

  ShapeRenderer shapeRenderer;

  /**
   * This method is used to create a new instance of an object. It should be implemented in classes
   * that require initialization.
   */
  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
  }

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  @Override
  public void render(final SpriteBatch batch) {

    final Player player = GameManager.instance().player();
    shapeRenderer.setProjectionMatrix(player.cameraController().camera().combined);

    final Camera camera = player.cameraController().camera();

    // Get tile under mouse
    final Location mousePixel = Location.fromMouse();
    final Location mouseTile = mousePixel.asTile();
    final Location playerTile = player.location().asTile();

    final int dx = Math.abs(playerTile.x() - mouseTile.x());
    final int dy = Math.abs(playerTile.y() - mouseTile.y());

    final int maxRange = 5; // configurable range in tiles

    // Draw white outline around hovered tile if in range
    if(dx <= maxRange && dy <= maxRange) {
      shapeRenderer.setProjectionMatrix(camera.combined);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      shapeRenderer.setColor(Color.WHITE);
      shapeRenderer.rect(
        mouseTile.x(),
        mouseTile.y(),
        TILE_SIZE,
        TILE_SIZE);

      shapeRenderer.end();
    }
  }

  /**
   * Disposes of any resources held by the object implementing this method. This method should be
   * called when the object is no longer needed to release any resources it may hold.
   */
  @Override
  public void dispose() {

    shapeRenderer.dispose();
  }
}
