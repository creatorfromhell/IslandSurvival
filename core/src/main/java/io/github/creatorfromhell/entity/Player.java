package io.github.creatorfromhell.entity;
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

import com.badlogic.gdx.graphics.Camera;
import io.github.creatorfromhell.client.CameraController;
import io.github.creatorfromhell.client.InputHandler;

/**
 * Player
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class Player extends Entity {

  private InputHandler handler;
  private CameraController cameraController;
  private boolean debug = false;

  public Player() {

    this.handler = new InputHandler(this);
    this.cameraController = new CameraController(this);
  }

  public InputHandler handler() {

    return handler;
  }

  public void handler(final InputHandler handler) {

    this.handler = handler;
  }

  public CameraController cameraController() {

    return cameraController;
  }

  public void cameraController(final CameraController cameraController) {

    this.cameraController = cameraController;
  }

  public boolean debug() {

    return debug;
  }

  public void debug(final boolean debug) {

    this.debug = debug;
  }
}
