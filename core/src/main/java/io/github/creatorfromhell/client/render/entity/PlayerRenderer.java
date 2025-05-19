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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.Player;

/**
 * PlayerRenderer
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class PlayerRenderer implements Renderable {

  public Animation<TextureRegion> northWalkingAnimation;
  public Animation<TextureRegion> southWalkingAnimation;
  public Animation<TextureRegion> westWalkingAnimation;
  public Animation<TextureRegion> eastWalkingAnimation;

  public Animation<TextureRegion> northStandingAnimation;
  public Animation<TextureRegion> southStandingAnimation;
  public Animation<TextureRegion> westStandingAnimation;
  public Animation<TextureRegion> eastStandingAnimation;

  private TextureRegion[] playerRegions;

  float animationTime;

  private TextureRegion playerRegion() {

    final Player player = GameManager.instance().player();
    switch(player.direction()) {
      case NORTH -> {
        if(!player.moving()) {
          return northStandingAnimation.getKeyFrame(animationTime, true);
        }

        return northWalkingAnimation.getKeyFrame(animationTime, true);
      }
      case EAST -> {
        if(!player.moving()) {
          return eastStandingAnimation.getKeyFrame(animationTime, true);
        }
        return eastWalkingAnimation.getKeyFrame(animationTime, true);
      }
      case WEST -> {
        if(!player.moving()) {
          return westStandingAnimation.getKeyFrame(animationTime, true);
        }
        return westWalkingAnimation.getKeyFrame(animationTime, true);
      }
      default -> {
        if(!player.moving()) {
          return southStandingAnimation.getKeyFrame(animationTime, true);
        }
        return southWalkingAnimation.getKeyFrame(animationTime, true);
      }
    }
  }

  private TextureRegion[] animationFrames(final int start, final int length) {

    final TextureRegion[] frames = new TextureRegion[length];
    int index = start;

    for(int i = 0; i < length; i++) {

      frames[i] = playerRegions[index];

      index++;
    }

    return frames;
  }

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

    //animation arrays creation
    //our walking animations
    southWalkingAnimation = new Animation<>(0.05f, animationFrames(32, 8));
    northWalkingAnimation = new Animation<>(0.05f, animationFrames(40,8));
    westWalkingAnimation = new Animation<>(0.05f, animationFrames(56,8));
    eastWalkingAnimation = new Animation<>(0.05f, animationFrames(48,8));

    //standing animation
    southStandingAnimation = new Animation<>(0.10f, animationFrames(0, 8));
    northStandingAnimation = new Animation<>(0.10f, animationFrames(8,8));
    westStandingAnimation = new Animation<>(0.10f, animationFrames(24,8));
    eastStandingAnimation = new Animation<>(0.10f, animationFrames(16,8));

    animationTime = 0f;
  }

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  @Override
  public void render(final SpriteBatch batch) {

    animationTime += Gdx.graphics.getDeltaTime();

    batch.draw(playerRegion(), GameManager.instance().player().location().x(), GameManager.instance().player().location().y(), 40, 56);
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
