package dk.cosby.games.snaketdd;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

import static dk.cosby.games.snaketdd.GameConstants.TILE_HEIGHT;
import static dk.cosby.games.snaketdd.GameConstants.TILE_WIDTH;

public class Snake {

    private Queue<Rectangle> snake = new LinkedList<>();
    private Group snakeGroup = new Group();

    public Snake() {
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


    public Group getSnake() {
        return snakeGroup;
    }

    public void move(char r) {

        Rectangle snakeEnd = snake.poll();
        snakeEnd.setX(snakeEnd.getX() + (3 * TILE_WIDTH));
        snake.add(snakeEnd);

    }
}
