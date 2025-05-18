package io.github.creatorfromhell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.creatorfromhell.entity.Player;
import io.github.creatorfromhell.registry.TileTypeRegistry;
import io.github.creatorfromhell.world.biome.Biome;
import io.github.creatorfromhell.world.generator.WorldGenerator;
import io.github.creatorfromhell.world.tile.Tile;
import io.github.creatorfromhell.world.tile.TileType;
import io.github.creatorfromhell.world.tile.impl.SimpleTile;
import io.github.creatorfromhell.world.tile.impl.SimpleTileRenderer;

public class Main extends ApplicationAdapter {

  public static final int TILE_SIZE = 16;

  public static final int SIZE = 600;
  private SpriteBatch batch;
  private Texture playerSheet;
  private Texture tilesheet;
  private TextureRegion[] tileRegions;
  private TextureRegion[] playerRegions;

  private BitmapFont font;

  private Player player;

  private WorldGenerator worldGenerator;

  @Override
  public void create() {

    batch = new SpriteBatch();

    player = new Player();

    player.cameraController().init();

    worldGenerator = new WorldGenerator(12345L);

    font = new BitmapFont();

    playerSheet = new Texture(Gdx.files.internal("character.png"));
    final TextureRegion[][] playerSplit = TextureRegion.split(playerSheet, 48, 48);
    playerRegions = new TextureRegion[8 * 24];
    int index = 0;
    for(int i = 0; i < 24; i++) {

      for(int j = 0; j < 8; j++) {

        playerRegions[index++] = playerSplit[i][j];
      }
    }


    tilesheet = new Texture(Gdx.files.internal("tiles/tilesheet_v2.png"));
    final TextureRegion[][] split = TextureRegion.split(tilesheet, TILE_SIZE, TILE_SIZE);

    //fixBleeding(split);

    // Register each tile using simplified SimpleTileRenderer constructor
    TileTypeRegistry.register(new SimpleTile("core:grass", false, new SimpleTileRenderer(split[0][2]), true));
    TileTypeRegistry.register(new SimpleTile("core:forest_grass", false, new SimpleTileRenderer(split[0][3]), true));
    TileTypeRegistry.register(new SimpleTile("core:sand", false, new SimpleTileRenderer(split[2][1]), false));
    TileTypeRegistry.register(new SimpleTile("core:snow", false, new SimpleTileRenderer(split[0][1]), false));
    TileTypeRegistry.register(new SimpleTile("core:ice", true, new SimpleTileRenderer(split[0][1]), false));
    TileTypeRegistry.register(new SimpleTile("core:dirt", false, new SimpleTileRenderer(split[0][0]), false));
    TileTypeRegistry.register(new SimpleTile("core:water", false, new SimpleTileRenderer(split[1][2]), false));
    TileTypeRegistry.register(new SimpleTile("core:lava", true, new SimpleTileRenderer(split[3][0]), false));
    TileTypeRegistry.register(new SimpleTile("core:stone", true, new SimpleTileRenderer(split[1][0]), false));
    TileTypeRegistry.register(new SimpleTile("core:coast", true, new SimpleTileRenderer(split[3][3]), false));
  }

  private Texture createSolidTexture(final Color color) {

    final Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    p.setColor(color);
    p.fill();
    final Texture t = new Texture(p);
    p.dispose();
    return t;
  }

  @Override
  public void render() {

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    player.handler().render();

    player.cameraController().render();

    ScreenUtils.clear(0, 0, 0, 1);


    batch.setProjectionMatrix(player.cameraController().camera().combined);
    batch.begin();

    // batch.setColor(Color.WHITE);

    final int minX = (int)((player.location().x() - (Gdx.graphics.getWidth() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) - 2;
    final int minY = (int)((player.location().y() - (Gdx.graphics.getHeight() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) - 2;
    final int maxX = (int)((player.location().x() + (Gdx.graphics.getWidth() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) + 2;
    final int maxY = (int)((player.location().y() + (Gdx.graphics.getHeight() * player.cameraController().camera().zoom) / 2f) / TILE_SIZE) + 2;

    for(int x = minX; x <= maxX; x++) {
      for(int y = minY; y <= maxY; y++) {

        final Tile tile = worldGenerator.getTileType(x, y);
        final Biome biome = worldGenerator.getBiome(x, y);
        final float height = worldGenerator.terrainGenerator().getHeight(x, y);

        tile.renderer().render(batch, biome, tile, height, x, y);
      }
    }

    //reset batch color
    batch.setColor(Color.WHITE);

    // Draw biome borders (by comparing neighboring biome cells)
    for(int x = minX; x <= maxX; x++) {
      for(int y = minY; y <= maxY; y++) {
        final Biome current = worldGenerator.getBiome(x, y);
        boolean border = false;
        if(worldGenerator.getBiome(x + 1, y) != current) border = true;
        if(worldGenerator.getBiome(x - 1, y) != current) border = true;
        if(worldGenerator.getBiome(x, y + 1) != current) border = true;
        if(worldGenerator.getBiome(x, y - 1) != current) border = true;

        if(border) {
          font.setColor(Color.BLACK);
          font.draw(batch, "#", x * TILE_SIZE + TILE_SIZE / 4f, y * TILE_SIZE + TILE_SIZE * 0.75f);
        }
      }
    }

    batch.draw(playerRegions[0], player.location().x(), player.location().y(), 40, 56);

    font.setColor(Color.WHITE);

    if(player.debug()) {

      // Text Overlay: Tile type, position, biome
      final int tileX = (int)(player.location().x() / TILE_SIZE);
      final int tileY = (int)(player.location().y() / TILE_SIZE);
      final Tile currentTile = worldGenerator.getTileType(tileX, tileY);
      final Biome currentBiome = worldGenerator.getBiome(tileX, tileY);
      font.draw(batch, "Coords: (" + tileX + ", " + tileY + ")", player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 10);
      font.draw(batch, "Tile: " + currentTile.id(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 30);
      font.draw(batch, "Biome: " + currentBiome.id(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 50);
      font.draw(batch, "Speed: " + player.speed(), player.cameraController().camera().position.x - Gdx.graphics.getWidth() / 2f * player.cameraController().camera().zoom + 10, player.cameraController().camera().position.y + Gdx.graphics.getHeight() / 2f * player.cameraController().camera().zoom - 70);
    }
    batch.end();
  }

  @Override
  public void dispose() {

    batch.dispose();
    tilesheet.dispose();
    playerSheet.dispose();
  }
}
