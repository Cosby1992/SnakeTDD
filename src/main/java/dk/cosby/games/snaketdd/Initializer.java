package dk.cosby.games.snaketdd;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class Initializer {

    //"file:resources/dk/cosby/games/snaketdd/images/snake-logo.png"
    private final Image LOGO = new Image(Objects.requireNonNull(getClass().getResource("images/snake-logo.png")).toString());

    private Scene gameScene;
    private Group root;
    private BackgroundScene background;
    private final UI ui = new UI();
    private final Snake snake = new Snake(this, ui);
    private final Apple apple = new Apple();

    public Group initGame(Stage primaryStage) {

        primaryStage.setTitle("Snake");
        primaryStage.getIcons().add(LOGO);

        newGame();

        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        return root;
    }

    public void newGame() {

        GameUpdateHandler.unregisterGameObject(snake);
        GameUpdateHandler.unregisterGameObject(apple);
        GameUpdateHandler.unregisterGameObject(background);

        background = new BackgroundScene();

        root = background.createBackground();

        apple.createNewApple();
        root.getChildren().add(apple.getApple());
        root.getChildren().add(snake.createNewSnake());
        root.getChildren().add(ui.createUI());

        gameScene = new Scene(root);

        InputHandler.listenForKeyInput(gameScene, snake, this);

        GameUpdateHandler.registerGameObject(snake);
        GameUpdateHandler.registerGameObject(apple);
        GameUpdateHandler.registerGameObject(background);
        GameUpdateHandler.startGameLoop();
    }

    public void gameOver() {
        GameUpdateHandler.stopGame();
    }

    public void stopGame() {
        GameUpdateHandler.stopGame();
    }

    public void resumeGame() {
        GameUpdateHandler.startGameLoop();
    }


}