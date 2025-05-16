package io.github.creatorfromhell.client;
/*
 * The New Economy
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
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.creatorfromhell.entity.Player;

import static io.github.creatorfromhell.Main.TILE_SIZE;

/**
 * CameraController
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class CameraController {
  private OrthographicCamera camera;

  private final Player player;

  public CameraController(final Player player) {

    this.player = player;
    camera = new OrthographicCamera();
  }

  public void init() {

    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera.zoom = 0.5f;

  }

  public void render() {


    camera.position.set(player.location().x() + TILE_SIZE / 2f, player.location().y() + TILE_SIZE / 2f, 0);

    camera.zoom = Math.max(0.25f, Math.min(4f, camera.zoom));
    camera.update();
  }

  public OrthographicCamera camera() {

    return camera;
  }

  public void camera(final OrthographicCamera camera) {

    this.camera = camera;
  }

  public void zoom(final float zoom) {

    this.camera.zoom = 1f;
  }

  public void zoomIn(final float zoom) {

    this.camera.zoom += zoom;
  }

  public void zoomOut(final float zoom) {

    this.camera.zoom -= zoom;
  }
}
