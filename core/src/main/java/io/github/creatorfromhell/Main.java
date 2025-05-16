package io.github.creatorfromhell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.creatorfromhell.util.BiomeColors;
import io.github.creatorfromhell.util.WorldGenerator;
import io.github.creatorfromhell.world.Biome;
import io.github.creatorfromhell.world.tile.TileType;

public class Main extends ApplicationAdapter {

    public static final int SIZE = 600;
    private static final int PLAYER_SIZE = 16;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture playerSheet;
    private Texture tilesheet;
    private TextureRegion[] tileRegions;
    private TextureRegion[] playerRegions;

    private BitmapFont font;

    private float playerX, playerY;
    private float playerSpeed = 50f; // pixels per second
    private final int TILE_SIZE = 16;

    private Texture waterTex, sandTex, coastTex, grassTex, forestGrassTex, snowTex, playerTex;

    private WorldGenerator worldGenerator;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;

        playerX = 0;
        playerY = 0;

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


        tilesheet = new Texture(Gdx.files.internal("tiles/img.png"));
        final TextureRegion[][] split = TextureRegion.split(tilesheet, TILE_SIZE, TILE_SIZE);

        //fixBleeding(split);

        tileRegions = new TextureRegion[TileType.values().length];
        tileRegions[TileType.GRASS.id()]         = split[0][0]; // grayscale grass
        tileRegions[TileType.WATER.id()]         = split[0][1];
        tileRegions[TileType.COAST.id()]         = split[0][2];
        tileRegions[TileType.SAND.id()]          = split[1][0];
        tileRegions[TileType.SNOW.id()]          = split[1][1];
        tileRegions[TileType.FOREST_GRASS.id()]  = split[0][0];

