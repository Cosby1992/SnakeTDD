package dk.cosby.games.snaketdd;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Main class, launches the application
 */
public class Game extends Application {

    @Override
    public void start(Stage stage) {
        // Fills the primary stage with all the game entities
        // and starts the game loop
        Initializer initializer = new Initializer();
        initializer.initGame(stage);
    }

    @Override
    public void stop(){
        // Kills all game processes and Exits with code 0
        Platform.exit();
        System.exit(0);
    }

    /**
     * Launches the application
     * @param args arguments for the program, not used
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * This inner class is in place to avoid conflicts with testFX framework and java modules.
     * Without this class the test framework is not allowed to access the application.
     */
    static class GameLauncher {
        public static void main(String[] args) {
            Game.main(args);
        }
    }

}