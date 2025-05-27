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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.creatorfromhell.GameManager;
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.entity.Entity;
import io.github.creatorfromhell.entity.creatures.Rabbit;
import io.github.creatorfromhell.util.location.Location;

import java.util.UUID;

/**
 * RabbitRenderer
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class RabbitRenderer implements Renderable {

  private final UUID rabbitID;
  private ShapeRenderer shapeRenderer;

  public RabbitRenderer(final UUID rabbitID) {
    this.rabbitID = rabbitID;
  }

  /**
   * This method is used to create a new instance of an object. It should be implemented in classes
   * that require initialization.
   */
  @Override
  public void create() {

    this.shapeRenderer = new ShapeRenderer();
  }

  /**
   * Renders the object with the given batch.
   *
   * @param batch the SpriteBatch used for rendering
   */
  @Override
  public void render(final SpriteBatch batch) {
    batch.end(); // End batch before using ShapeRenderer

    final Entity entity = GameManager.instance().entityManager().entities().get(rabbitID);
    if(entity instanceof final Rabbit rabbit) {

      final Location loc = rabbit.location();
      shapeRenderer.setProjectionMatrix(GameManager.instance().player().cameraController().camera().combined);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
      shapeRenderer.setColor(0.75f, 0.75f, 0.75f, 1f); // Gray color
      shapeRenderer.rect(loc.x(), loc.y(), 16, 16);
      shapeRenderer.end();
    }

    batch.begin(); // Resume batch
  }

  /**
   * Disposes of any resources held by the object implementing this method. This method should be
   * called when the object is no longer needed to release any resources it may hold.
   */
  @Override
  public void dispose() {

    this.shapeRenderer.dispose();
  }
}
