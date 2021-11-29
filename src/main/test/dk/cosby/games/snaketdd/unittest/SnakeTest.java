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
    void snake_must_start_in_R_direction() {
        Snake snake = new Snake();
        snake.createNewSnake();

        assertEquals('R', snake.getDirection());
    }

    @Test
    void direction_must_be_set_to_R_on_input_R() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('R');

        assertEquals('R', snake.getDirection());

    }

    @Test
    void direction_must_be_set_to_U_on_input_U() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('U');

        assertEquals('U', snake.getDirection());

    }

    @Test
    void direction_must_be_set_to_D_on_input_D() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('D');

        assertEquals('D', snake.getDirection());

    }

    @Test
    void direction_must_not_be_set_to_opposite_R_to_L() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('R');
        snake.setDirection('L');

        assertEquals('R', snake.getDirection());

    }

    @Test
    void direction_must_not_be_set_to_opposite_U_to_D() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('U');
        snake.move();
        snake.setDirection('D');

        assertEquals('U', snake.getDirection());

    }

    @Test
    void direction_must_not_be_set_to_opposite_L_to_R() {
        Snake snake = new Snake();
        snake.createNewSnake();

        // Turning snake to allow for left turn
        snake.setDirection('D');
        snake.move();

        snake.setDirection('L');
        snake.move();
        snake.setDirection('R');

        assertEquals('L', snake.getDirection());

    }

    @Test
    void snake_must_move_right_when_direction_is_right() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('R');
        snake.move();

        Group snakeGroup = snake.getSnakeGroup();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;

        for(Node n : snakeGroup.getChildren()) {
            if(n instanceof Rectangle snakeBody) {
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

    @Test
    void move_must_move_snake_in_move_direction_down() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('D');
        snake.move();

        Group snakeGroup = snake.getSnakeGroup();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;

        for(Node n : snakeGroup.getChildren()) {
            if(n instanceof Rectangle snakeBody) {
                if(minX > snakeBody.getX()) minX = (int) snakeBody.getX();
                if(maxX < snakeBody.getX()) maxX = (int) snakeBody.getX();
                if(minY > snakeBody.getY()) minY = (int) snakeBody.getY();
                if(maxY < snakeBody.getY()) maxY = (int) snakeBody.getY();
            }
        }

        assertEquals(2 * TILE_WIDTH, maxX);
        assertEquals(TILE_WIDTH, minX);
        assertEquals(0, minY);
        assertEquals(TILE_HEIGHT, maxY);
    }

    @Test
    void move_must_move_snake_in_move_direction_up() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('U');
        snake.move();

        Group snakeGroup = snake.getSnakeGroup();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;

        for(Node n : snakeGroup.getChildren()) {
            if(n instanceof Rectangle snakeBody) {
                if(minX > snakeBody.getX()) minX = (int) snakeBody.getX();
                if(maxX < snakeBody.getX()) maxX = (int) snakeBody.getX();
                if(minY > snakeBody.getY()) minY = (int) snakeBody.getY();
                if(maxY < snakeBody.getY()) maxY = (int) snakeBody.getY();
            }
        }

        assertEquals(2 * TILE_WIDTH, maxX);
        assertEquals(TILE_WIDTH, minX);
        assertEquals(- TILE_HEIGHT, minY);
        assertEquals(0, maxY);
    }

    @Test
    void move_must_not_move_snake_in_opposite_direction_of_current_direction() {
        Snake snake = new Snake();
        snake.createNewSnake();

        snake.setDirection('L');
        snake.move();

        Group snakeGroup = snake.getSnakeGroup();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;

        for(Node n : snakeGroup.getChildren()) {
            if(n instanceof Rectangle snakeBody) {
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