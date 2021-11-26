package dk.cosby.games.snaketdd.unittest;

import dk.cosby.games.snaketdd.Snake;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static dk.cosby.games.snaketdd.GameConstants.TILE_HEIGHT;
import static dk.cosby.games.snaketdd.GameConstants.TILE_WIDTH;
import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @Test
    void createNewSnake_should_make_snake_of_three_rectangles() {

        Snake snake = new Snake();
        Group snakeGroup = snake.createNewSnake();

        int rectCounter = 0;

        for (Node n : snakeGroup.getChildren()) {
            if(n instanceof Rectangle) {
                rectCounter++;
            }
        }

        assertEquals(3, rectCounter);
    }

    @Test
    void move_must_move_snake_in_move_direction() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.move('R');

        Group snakeGroup = snake.getSnake();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;

        for(Node n : snakeGroup.getChildren()) {
            if(n instanceof Rectangle) {
                Rectangle snakeBody = ((Rectangle) n);
                if(minX > snakeBody.getX()) minX = (int) snakeBody.getX();
                if(maxX < snakeBody.getX()) maxX = (int) snakeBody.getX();
                if(minY > snakeBody.getY()) minY = (int) snakeBody.getY();
                if(maxY < snakeBody.getY()) maxY = (int) snakeBody.getY();
            }
        }

        assertEquals(3 * TILE_WIDTH, maxX);
        assertEquals(TILE_WIDTH, minX);
        assertEquals(0, minY);
        assertEquals(0, maxY);
    }
}