package io.github.creatorfromhell.util;


import com.badlogic.gdx.graphics.Color;
import io.github.creatorfromhell.world.Biome;

/**
 * BiomeColors
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */

public class BiomeColors {

    public static Color getBaseColor(final Biome biome) {
        return switch (biome) {
            case PLAINS -> new Color(0.6f, 0.9f, 0.4f, 1f);
            case TUNDRA -> new Color(0.8f, 0.8f, 0.8f, 1f);
            case JUNGLE -> new Color(0.2f, 0.6f, 0.2f, 1f);
            case DESERT -> new Color(0.8f, 0.7f, 0.4f, 1f);
            default -> Color.WHITE;
        };
    }

    /** Interpolates a biome color based on height (adds green with altitude). */
    public static Color getTintedColor(final Biome biome, final float height) {
        final Color base = getBaseColor(biome);
        final float mix = Math.min(Math.max(height + 0.2f, 0f), 1f); // clamp height
        // Blend green by elevation
        return base.cpy().lerp(0.2f, 0.8f, 0.2f, 1f, mix);
    }
}
