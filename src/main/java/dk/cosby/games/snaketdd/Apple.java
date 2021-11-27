package dk.cosby.games.snaketdd;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static dk.cosby.games.snaketdd.GameConstants.*;

public class Apple implements GameObject {

    private Circle apple;

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

    }

    private void moveApple() {
        int applePosX = TILE_WIDTH / 2;
        int applePosY = TILE_HEIGHT / 2;

        int x = ((int) (Math.random() * GRID_SIZE) * TILE_WIDTH + applePosX);
        int y = ((int) (Math.random() * GRID_SIZE) * TILE_HEIGHT + applePosY);

        apple.setCenterX(x);
        apple.setCenterY(y);
    }

    @Override
    public void checkAndHandleCollision(GameObject other) {
        if(other instanceof Snake snake) {
            if(snake.getSnake().get(snake.getSnake().size() - 1).getBoundsInLocal().contains(new Point2D(this.apple.getCenterX(), this.apple.getCenterY()))) {
                System.out.println("APPLE: COLLISION WITH SNAKE!" );
                moveApple();
            }
        }
    }
}
