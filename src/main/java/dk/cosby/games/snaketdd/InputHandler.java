package dk.cosby.games.snaketdd;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputHandler {

    private static boolean gameRunning = true;

    private static void toggleRunning() {
        gameRunning = !gameRunning;
    }

    public static void listenForKeyInput(Scene game, Snake snake, Initializer initializer) {

        game.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

            if(key.getCode() == KeyCode.W) {
                // snakey go up
                snake.setDirection('U');
            }
            if(key.getCode() == KeyCode.S) {
                // snakey go Down
                snake.setDirection('D');
            }
            if(key.getCode() == KeyCode.A) {
                // snakey go left
                snake.setDirection('L');
            }
            if(key.getCode() == KeyCode.D) {
                // snakey go right
                snake.setDirection('R');
            }
            if(key.getCode() == KeyCode.SPACE) {
                toggleRunning();

                if(!gameRunning) initializer.stopGame();
                else initializer.resumeGame();
            }
        });

    }
}
