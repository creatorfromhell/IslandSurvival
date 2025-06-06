package io.github.creatorfromhell.client;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.util.location.Direction;

/**
 * InputHandler
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class InputHandler {

  private final Player player;

  public InputHandler(final Player player) {

    this.player = player;
  }

  public void render() {

    final float dt = Gdx.graphics.getDeltaTime();
    boolean isMoving = false;

    //our player movement using WASD
    if(Gdx.input.isKeyPressed(Input.Keys.W)) {
      player.location().addY((int)(player.speed() * dt));

      player.face(Direction.NORTH);
      isMoving = true;
    }

    if(Gdx.input.isKeyPressed(Input.Keys.S)) {
      player.location().subY((int)(player.speed() * dt));
      player.face(Direction.SOUTH);
      isMoving = true;
    }
    if(Gdx.input.isKeyPressed(Input.Keys.A)) {
      player.location().subX((int)(player.speed() * dt));
      player.face(Direction.WEST);
      isMoving = true;
    }
    if(Gdx.input.isKeyPressed(Input.Keys.D)) {
      player.location().addX((int)(player.speed() * dt));
      player.face(Direction.EAST);
      isMoving = true;
    }

    player.setMoving(isMoving);

    if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)) player.debug(!player.debug());

    if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
      player.decelerate(50f);

      if(player.speed() < 150f) player.speed(150f);
    }

    if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) player.accelerate(50f);
    if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.cameraController().zoomIn(1f * dt);
    if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.cameraController().zoomOut(1f * dt);
    if(Gdx.input.isKeyJustPressed(Input.Keys.R)) player.cameraController().zoom(1f);

    //player.cameraController().camera().update();
  }
}
