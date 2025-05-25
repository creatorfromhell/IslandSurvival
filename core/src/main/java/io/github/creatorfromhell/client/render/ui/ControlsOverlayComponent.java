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

/**
 * ControlsOverlayComponent
 * Displays control instructions when debug mode is off.
 *
 * @author creatorfromhell
 * @since 0.0.1.1
 */
public class ControlsOverlayComponent implements Renderable {

  private BitmapFont font;

  @Override
  public void create() {
    font = new BitmapFont();
  }

  @Override
  public void render(final SpriteBatch batch) {
    final Player player = GameManager.instance().player();

    if(!player.debug()) {
      font.setColor(Color.LIGHT_GRAY);

      final float startX = player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10;
      final float startY = player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 10;

      final float lineHeight = 20f;
      float y = startY;

      font.draw(batch, "Controls:", startX, y);
      y -= lineHeight;
      font.draw(batch, "Movement: W A S D", startX, y);
      y -= lineHeight;
      font.draw(batch, "Zoom In: DOWN Arrow", startX, y);
      y -= lineHeight;
      font.draw(batch, "Zoom Out: UP Arrow", startX, y);
      y -= lineHeight;
      font.draw(batch, "Toggle Debug: TAB", startX, y);
      y -= lineHeight;
      font.draw(batch, "Increase Speed: RIGHT Arrow", startX, y);
      y -= lineHeight;
      font.draw(batch, "Decrease Speed: LEFT Arrow", startX, y);
    }
  }

  @Override
  public void dispose() {
    font.dispose();
  }
}
