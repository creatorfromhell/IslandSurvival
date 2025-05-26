package io.github.creatorfromhell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class Main extends ApplicationAdapter {

  private final GameManager manager = new GameManager(SIZE);

  public static final int SIZE = 600;

  @Override
  public void create() {

    manager.create();
  }

  @Override
  public void render() {

    final float delta = Gdx.graphics.getDeltaTime();
    manager.render(delta);
  }

  @Override
  public void dispose() {

    manager.dispose();
  }
}
