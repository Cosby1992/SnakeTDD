package dk.cosby.games.snaketdd;

import javafx.geometry.Point2D;
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


    @Override
    public void update() {
        // Empty since Apple should not do anything with each game-tick
    }

    /**
     * Moves the apple, this is instead of generating a new one each time the snake eats the apple
     */
    public void moveApple() {
        int applePosX = TILE_WIDTH / 2;
        int applePosY = TILE_HEIGHT / 2;

        int x;
        int y;

        while (true){
            x = ((int) (Math.random() * GRID_SIZE) * TILE_WIDTH + applePosX);
            y = ((int) (Math.random() * GRID_SIZE) * TILE_HEIGHT + applePosY);

            if(x != (int) apple.getCenterX() && y != (int) apple.getCenterY()) {
                apple.setCenterX(x);
                apple.setCenterY(y);
                break;
            }
        }

    }

    /**
     * Moves the apple, this is instead of generating a new one each time the snake eats the apple
     */
    public void moveApple(int x, int y) {
        int applePosX = TILE_WIDTH / 2;
        int applePosY = TILE_HEIGHT / 2;

        apple.setCenterX(x * TILE_WIDTH + applePosX);
        apple.setCenterY(y * TILE_HEIGHT + applePosY);
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

    public Circle getApple() {
        return apple;
    }
}
