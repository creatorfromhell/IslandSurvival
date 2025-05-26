package io.github.creatorfromhell;
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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.registry.TileTypeRegistry;
import io.github.creatorfromhell.simulation.EntityManager;
import io.github.creatorfromhell.world.generator.WorldGenerator;

/**
 * GameManager
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class GameManager {

  private static GameManager instance;

  private final EntityManager entityManager;

  private final AssetManager assetManager;

  private WorldGenerator worldGenerator;

  private final int windowSize;

  public GameManager(final int windowSize) {

    instance = this;

    entityManager = new EntityManager();

    this.assetManager = new AssetManager();

    this.windowSize = windowSize;

    this.worldGenerator = new WorldGenerator(12345L);
  }

  public void create() {

    entityManager.player().cameraController().init();

    assetManager.create();

    TileTypeRegistry.create();
  }

  public void render(final float delta) {

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    ScreenUtils.clear(0, 0, 0, 1);

    entityManager.updateAll(delta);

    entityManager.player().handler().render();

    entityManager.player().cameraController().render();

    assetManager.render();
  }

  public void dispose() {

    assetManager.dispose();
  }

  public static GameManager instance() {

    return instance;
  }

  public EntityManager entityManager() {

    return entityManager;
  }

  public AssetManager assetManager() {

    return assetManager;
  }

  public Player player() {

    return entityManager.player();
  }

  public WorldGenerator worldGenerator() {

    return worldGenerator;
  }
}
