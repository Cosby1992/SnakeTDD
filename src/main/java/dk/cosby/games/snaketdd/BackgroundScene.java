package dk.cosby.games.snaketdd;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static dk.cosby.games.snaketdd.GameConstants.*;

/**
 * This class generates the background grid
 * Implements GameObject to make it possible to detects collisions
 */
public class BackgroundScene implements GameObject {

    public final Group backgroundGroup = new Group();

    /**
     * This method generated a grid of green squares
     * @return the grid of Rectangles in a Group
     */
    public Group createBackground() {

        backgroundGroup.setId("background");

        // Iterate through a grid of predetermined size (from GameConstants)
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                // create a new rectangle of predetermined size (from GameConstants)
                Rectangle rectangle = new Rectangle(i * TILE_WIDTH,j * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                // color the rectangles, every second rectangle is colored a slightly different color
                if((i + j) % 2 == 0) rectangle.setFill(Color.rgb(31, 179, 20));
                else rectangle.setFill(Color.rgb(36, 199, 24));
                rectangle.setId("tile" + i + j);
                backgroundGroup.getChildren().add(rectangle); // add the rectangle to the group

            }
        }

        return backgroundGroup;
    }

    // inherited method, not used for this object
    @Override
    public void update() {

    }

    // inherited method, not used for this object
    @Override
    public void checkAndHandleCollision(GameObject other) {

    }
}
