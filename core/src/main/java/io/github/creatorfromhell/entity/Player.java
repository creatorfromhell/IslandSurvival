package io.github.creatorfromhell.entity;


import io.github.creatorfromhell.client.CameraController;
import io.github.creatorfromhell.client.InputHandler;

/**
 * Player
 *
 * @author creatorfromhell
 * @since 0.0.1.0
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
