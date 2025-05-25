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
import io.github.creatorfromhell.client.render.Renderable;
import io.github.creatorfromhell.client.render.ui.BiomeBordersComponent;
import io.github.creatorfromhell.client.render.ui.ControlsOverlayComponent;
import io.github.creatorfromhell.client.render.ui.DebugComponent;
import io.github.creatorfromhell.client.render.ui.TileSelectorComponent;
import io.github.creatorfromhell.client.render.ui.WorldComponent;
import io.github.creatorfromhell.entity.Entity;

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

  //we use a linked hashmap since rendering is very order driven and we need to render in the correct order.
  public final LinkedHashMap<String, Renderable> uiComponents = new LinkedHashMap<>();
  public final LinkedHashMap<String, Renderable> lateUIComponents = new LinkedHashMap<>();

  public AssetManager() {

    uiComponents.put("world", new WorldComponent());
    uiComponents.put("biome-borders", new BiomeBordersComponent());
    uiComponents.put("debug", new DebugComponent());
    uiComponents.put("controls", new ControlsOverlayComponent());
    lateUIComponents.put("selector", new TileSelectorComponent());
  }

  public void create() {

    batch = new SpriteBatch();

    playerSheet = new Texture(Gdx.files.internal("character.png"));

    tilesheet = new Texture(Gdx.files.internal("tiles/tilesheet_v2.png"));

    for(final Renderable uiComponent : uiComponents.values()) {

      uiComponent.create();
    }

    for(final Entity entity : GameManager.instance().entityManager().entities.values()) {

      if(entity.renderer().isPresent()) {
        entity.renderer().get().create();
      }
    }
  }

  public void render() {


    batch.setProjectionMatrix(GameManager.instance().player().cameraController().camera().combined);
    batch.begin();

    for(final Renderable uiComponent : uiComponents.values()) {

      uiComponent.render(batch);

      //reset batch color to prevent color bleeding.
      batch.setColor(Color.WHITE);
    }

    for(final Entity entity : GameManager.instance().entityManager().entities.values()) {

      if(entity.renderer().isPresent()) {
        entity.renderer().get().render(batch);

        //reset batch color to prevent color bleeding.
        batch.setColor(Color.WHITE);
      }
    }

    batch.end();
  }

  public void dispose() {

    for(final Renderable uiComponent : uiComponents.values()) {

      uiComponent.dispose();
    }

    for(final Entity entity : GameManager.instance().entityManager().entities.values()) {

      if(entity.renderer().isPresent()) {
        entity.renderer().get().dispose();
      }
    }

    batch.dispose();
    playerSheet.dispose();
    tilesheet.dispose();
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
}
