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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.creatorfromhell.client.render.BiomeBordersComponent;
import io.github.creatorfromhell.client.render.DebugComponent;
import io.github.creatorfromhell.client.render.UIComponent;
import io.github.creatorfromhell.client.render.WorldComponent;

import java.util.LinkedHashMap;

/**
 * AssetManager
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class AssetManager {

  private SpriteBatch batch;
  private Texture playerSheet;
  private Texture tilesheet;

  private TextureRegion[] playerRegions;

  //we use a linked hashmap since rendering is very order driven and we need to render in the correct order.
  public final LinkedHashMap<String, UIComponent> uiComponents = new LinkedHashMap<>();

  public AssetManager() {

    uiComponents.put("world", new WorldComponent());
    uiComponents.put("biome-borders", new BiomeBordersComponent());
    uiComponents.put("debug", new DebugComponent());
  }

  public void create() {

    batch = new SpriteBatch();

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

    for(final UIComponent uiComponent : uiComponents.values()) {

      uiComponent.create();
    }
  }

  public void render() {


    batch.setProjectionMatrix(GameManager.instance().player().cameraController().camera().combined);
    batch.begin();

    for(final UIComponent uiComponent : uiComponents.values()) {

      uiComponent.render(batch);

      //reset batch color to prevent color bleeding.
      batch.setColor(Color.WHITE);
    }

    //TODO: Player renderer
    batch.draw(playerRegions[0], GameManager.instance().player().location().x(), GameManager.instance().player().location().y(), 40, 56);

    batch.end();
  }

  public void dispose() {

    for(final UIComponent uiComponent : uiComponents.values()) {

      uiComponent.dispose();
    }

    batch.dispose();
    playerSheet.dispose();
    tilesheet.dispose();
    playerRegions = null;
  }


  public SpriteBatch batch() {

    return batch;
  }

  public Texture playerSheet() {

    return playerSheet;
  }

  public Texture tilesheet() {

    return tilesheet;
  }

  public TextureRegion[] playerRegions() {

    return playerRegions;
  }
}
