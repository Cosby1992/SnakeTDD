package dk.cosby.games.snaketdd;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

import static dk.cosby.games.snaketdd.AppleCreator.createNewApple;
import static dk.cosby.games.snaketdd.BackgroundScene.createBackground;

public class Initializer {

    //"file:resources/dk/cosby/games/snaketdd/images/snake-logo.png"
    private final Image LOGO = new Image(Objects.requireNonNull(getClass().getResource("images/snake-logo.png")).toString());

    private Group root;
    private Snake snake = new Snake();

    public Group initGame(Stage stage) {
        stage.setTitle("Snake");
        stage.getIcons().add(LOGO);
        root = createBackground();
        root.getChildren().add(createNewApple());

        root.getChildren().add(snake.createNewSnake());
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        return root;
    }
}
