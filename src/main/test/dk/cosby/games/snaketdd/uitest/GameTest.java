package dk.cosby.games.snaketdd.uitest;

import dk.cosby.games.snaketdd.Initializer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static dk.cosby.games.snaketdd.GameConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

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
            if(node instanceof Rectangle rectangle) {

                int x = (int) rectangle.getX();
                int y = (int) rectangle.getY();

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
            if(node instanceof Circle circle) {
                applePosX = (int) circle.getCenterX();
                applePosY = (int) circle.getCenterY();
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
            if(node instanceof Group group) {
                if(group.getId() != null && group.getId().equals("snake")) {
                    for (Node n : ((Group) node).getChildren()) {
                        snakeLength++;
                    }
                }
            }
        }

        assertEquals(3, snakeLength);

    }

    @Test
    void snake_must_go_down_when_s_is_pressed(FxRobot robot) {

        robot.push(KeyCode.S);

        // waste some time, so snake has time to move
        robot.clickOn("#apple"); // <-- ONLY FOR WAISTING TIME

        int snakeMaxY = Integer.MIN_VALUE;

        for(Node node : root.getChildren()) {
            if(node instanceof Group group) {
                if(group.getId() != null && group.getId().equals("snake")) {
                    for (Node n : ((Group) node).getChildren()) {

                        if(n instanceof Rectangle) {

                            int y = (int) ((Rectangle) n).getY();

                            if(y > snakeMaxY) {
                                snakeMaxY = y;
                            }

                        }
                    }
                }
            }
        }

        assertTrue(snakeMaxY > 0);

    }

}