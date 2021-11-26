package dk.cosby.games.snaketdd;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static dk.cosby.games.snaketdd.GameConstants.*;

public class AppleCreator {

    public static Circle createNewApple() {

        int applePosX = TILE_WIDTH / 2;
        int applePosY = TILE_HEIGHT / 2;

        int x = ((int) (Math.random() * GRID_SIZE) * TILE_WIDTH + applePosX);
        int y = ((int) (Math.random() * GRID_SIZE) * TILE_HEIGHT + applePosY);

        Circle apple = new Circle(x, y, applePosX, Color.RED);

        apple.setId("apple");

        return apple;

    }


}
