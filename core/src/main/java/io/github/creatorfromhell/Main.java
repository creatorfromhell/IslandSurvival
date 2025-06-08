package io.github.creatorfromhell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import io.github.creatorfromhell.data.MongoDatabaseManager;
import io.github.creatorfromhell.world.World;

public class Main extends ApplicationAdapter {

  private final GameManager manager = new GameManager(SIZE);

  public static final int SIZE = 600;

  @Override
  public void create() {

    MongoDatabaseManager.connect();
    MongoDatabaseManager.initialize();
    final World world = new World();
    world.load(); // load from Mongo
    WorldManager.set(world); // make globally accessible

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
