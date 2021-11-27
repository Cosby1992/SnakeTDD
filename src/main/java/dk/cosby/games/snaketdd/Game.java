package dk.cosby.games.snaketdd;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Game extends Application {


    @Override
    public void start(Stage stage) {
        Initializer initializer = new Initializer();
        initializer.initGame(stage);
    }

    @Override
    public void stop(){
        Platform.exit();
        System.exit(0);
    }

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