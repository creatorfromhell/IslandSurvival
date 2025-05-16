package io.github.creatorfromhell.client;


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
