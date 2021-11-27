package dk.cosby.games.snaketdd;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static dk.cosby.games.snaketdd.GameConstants.*;

public class BackgroundScene implements GameObject {

    public Group backgroundGroup = new Group();

    public Group createBackground() {

        backgroundGroup.setId("background");

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Rectangle rectangle = new Rectangle(i * TILE_WIDTH,j * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                if((i + j) % 2 == 0) rectangle.setFill(Color.rgb(31, 179, 20));
                else rectangle.setFill(Color.rgb(36, 199, 24));
                rectangle.setId("tile" + i + j);
                backgroundGroup.getChildren().add(rectangle);

            }
        }

        return backgroundGroup;
    }

    public Group getBackgroundGroup() {
        return backgroundGroup;
    }

    @Override
    public void update() {

    }

    @Override
    public void checkAndHandleCollision(GameObject other) {

    }
}
