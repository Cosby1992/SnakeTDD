package dk.cosby.games.snaketdd;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * This class handles the creation of a new game
 * Initializes all GameObjects and adds them to the stage
 */
public class Initializer {

    //"file:resources/dk/cosby/games/snaketdd/images/snake-logo.png"
    private final Image LOGO = new Image(Objects.requireNonNull(getClass().getResource("images/snake-logo.png")).toString());

    private Scene gameScene;
    private Group root;
    private BackgroundScene background;
    private final UI ui = new UI();
    private final Snake snake = new Snake(this, ui);
    private final Apple apple = new Apple();

    /**
     * Initializes a new game
     * @param primaryStage The stage passed from the @start method in Game.class
     * @return the Group created that contains all gameobjects like, apple, snake, background etc.
     * It only returns to make it more testable
     */
    public Group initGame(Stage primaryStage) {

        primaryStage.setTitle("Snake");
        primaryStage.getIcons().add(LOGO);

        newGame();

        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        return root;
    }

    public Group initGameWithNoGameLoop(Stage primaryStage) {
        primaryStage.setTitle("Snake");
        primaryStage.getIcons().add(LOGO);

        // removes the gameobjects from the array being iterated
        // through to handle updates and collisions
        GameUpdateHandler.unregisterGameObject(snake);
        GameUpdateHandler.unregisterGameObject(apple);
        GameUpdateHandler.unregisterGameObject(background);

        background = new BackgroundScene();

        root = background.createBackground();

        apple.createNewApple();
        // adds the game objects to the root Group
        root.getChildren().add(apple.getApple());
        root.getChildren().add(snake.createNewSnake());
        root.getChildren().add(ui.createUI());

        gameScene = new Scene(root);

        // Initializes a new input-handler
        InputHandler.listenForKeyInput(this);

        // registers all the new gameobjects to the array being iterated
        // through to handle updates and collisions
        GameUpdateHandler.registerGameObject(snake);
        GameUpdateHandler.registerGameObject(apple);
        GameUpdateHandler.registerGameObject(background);

        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        return root;
    }

    /**
     * This method resets everything to the beginning of the game
     * with all new gameobjects
     */
    public void newGame() {

        // removes the gameobjects from the array being iterated
        // through to handle updates and collisions
        GameUpdateHandler.unregisterGameObject(snake);
        GameUpdateHandler.unregisterGameObject(apple);
        GameUpdateHandler.unregisterGameObject(background);

        background = new BackgroundScene();

        root = background.createBackground();

        apple.createNewApple();
        // adds the game objects to the root Group
        root.getChildren().add(apple.getApple());
        root.getChildren().add(snake.createNewSnake());
        root.getChildren().add(ui.createUI());

        gameScene = new Scene(root);

        // Initializes a new input-handler
        InputHandler.listenForKeyInput(this);

        // registers all the new gameobjects to the array being iterated
        // through to handle updates and collisions
        GameUpdateHandler.registerGameObject(snake);
        GameUpdateHandler.registerGameObject(apple);
        GameUpdateHandler.registerGameObject(background);

        // Start the game loop
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

    public Scene getGameScene() {
        return gameScene;
    }

    public Snake getSnake() {
        return snake;
    }
}