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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.Entity;
import io.github.creatorfromhell.entity.creatures.Rabbit;

import java.util.UUID;

/**
 * RabbitRenderer
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class RabbitRenderer implements Renderable {

  private final UUID rabbitID;

  private final TextureHelper helper = new TextureHelper();

  public RabbitRenderer(final UUID rabbitID) {
    this.rabbitID = rabbitID;
  }

  /**
   * This method is used to create a new instance of an object. It should be implemented in classes
   * that require initialization.
   */
  @Override
  public void create() {

    //call our helper's create method with the correct sheet
    helper.create(GameManager.instance().assetManager().rabbitSheet(), 16, 16, 5, 4);

    //animation arrays creation
    //our walking animations
    helper.southWalkingAnimation(new Animation<>(0.20f, helper.animationFrames(0, 4)));
    helper.northWalkingAnimation(new Animation<>(0.20f, helper.animationFrames(4,4)));
    helper.westWalkingAnimation(new Animation<>(0.20f, helper.animationFrames(8,4)));
    helper.eastWalkingAnimation(new Animation<>(0.20f, helper.animationFrames(12,4)));

    //standing animation
    final Animation<TextureRegion> standing = new Animation<>(0.20f, helper.animationFrames(16, 4));
    helper.southStandingAnimation(standing);
    helper.northStandingAnimation(standing);
    helper.westStandingAnimation(standing);
    helper.eastStandingAnimation(standing);
  }

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  @Override
  public void render(final SpriteBatch batch) {

    final Entity entity = GameManager.instance().entityManager().entities().get(rabbitID);
    if(entity instanceof final Rabbit rabbit) {

      this.helper.render(batch, rabbit, 16, 16);
    }
  }

  /**
   * Disposes of any resources held by the object implementing this method. This method should be
   * called when the object is no longer needed to release any resources it may hold.
   */
  @Override
  public void dispose() {

    this.helper.dispose();
  }
}
