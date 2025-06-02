package io.github.creatorfromhell.entity;


import io.github.creatorfromhell.client.CameraController;
import io.github.creatorfromhell.client.InputHandler;
import io.github.creatorfromhell.client.render.entity.PlayerRenderer;
import io.github.creatorfromhell.entity.traits.Predator;
import org.jetbrains.annotations.NotNull;

/**
 * Player
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class Player extends LivingEntity implements Predator {

  private InputHandler handler;
  private CameraController cameraController;
  private boolean debug = false;

  public Player() {

    this.handler = new InputHandler(this);
    this.cameraController = new CameraController(this);
    this.renderable = new PlayerRenderer();
  }

  public InputHandler handler() {

    return handler;
  }

  public void handler(final InputHandler handler) {

    this.handler = handler;
  }

  public CameraController cameraController() {

    return cameraController;
  }

  public void cameraController(final CameraController cameraController) {

    this.cameraController = cameraController;
  }

  public boolean debug() {

    return debug;
  }

  public void debug(final boolean debug) {

    this.debug = debug;
  }

  /**
   * Update method called every game tick.
   *
   * @param delta time elapsed since last update (in seconds)
   */
  @Override
  public void update(final float delta) {
    //Nothing to implement since it's the player and not an NPC predator
  }

  /**
   * Adds a prey to the predator with a specified priority.
   *
   * @param prey     The LivingEntity to be added as prey.
   * @param priority The priority of the prey being added.
   */
  @Override
  public void addPrey(@NotNull final LivingEntity prey, final int priority) {
    //Nothing to implement since it's the player and not an NPC predator
  }

  /**
   * Removes a prey entity from this predator's list of prey.
   *
   * @param prey The LivingEntity to be removed as prey.
   */
  @Override
  public void removePrey(@NotNull final LivingEntity prey) {
    //Nothing to implement since it's the player and not an NPC predator
  }

  /**
   * Determines if this predator can hunt the specified target.
   *
   * @param target The potential prey.
   *
   * @return true if the target is a valid prey and within hunting logic.
   */
  @Override
  public boolean canHunt(@NotNull final LivingEntity target) {
    //Nothing to implement since it's the player and not an NPC predator
    return false;
  }

  /**
   * Initiates a hunt toward the given prey.
   *
   * @param target The prey entity to hunt.
   */
  @Override
  public void hunt(@NotNull final LivingEntity target) {
    //Nothing to implement since it's the player and not an NPC predator
  }
}
