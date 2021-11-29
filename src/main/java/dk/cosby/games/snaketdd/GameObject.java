package dk.cosby.games.snaketdd;

/**
 * Interface that makes it possible to handle collisions and
 * update entities on game tick
 */
public interface GameObject {

    /**
     * Called every game-tick
     */
    void update();

    /**
     * called every game-tick
     * @param other the game-object it's colliding with
     */
    void checkAndHandleCollision(GameObject other);

}
