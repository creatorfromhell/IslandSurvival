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

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;

/**
 * PlayerRenderer
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class PlayerRenderer implements Renderable {

  private final TextureHelper helper = new TextureHelper();

  /**
   * This method is used to create a new instance of an object. It should be implemented in classes
   * that require initialization.
   */
  @Override
  public void create() {

    helper.create(GameManager.instance().assetManager().playerSheet(), 48, 48, 24, 8);

    //animation arrays creation
    //our walking animations
    helper.southWalkingAnimation(new Animation<>(0.05f, helper.animationFrames(32, 8)));
    helper.northWalkingAnimation(new Animation<>(0.05f, helper.animationFrames(40,8)));
    helper.westWalkingAnimation(new Animation<>(0.05f, helper.animationFrames(56,8)));
    helper.eastWalkingAnimation(new Animation<>(0.05f, helper.animationFrames(48,8)));

    //standing animation
    helper.southStandingAnimation(new Animation<>(0.10f, helper.animationFrames(0, 8)));
    helper.northStandingAnimation(new Animation<>(0.10f, helper.animationFrames(8,8)));
    helper.westStandingAnimation(new Animation<>(0.10f, helper.animationFrames(24,8)));
    helper.eastStandingAnimation(new Animation<>(0.10f, helper.animationFrames(16,8)));
  }

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  @Override
  public void render(final SpriteBatch batch) {

    helper.render(batch, GameManager.instance().player(), 40, 48);
  }

  /**
   * Disposes of any resources held by the object implementing this method. This method should be
   * called when the object is no longer needed to release any resources it may hold.
   */
  @Override
  public void dispose() {

    helper.dispose();
  }
}
