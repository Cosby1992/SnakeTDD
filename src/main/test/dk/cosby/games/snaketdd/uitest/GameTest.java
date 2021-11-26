package dk.cosby.games.snaketdd.uitest;

import dk.cosby.games.snaketdd.Initializer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static dk.cosby.games.snaketdd.GameConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class GameTest {

    private Group root;

    @Start
    void start(Stage stage) {
        Initializer initializer = new Initializer();
        root = initializer.initGame(stage);
    }

    @AfterAll
    public static void tearDown () throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    void background_should_be_visible() {
        verifyThat("#background", isVisible());
    }

    @Test
    void background_should_contain_grid_of_rectangles() {

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                String id = "#tile" + i + j;
                verifyThat(id, isVisible());
            }
        }

        int maxX = 0;
        int minX = 0;
        int maxY = 0;
        int minY = 0;

        for (Node node : root.getChildren()) {
            if(node instanceof Rectangle) {

                int x = (int) ((Rectangle) node).getX();
                int y = (int) ((Rectangle) node).getY();

                if(x > maxX) {
                    maxX = x;
                }
                if(x < minX) {
                    minX = x;
                }
                if(y > maxY) {
                    maxY = y;
                }
                if(y < minY) {
                    minY = y;
                }
            }
        }

        assertEquals((GRID_SIZE - 1) * TILE_WIDTH, maxX);
        assertEquals((GRID_SIZE - 1) * TILE_HEIGHT, maxY);
        assertEquals(0, minX);
        assertEquals(0, minY);

    }

    @Test
    void one_apple_should_be_visible() {

        verifyThat("#apple", isVisible());

        int appleCounter = 0;

        for (Node node: root.getChildren()) {
            if(node instanceof Circle) {
                appleCounter++;
                verifyThat(node, isVisible());
            }
        }

        assertEquals(appleCounter, 1);

    }

    @Test
    void apple_should_be_in_center_of_tile() {

        int applePosX = 0;
        int applePosY = 0;

        for (Node node: root.getChildren()) {
            if(node instanceof Circle) {
                applePosX = (int) ((Circle) node).getCenterX();
                applePosY = (int) ((Circle) node).getCenterY();
            }
        }

        applePosX -= (TILE_WIDTH / 2);
        applePosY -= (TILE_HEIGHT / 2);

        assertEquals(0, applePosX % TILE_WIDTH);
        assertEquals(0, applePosY % TILE_HEIGHT);

    }

    @Test
    void snake_must_be_length_3_when_game_starts() {

        int snakeLength = 0;

        for(Node node : root.getChildren()) {
            if(node instanceof Group) {
                if(((Group) node).getId() != null && ((Group) node).getId().equals("snake")) {
                    for (Node n : ((Group) node).getChildren()) {
                        snakeLength++;
                    }
                }
            }
        }

        assertEquals(3, snakeLength);

    }

    @Test
    void snake_must_be_located_in_upper_left_corner_when_game_starts() {

        int snakeMinX = 0;
        int snakeMaxX = 0;
        int snakeMinY = 0;
        int snakeMaxY = 0;

        for(Node node : root.getChildren()) {
            if(node instanceof Group) {
                if(((Group) node).getId() != null && ((Group) node).getId().equals("snake")) {
                    for (Node n : ((Group) node).getChildren()) {

                        if(n instanceof Rectangle) {

                            int x = (int) ((Rectangle) n).getX();
                            int y = (int) ((Rectangle) n).getY();

                            if(x < snakeMinX) {
                                snakeMinX = x;
                            }
                            if(x > snakeMaxX) {
                                snakeMaxX = x;
                            }
                            if(y < snakeMinY) {
                                snakeMinY = y;
                            }
                            if(y > snakeMaxY) {
                                snakeMaxY = y;
                            }

                        }
                    }
                }
            }
        }

        assertEquals(0, snakeMinX);
        assertEquals(0, snakeMinY);
        assertEquals(TILE_WIDTH * 2, snakeMaxX);
        assertEquals(0, snakeMaxY);

    }

}