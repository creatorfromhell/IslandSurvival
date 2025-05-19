package io.github.creatorfromhell.client.render.entity;
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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;

/**
 * PlayerRenderer
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class PlayerRenderer implements Renderable {

  private TextureRegion[] playerRegions;

  /**
   * This method is used to create a new instance of an object. It should be implemented in classes
   * that require initialization.
   */
  @Override
  public void create() {

    final TextureRegion[][] playerSplit = TextureRegion.split(GameManager.instance().assetManager().playerSheet(), 48, 48);
    playerRegions = new TextureRegion[8 * 24];
    int index = 0;
    for(int i = 0; i < 24; i++) {

      for(int j = 0; j < 8; j++) {

        playerRegions[index++] = playerSplit[i][j];
      }
    }
  }

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  @Override
  public void render(final SpriteBatch batch) {
    batch.draw(playerRegions[0], GameManager.instance().player().location().x(), GameManager.instance().player().location().y(), 40, 56);
  }

  /**
   * Disposes of any resources held by the object implementing this method. This method should be
   * called when the object is no longer needed to release any resources it may hold.
   */
  @Override
  public void dispose() {

    playerRegions = null;
  }
}
