package dk.cosby.games.snaketdd;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

import static dk.cosby.games.snaketdd.GameConstants.*;

public class Snake implements GameObject {

    private ArrayList<Rectangle> snake = new ArrayList<>();
    private Group snakeGroup = new Group();
    private char direction = 'R';
    private char directionAtLastUpdate = 'R';
    private boolean collisionWithApple = false;
    private UIUpdater ui = null;
    private Initializer initializer;

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

    public Group createNewSnake() {

        for (int i = 0; i < 3; i++) {
            Rectangle rect = new Rectangle(i * TILE_WIDTH,0, TILE_WIDTH, TILE_HEIGHT);
            rect.setFill(Color.rgb(155, 46, 201));
            snake.add(rect);
        }

        snakeGroup.getChildren().addAll(snake);

        return snakeGroup;

    }

    public Group getSnakeGroup() {
        return snakeGroup;
    }

    public ArrayList<Rectangle> getSnake() {
        return snake;
    }

    public void setDirection(char dir) {
        if(dir == 'L' && directionAtLastUpdate != 'R') direction = 'L';
        if(dir == 'R' && directionAtLastUpdate != 'L') direction = 'R';
        if(dir == 'U' && directionAtLastUpdate != 'D') direction = 'U';
        if(dir == 'D' && directionAtLastUpdate != 'U') direction = 'D';
    }

    public char getDirection() {
        return direction;
    }

    public void move() {

        snakeGroup.getChildren().removeAll(snake);

        if(collisionWithApple) collisionWithApple = false;
        else snake.remove(0);

        Rectangle snakeHeadReference = snake.get(snake.size() - 1);
        Rectangle newSnakeHead;

        switch (direction) {
            case 'R' -> {
                newSnakeHead = new Rectangle(snakeHeadReference.getX() + TILE_WIDTH, snakeHeadReference.getY(), TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'R';
            }
            case 'L' -> {
                newSnakeHead = new Rectangle(snakeHeadReference.getX() - TILE_WIDTH, snakeHeadReference.getY(), TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'L';
            }
            case 'U' -> {
                newSnakeHead = new Rectangle(snakeHeadReference.getX(), snakeHeadReference.getY() - TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'U';
            }
            case 'D' -> {
                newSnakeHead = new Rectangle(snakeHeadReference.getX(), snakeHeadReference.getY() + TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                newSnakeHead.setFill(snakeHeadReference.getFill());
                snake.add(newSnakeHead);
                directionAtLastUpdate = 'D';
            }
        }

        snakeGroup.getChildren().addAll(snake);

        //System.out.println(snake.get(0));

    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void checkAndHandleCollision(GameObject other) {
        if(other instanceof Apple apple) {
            if(snake.get(snake.size()-1).getBoundsInLocal().contains(new Point2D(apple.getApple().getCenterX(), apple.getApple().getCenterY()))) {
                System.out.println("SNAKE: COLLISION WITH APPLE!" );
                collisionWithApple = true;
                if (ui != null) ui.incrementScore();
            }
        }

        if (other instanceof BackgroundScene background) {
            if(!(this.snake.get(this.snake.size() - 1).getBoundsInLocal().intersects(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH * (GRID_SIZE - 2), TILE_HEIGHT * (GRID_SIZE - 2)))) {
                // END GAME AND SHOW HIGHSCORE
                initializer.gameOver();
            }
        }

        if (other instanceof Snake snakeCollider) {

            Point2D snakeHeadCenter = new Point2D(snake.get(snake.size() -1).getX() + snake.get(snake.size() -1).getWidth() / 2, snake.get(snake.size() -1).getY() + snake.get(snake.size() -1).getHeight() / 2);

            for (int i = 0; i < snakeCollider.getSnake().size() - 2; i++) {
                if(snakeCollider.getSnake().get(i).getBoundsInLocal().contains(snakeHeadCenter)) {
                    initializer.gameOver();
                }
            }
        }

    }
}
