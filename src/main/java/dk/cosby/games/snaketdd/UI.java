package dk.cosby.games.snaketdd;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static dk.cosby.games.snaketdd.GameConstants.*;

/**
 * This class handles the UI for the game
 * Implements UI adapter to make it possible for other
 * classes to update the UI
 */
public class UI implements UIUpdater {

    private Group ui = new Group();
    private int points = 0;
    private Text pointsText = new Text();

    /**
     * This method creates the UI, this is currently only text
     * describing points collected through the game
     * @return
     */
    public Group createUI() {

        pointsText.setText(String.valueOf(points));
        pointsText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pointsText.setX(((TILE_WIDTH * GRID_SIZE) / 2) - (pointsText.getLayoutBounds().getWidth() / 2));
        pointsText.setY(0.5 * TILE_HEIGHT * 3);
        pointsText.setFill(Color.rgb(255,255,255));

        ui.getChildren().add(pointsText);

        return ui;
    }

    private void incrementPoints() {
        points++;
        pointsText.setText(String.valueOf(points));
    }

    private void resetPoints() {
        points = 0;
        pointsText.setText(String.valueOf(points));
    }

    @Override
    public void incrementScore() {
        incrementPoints();
    }
}
