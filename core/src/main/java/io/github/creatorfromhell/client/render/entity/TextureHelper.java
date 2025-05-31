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
import io.github.creatorfromhell.entity.Entity;
import io.github.creatorfromhell.entity.LivingEntity;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.util.location.Direction;

/**
 * TextureHelper
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class TextureHelper {

  public Animation<TextureRegion> northWalkingAnimation;
  public Animation<TextureRegion> southWalkingAnimation;
  public Animation<TextureRegion> westWalkingAnimation;
  public Animation<TextureRegion> eastWalkingAnimation;

  public Animation<TextureRegion> northStandingAnimation;
  public Animation<TextureRegion> southStandingAnimation;
  public Animation<TextureRegion> westStandingAnimation;
  public Animation<TextureRegion> eastStandingAnimation;

  private TextureRegion[] regions;

  float animationTime;

  /**
   * Determines the appropriate TextureRegion to display for the player based on their direction and movement state.
   *
   * @return the TextureRegion to display for the player
   */
  private TextureRegion region(final Entity entity) {

    final Direction direction = (entity instanceof final LivingEntity living)? living.direction() : Direction.SOUTH;
    final boolean moving = entity instanceof final LivingEntity living && living.isMoving();

    switch(direction) {
      case NORTH -> {
        if(!moving) {
          return northStandingAnimation.getKeyFrame(animationTime, true);
        }

        return northWalkingAnimation.getKeyFrame(animationTime, true);
      }
      case EAST -> {
        if(!moving) {
          return eastStandingAnimation.getKeyFrame(animationTime, true);
        }
        return eastWalkingAnimation.getKeyFrame(animationTime, true);
      }
      case WEST -> {
        if(!moving) {
          return westStandingAnimation.getKeyFrame(animationTime, true);
        }
        return westWalkingAnimation.getKeyFrame(animationTime, true);
      }
      default -> {
        if(!moving) {
          return southStandingAnimation.getKeyFrame(animationTime, true);
        }
        return southWalkingAnimation.getKeyFrame(animationTime, true);
      }
    }
  }

  /**
   * Retrieves a sequence of TextureRegions from the regions array based on the specified start index and length.
   *
   * @param start the starting index in the regions array
   * @param length the number of TextureRegions to retrieve
   * @return an array of TextureRegions containing the specified sequence of frames
   */
  public TextureRegion[] animationFrames(final int start, final int length) {

    final TextureRegion[] frames = new TextureRegion[length];
    int index = start;

    for(int i = 0; i < length; i++) {

      frames[i] = regions[index];

      index++;
    }

    return frames;
  }

  /**
   * This method is used to create a new instance of an object. It should be implemented in classes
   * that require initialization.
   */
  public void create(final int tileWidth, final int tileHeight, final int sheetHeight, final int sheetWidth) {

    //create a texture region split
    final TextureRegion[][] split = TextureRegion.split(GameManager.instance().assetManager().playerSheet(), tileWidth, tileHeight);
    regions = new TextureRegion[sheetWidth * sheetHeight];
    int index = 0;
    //initialize our texture regions from our texture split.
    for(int i = 0; i < sheetHeight; i++) {

      for(int j = 0; j < sheetWidth; j++) {

        regions[index++] = split[i][j];
      }
    }

    animationTime = 0f;
  }

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  public void render(final SpriteBatch batch, final Entity entity, final int width, final int height) {

    animationTime += Gdx.graphics.getDeltaTime();

    batch.draw(region(entity), entity.location().x(), entity.location().y(), width, height);
  }

  /**
   * Disposes of any resources held by the object implementing this method. This method should be
   * called when the object is no longer needed to release any resources it may hold.
   */
  public void dispose() {

    regions = null;
  }

  public Animation<TextureRegion> northWalkingAnimation() {

    return northWalkingAnimation;
  }

  public void northWalkingAnimation(final Animation<TextureRegion> northWalkingAnimation) {

    this.northWalkingAnimation = northWalkingAnimation;
  }

  public Animation<TextureRegion> southWalkingAnimation() {

    return southWalkingAnimation;
  }

  public void southWalkingAnimation(final Animation<TextureRegion> southWalkingAnimation) {

    this.southWalkingAnimation = southWalkingAnimation;
  }

  public Animation<TextureRegion> westWalkingAnimation() {

    return westWalkingAnimation;
  }

  public void westWalkingAnimation(final Animation<TextureRegion> westWalkingAnimation) {

    this.westWalkingAnimation = westWalkingAnimation;
  }

  public Animation<TextureRegion> eastWalkingAnimation() {

    return eastWalkingAnimation;
  }

  public void eastWalkingAnimation(final Animation<TextureRegion> eastWalkingAnimation) {

    this.eastWalkingAnimation = eastWalkingAnimation;
  }

  public Animation<TextureRegion> northStandingAnimation() {

    return northStandingAnimation;
  }

  public void northStandingAnimation(final Animation<TextureRegion> northStandingAnimation) {

    this.northStandingAnimation = northStandingAnimation;
  }

  public Animation<TextureRegion> southStandingAnimation() {

    return southStandingAnimation;
  }

  public void southStandingAnimation(final Animation<TextureRegion> southStandingAnimation) {

    this.southStandingAnimation = southStandingAnimation;
  }

  public Animation<TextureRegion> westStandingAnimation() {

    return westStandingAnimation;
  }

  public void westStandingAnimation(final Animation<TextureRegion> westStandingAnimation) {

    this.westStandingAnimation = westStandingAnimation;
  }

  public Animation<TextureRegion> eastStandingAnimation() {

    return eastStandingAnimation;
  }

  public void eastStandingAnimation(final Animation<TextureRegion> eastStandingAnimation) {

    this.eastStandingAnimation = eastStandingAnimation;
  }

  public TextureRegion[] regions() {

    return regions;
  }

  public float animationTime() {

    return animationTime;
  }
}
