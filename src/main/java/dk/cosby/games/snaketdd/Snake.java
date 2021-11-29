package dk.cosby.games.snaketdd;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

import static dk.cosby.games.snaketdd.GameConstants.*;

/**
 * This class contains code the handle the snake
 * It implements GameObject since the snake need
 * to update movement every game-loop and handle
 * multiple collisions
 */
public class Snake implements GameObject {

    private ArrayList<Rectangle> snake = new ArrayList<>();
    private Group snakeGroup = new Group();
    private char direction = 'R';
    private char directionAtLastUpdate = 'R';
    private boolean collisionWithApple = false;
    private UIUpdater ui = null;
    private Initializer initializer;

    // no-arg constructor
    // used in tests
    public Snake() {
        snakeGroup.setId("snake");
    }

    public Snake(Initializer initializer) {
        this.initializer = initializer;
        snakeGroup.setId("snake");
    }

    public Snake(Initializer initializer, UIUpdater ui) {
        this.initializer = initializer;
        this.ui = ui;
        snakeGroup.setId("snake");
    }

    /**
     * This method creates a new snake with a length of three rectangles
     * @return the snake as a group (of rectangles)
     */
    public Group createNewSnake() {

        for (int i = 0; i < 3; i++) {
            Rectangle rect = new Rectangle(i * TILE_WIDTH,0, TILE_WIDTH, TILE_HEIGHT);
            rect.setFill(Color.rgb(155, 46, 201));
            snake.add(rect);
        }

        snakeGroup.getChildren().addAll(snake);

        return snakeGroup;

    }

    /**
     * This method moves the snake in the direction determined by the direction char
     *
     * Instead of iterating through the entire snake, it removes the tail
     * of the snake and creates a new head for the snake.
     * This approach removes unnecessary complexity when moving the snake
     */
    public void move() {

        // removes the old snake from the group
        snakeGroup.getChildren().removeAll(snake);

        // removes the tail of the snake, if it did not collide with an apple
        if(collisionWithApple) collisionWithApple = false;
        else snake.remove(0);



        Rectangle snakeHeadReference = snake.get(snake.size() - 1);
        Rectangle newSnakeHead;

        // creates a new head for the snake using the old head as a reference.
        switch (direction) {
            case 'R' -> { // Right
                newSnakeHead = new Rectangle(snakeHeadReference.getX() + TILE_WIDTH, snakeHeadReference.getY(), TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'R';
            }
            case 'L' -> { // Left
                newSnakeHead = new Rectangle(snakeHeadReference.getX() - TILE_WIDTH, snakeHeadReference.getY(), TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'L';
            }
            case 'U' -> { // Up
                newSnakeHead = new Rectangle(snakeHeadReference.getX(), snakeHeadReference.getY() - TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'U';
            }
            case 'D' -> { // Down
                newSnakeHead = new Rectangle(snakeHeadReference.getX(), snakeHeadReference.getY() + TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'D';
            }
        }

        // Adds the new snake to the group
        snakeGroup.getChildren().addAll(snake);

        //System.out.println(snake.get(0));

    }

    /**
     * Moves the snake with each game tick
     */
    @Override
    public void update() {
        move();
    }

    /**
     * Handles collisions with other objects
     * @param other the game-object it's colliding with
     */
    @Override
    public void checkAndHandleCollision(GameObject other) {
        if(other instanceof Apple apple) {
            if(snake.get(snake.size()-1).getBoundsInLocal().contains(new Point2D(apple.getApple().getCenterX(), apple.getApple().getCenterY()))) {
                // if the snakes head is colliding with an apple
                collisionWithApple = true; // used to make the snake longer
                if (ui != null) ui.incrementScore(); // increments the score in UI
            }
        }

        if (other instanceof BackgroundScene background) {
            if(!(this.snake.get(this.snake.size() - 1).getBoundsInLocal().intersects(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH * (GRID_SIZE - 2), TILE_HEIGHT * (GRID_SIZE - 2)))) {
                // This is used to check if the snakes head has left the background
                // This means game-over (wall-hit)
                // END GAME AND SHOW HIGHSCORE
                initializer.gameOver();
            }
        }

        if (other instanceof Snake snakeCollider) {

            Point2D snakeHeadCenter = new Point2D(snake.get(snake.size() -1).getX() + snake.get(snake.size() -1).getWidth() / 2, snake.get(snake.size() -1).getY() + snake.get(snake.size() -1).getHeight() / 2);

            for (int i = 0; i < snakeCollider.getSnake().size() - 2; i++) {
                if(snakeCollider.getSnake().get(i).getBoundsInLocal().contains(snakeHeadCenter)) {
                    // If the snakes head have hit a part of it's body
                    // this means game-over
                    initializer.gameOver();
                }
            }
        }

    }

    /**
     * Updates the direction the snake should go on the next update
     * The snake is not allowed to go in the opposite direction of what
     * it is currently moving.
     * @param dir the letter-code of the direction
     *            L: Left
     *            R: Right
     *            U: Up
     *            D: Down
     */
    public void setDirection(char dir) {
        if(dir == 'L' && directionAtLastUpdate != 'R') direction = 'L';
        if(dir == 'R' && directionAtLastUpdate != 'L') direction = 'R';
        if(dir == 'U' && directionAtLastUpdate != 'D') direction = 'U';
        if(dir == 'D' && directionAtLastUpdate != 'U') direction = 'D';
    }

    /////////////// GETTERS ///////////////////

    public char getDirection() {
        return direction;
    }

    public Group getSnakeGroup() {
        return snakeGroup;
    }

    public ArrayList<Rectangle> getSnake() {
        return snake;
    }
}