        // Placeholder 1x1 textures
        waterTex = createSolidTexture(Color.SKY);
        coastTex = createSolidTexture(Color.BROWN);
        sandTex = createSolidTexture(Color.TAN);
        grassTex = createSolidTexture(Color.GREEN);
        forestGrassTex = createSolidTexture(Color.FOREST);
        snowTex = createSolidTexture(Color.LIGHT_GRAY);
        playerTex = createSolidTexture(Color.WHITE);
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
        final float dt = Gdx.graphics.getDeltaTime();
        //our player movement using WASD
        if (Gdx.input.isKeyPressed(Input.Keys.W)) playerY += playerSpeed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) playerY -= playerSpeed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) playerX -= playerSpeed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) playerX += playerSpeed * dt;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerSpeed -= 50f;

            if(playerSpeed < 50f) playerSpeed = 50f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) playerSpeed += 100f;

        camera.position.set(playerX + TILE_SIZE / 2f, playerY + TILE_SIZE / 2f, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) camera.zoom += 1f * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) camera.zoom -= 1f * dt;
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) camera.zoom = 1f;

        camera.zoom = Math.max(0.25f, Math.min(4f, camera.zoom));
        camera.update();

        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

       // batch.setColor(Color.WHITE);

        final int minX = (int)((playerX - (Gdx.graphics.getWidth() * camera.zoom) / 2f) / TILE_SIZE) - 2;
        final int minY = (int)((playerY - (Gdx.graphics.getHeight() * camera.zoom) / 2f) / TILE_SIZE) - 2;
        final int maxX = (int)((playerX + (Gdx.graphics.getWidth() * camera.zoom) / 2f) / TILE_SIZE) + 2;
        final int maxY = (int)((playerY + (Gdx.graphics.getHeight() * camera.zoom) / 2f) / TILE_SIZE) + 2;

        //mask tile for auto rounding later
        final Texture roundedMask = createRoundedCornerMask(TILE_SIZE, 5);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                final TileType type = worldGenerator.getTileType(x, y);
                final Biome biome = worldGenerator.getBiome(x, y);
                final float height = worldGenerator.terrainGenerator().getHeight(x, y);

                batch.setColor(Color.WHITE);
                if (type == TileType.GRASS || type == TileType.FOREST_GRASS) {
                    final Color tint = BiomeColors.getTintedColor(biome, height);
                    batch.setColor(tint);
                } else {
                    batch.setColor(Color.WHITE);
                }

                final TextureRegion region = tileRegions[type.id()];
                batch.draw(region, x * TILE_SIZE, y * TILE_SIZE);
            }
        }

        batch.setColor(Color.WHITE);

        // Draw biome borders (by comparing neighboring biome cells)
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                final Biome current = worldGenerator.getBiome(x, y);
                boolean border = false;
                if (worldGenerator.getBiome(x + 1, y) != current) border = true;
                if (worldGenerator.getBiome(x - 1, y) != current) border = true;
                if (worldGenerator.getBiome(x, y + 1) != current) border = true;
                if (worldGenerator.getBiome(x, y - 1) != current) border = true;

                if (border) {
                    font.setColor(Color.BLACK);
                    font.draw(batch, "#", x * TILE_SIZE + TILE_SIZE / 4f, y * TILE_SIZE + TILE_SIZE * 0.75f);
                }
            }
        }

        batch.draw(playerRegions[0], playerX, playerY, 40, 56);

        // Text Overlay: Tile type, position, biome
        final int tileX = (int)(playerX / TILE_SIZE);
        final int tileY = (int)(playerY / TILE_SIZE);
        final TileType currentTile = worldGenerator.getTileType(tileX, tileY);
        final Biome currentBiome = worldGenerator.getBiome(tileX, tileY);

        font.setColor(Color.WHITE);
        font.draw(batch, "Coords: (" + tileX + ", " + tileY + ")", camera.position.x - Gdx.graphics.getWidth() / 2f * camera.zoom + 10, camera.position.y + Gdx.graphics.getHeight() / 2f * camera.zoom - 10);
        font.draw(batch, "Tile: " + currentTile, camera.position.x - Gdx.graphics.getWidth() / 2f * camera.zoom + 10, camera.position.y + Gdx.graphics.getHeight() / 2f * camera.zoom - 30);
        font.draw(batch, "Biome: " + currentBiome, camera.position.x - Gdx.graphics.getWidth() / 2f * camera.zoom + 10, camera.position.y + Gdx.graphics.getHeight() / 2f * camera.zoom - 50);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerTex.dispose();
        waterTex.dispose();
        sandTex.dispose();
        grassTex.dispose();
        snowTex.dispose();
        tilesheet.dispose();
        playerSheet.dispose();
    }

    public boolean isEdgeOfWater(final WorldGenerator worldGenerator, final int x, final int y) {
        final TileType center = worldGenerator.getTileType(x, y);
        if (center == TileType.WATER) return false;

        // Check 4 directions (optionally 8)
        final int[][] offsets = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}  // Top, Right, Bottom, Left
            // Add diagonals if you want rounded outer corners
            // {-1, -1}, {1, -1}, {1, 1}, {-1, 1}
        };

        for (final int[] offset : offsets) {
            final int nx = x + offset[0];
            final int ny = y + offset[1];

            if (worldGenerator.getTileType(x, y)== TileType.WATER) return true;
        }

        return false;
    }

    public Texture createRoundedCornerMask(final int tileSize, final int radius) {
        final Pixmap mask = new Pixmap(tileSize, tileSize, Pixmap.Format.RGBA8888);
        mask.setColor(1, 1, 1, 1);
        mask.fill();

        // Erase corners using transparent pie slices
        mask.setBlending(Pixmap.Blending.None);
        mask.setColor(1, 1, 1, 0);

        mask.fillCircle(radius, radius, radius);                             // top-left
        mask.fillCircle(tileSize - radius - 1, radius, radius);             // top-right
        mask.fillCircle(radius, tileSize - radius - 1, radius);             // bottom-left
        mask.fillCircle(tileSize - radius - 1, tileSize - radius - 1, radius); // bottom-right

        return new Texture(mask);
    }

    public Texture applyMask(final Texture tile, final Texture mask) {
        final FrameBuffer fb = new FrameBuffer(Pixmap.Format.RGBA8888, tile.getWidth(), tile.getHeight(), false);
        final SpriteBatch batch = new SpriteBatch();
        fb.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(tile, 0, 0);
        batch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA); // Keep only areas where mask is opaque
        batch.draw(mask, 0, 0);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); // Reset blend
        batch.end();

        final Pixmap result = ScreenUtils.getFrameBufferPixmap(0, 0, tile.getWidth(), tile.getHeight());
        fb.end();
        batch.dispose();
        fb.dispose();

        final Texture resultTex = new Texture(result);
        result.dispose();
        return resultTex;
    }
}
