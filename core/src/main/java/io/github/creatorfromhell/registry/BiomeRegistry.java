package io.github.creatorfromhell.registry;
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

import com.badlogic.gdx.graphics.Color;
import io.github.creatorfromhell.world.biome.Biome;
import io.github.creatorfromhell.world.biome.impl.SimpleBiome;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * BiomeRegistry
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class BiomeRegistry {

  private static final Map<String, Biome> REGISTRY = new HashMap<>();

  /**
   * Registers a biome in the registry for lookup.
   *
   * @param biome The biome object to register.
   */
  public static void register(final Biome biome) {
    REGISTRY.put(biome.id(), biome);
  }

  /**
   * Retrieves a biome by its unique ID.
   *
   * @param id The unique ID of the biome to retrieve.
   * @return The Biome object associated with the provided ID.
   */
  public static Biome getById(final String id) {
    return REGISTRY.get(id);
  }

  /**
   * Retrieves all biomes available in the registry.
   *
   * @return A collection of Biome objects representing all available biomes.
   */
  public static Collection<Biome> getAllBiomes() {
    return REGISTRY.values();
  }

  static {
    register(new SimpleBiome("GRAVEYARD", 0.3f, 0.2f, "core:forest_grass", "core:dirt",
                             new Color(0.4f, 0.4f, 0.4f, 1f), new Color(0.2f, 0.2f, 0.2f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("ARCTIC", 0.1f, 0.2f, "core:snow", "core:ice",
                             new Color(0.9f, 0.9f, 1.0f, 1f), new Color(0.6f, 0.8f, 0.9f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("JUNGLE", 0.8f, 0.9f, "core:forest_grass", "core:dirt",
                             new Color(0.2f, 0.6f, 0.2f, 1f), new Color(0.0f, 0.4f, 0.2f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("SWAMP", 0.7f, 1.0f, "core:grass", "core:water",
                             new Color(0.3f, 0.5f, 0.3f, 1f), new Color(0.1f, 0.2f, 0.1f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("LAKE", 0.5f, 0.8f, "core:water", "core:dirt",
                             new Color(0.2f, 0.4f, 0.8f, 1f), new Color(0.2f, 0.4f, 0.8f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("RIVER", 0.6f, 0.9f, "core:water", "core:dirt",
                             new Color(0.3f, 0.5f, 0.9f, 1f), new Color(0.2f, 0.4f, 0.9f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("OCEAN", 0.5f, 1.0f, "core:water", "core:water",
                             new Color(0.0f, 0.3f, 0.7f, 1f), new Color(0.0f, 0.2f, 0.5f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("CORAL_REEF", 0.6f, 1.0f, "core:water", "core:sand",
                             new Color(0.3f, 0.7f, 0.9f, 1f), new Color(0.5f, 0.9f, 1.0f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("TAIGA", 0.2f, 0.6f, "core:forest_grass", "core:dirt",
                             new Color(0.5f, 0.6f, 0.5f, 1f), new Color(0.3f, 0.5f, 0.6f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("TUNDRA", 0.1f, 0.3f, "core:snow", "core:dirt",
                             new Color(0.8f, 0.8f, 0.9f, 1f), new Color(0.7f, 0.8f, 0.9f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("SAVANNA", 0.8f, 0.4f, "core:grass", "core:dirt",
                             new Color(0.9f, 0.8f, 0.5f, 1f), new Color(0.8f, 0.7f, 0.3f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("PLAINS", 0.6f, 0.5f, "core:grass", "core:dirt",
                             new Color(0.6f, 0.9f, 0.4f, 1f), new Color(0.5f, 0.8f, 0.3f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("DESERT", 1.0f, 0.1f, "core:sand", "core:sand",
                             new Color(0.95f, 0.85f, 0.5f, 1f), new Color(0.9f, 0.75f, 0.4f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("VOLCANIC", 0.9f, 0.2f, "core:stone", "core:lava",
                             new Color(0.4f, 0.2f, 0.2f, 1f), new Color(0.6f, 0.1f, 0.0f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("TEMPERATE_GRASSLAND", 0.5f, 0.4f, "core:grass", "core:dirt",
                             new Color(0.5f, 0.8f, 0.5f, 1f), new Color(0.3f, 0.6f, 0.3f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("RAINFOREST", 0.9f, 1.0f, "core:forest_grass", "core:dirt",
                             new Color(0.1f, 0.5f, 0.2f, 1f), new Color(0.0f, 0.3f, 0.2f, 1f), 0.0f, 1.0f));
    register(new SimpleBiome("FOREST", 0.5f, 0.7f, "core:forest_grass", "core:dirt",
                             new Color(0.3f, 0.6f, 0.3f, 1f), new Color(0.1f, 0.4f, 0.2f, 1f), 0.0f, 1.0f));
  }
}
