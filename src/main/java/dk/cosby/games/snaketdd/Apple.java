package dk.cosby.games.snaketdd;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static dk.cosby.games.snaketdd.GameConstants.*;

/**
 * This class handles code to generate an "apple" for the snake to eat
 */
public class Apple implements GameObject {

    private Circle apple;

    /**
     * Creates a new apple on a random location within the game window
     */
    public void createNewApple() {
        int applePosX = TILE_WIDTH / 2;
        int applePosY = TILE_HEIGHT / 2;

        int x = ((int) (Math.random() * GRID_SIZE) * TILE_WIDTH + applePosX);
        int y = ((int) (Math.random() * GRID_SIZE) * TILE_HEIGHT + applePosY);

        Circle apple = new Circle(x, y, applePosX, Color.RED);

        apple.setId("apple");

        this.apple = apple;
    }

    public Circle getApple() {
        return apple;
    }

    @Override
    public void update() {
        // Empty since apple should not do anything with each game-tick
    }

    /**
     * Moves the apple, this is instead of generating a new one each time the snake eats the apple
     */
    private void moveApple() {
        int applePosX = TILE_WIDTH / 2;
        int applePosY = TILE_HEIGHT / 2;

        int x = ((int) (Math.random() * GRID_SIZE) * TILE_WIDTH + applePosX);
        int y = ((int) (Math.random() * GRID_SIZE) * TILE_HEIGHT + applePosY);

        apple.setCenterX(x);
        apple.setCenterY(y);
    }

    /**
     * Checks collisions with this object
     * @param other The object that is colliding with
     */
    @Override
    public void checkAndHandleCollision(GameObject other) {
        if(other instanceof Snake snake) {
            if(snake.getSnake().get(snake.getSnake().size() - 1).getBoundsInLocal().contains(new Point2D(this.apple.getCenterX(), this.apple.getCenterY()))) {
                // if the collision is with a snake
                // Checked by seeing if the snakes head contains the center point of the apple
                moveApple();
            }
        }
    }
}
