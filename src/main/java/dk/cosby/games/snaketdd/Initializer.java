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
    private GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();

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

        gameUpdateHandler = new GameUpdateHandler();

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
        gameUpdateHandler.registerGameObject(snake);
        gameUpdateHandler.registerGameObject(apple);
        gameUpdateHandler.registerGameObject(background);

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
        gameUpdateHandler = new GameUpdateHandler();

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
        gameUpdateHandler.registerGameObject(snake);
        gameUpdateHandler.registerGameObject(apple);
        gameUpdateHandler.registerGameObject(background);

        // Start the game loop
        gameUpdateHandler.startGameLoop();
    }

    public void gameOver() {
        gameUpdateHandler.stopGame();
    }

    public void stopGame() {
        gameUpdateHandler.stopGame();
    }

    public void resumeGame() {
        gameUpdateHandler.startGameLoop();
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public Snake getSnake() {
        return snake;
    }
}