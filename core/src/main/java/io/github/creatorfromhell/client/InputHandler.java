package io.github.creatorfromhell.client;
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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.creatorfromhell.entity.Player;

/**
 * InputHandler
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class InputHandler {

    private final Player player;

    public InputHandler(final Player player) {
        this.player = player;
    }

    public void render() {

      final float dt = Gdx.graphics.getDeltaTime();
      //our player movement using WASD
      if(Gdx.input.isKeyPressed(Input.Keys.W)) player.location().addY((int)(player.speed() * dt));
      if(Gdx.input.isKeyPressed(Input.Keys.S)) player.location().subY((int)(player.speed() * dt));
      if(Gdx.input.isKeyPressed(Input.Keys.A)) player.location().subX((int)(player.speed() * dt));
      if(Gdx.input.isKeyPressed(Input.Keys.D)) player.location().addX((int)(player.speed() * dt));
      if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)) player.debug(!player.debug());

      if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
        player.decelerate(50f);

        if(player.speed() < 150f) player.speed(150f);
      }

      if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) player.accelerate(50f);
      if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.cameraController().zoomIn(1f * dt);
      if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.cameraController().zoomOut(1f * dt);
      if(Gdx.input.isKeyJustPressed(Input.Keys.R)) player.cameraController().zoom(1f);
    }
}
