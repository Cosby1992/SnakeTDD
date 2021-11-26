package dk.cosby.games.snaketdd;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Initializer initializer = new Initializer();
        initializer.initGame(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * This inner class is in place to avoid conflicts with testFX framework and java modules.
     * Without this the test framework is not allowed to access the application.
     */
    static class GameLauncher {
        public static void main(String[] args) {
            Game.main(args);
        }
    }

}