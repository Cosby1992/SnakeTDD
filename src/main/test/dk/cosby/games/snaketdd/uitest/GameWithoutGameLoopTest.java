package dk.cosby.games.snaketdd.uitest;

import dk.cosby.games.snaketdd.Initializer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static dk.cosby.games.snaketdd.GameConstants.TILE_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class GameWithoutGameLoopTest {

    private Group root;

    @Start
    void start(Stage stage) {
        Initializer initializer = new Initializer();
        root = initializer.initGameWithNoGameLoop(stage);
    }

    @AfterAll
    public static void tearDown () throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    void snake_must_be_located_in_upper_left_corner_when_game_starts() {

        int snakeMinX = 0;
        int snakeMaxX = 0;
        int snakeMinY = 0;
        int snakeMaxY = 0;

        for(Node node : root.getChildren()) {
            if(node instanceof Group group) {
                if(group.getId() != null && group.getId().equals("snake")) {
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
