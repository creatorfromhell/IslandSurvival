package io.github.creatorfromhell.world;
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

/**
 * Location
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class Location {

  public static final Location ZERO = new Location(0, 0);

    private int x;
    private int y;

    public Location(final int x, final int y) {

        this.x = x;
        this.y = y;
    }

    public int x() {

        return x;
    }

    public void x(final int x) {

        this.x = x;
    }

    public int y() {

        return y;
    }

    public void y(final int y) {

        this.y = y;
    }

    public void addY(final int y) {

      this.y += y;
    }

    public void subY(final int y) {

      this.y -= y;
    }

    public void addX(final int x) {

      this.x += x;
    }

    public void subX(final int x) {

      this.x -= x;
    }
}
