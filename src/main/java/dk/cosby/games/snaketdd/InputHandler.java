package dk.cosby.games.snaketdd;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class handles input from the user (keyboard)
 */
public class InputHandler {

    private static boolean gameRunning = true;

    private static void toggleRunning() {
        gameRunning = !gameRunning;
    }

    /**
     * This method maps keyboard inputs to functionality
     * @param context
     */
    public static void listenForKeyInput(Initializer context) {

        // Attaches eventhandler to game scene
        context.getGameScene().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

            // checking the inputs W,A,S,D and space
            if(key.getCode() == KeyCode.W) {
                // snakey go up
                context.getSnake().setDirection('U');
            }
            if(key.getCode() == KeyCode.S) {
                // snakey go Down
                context.getSnake().setDirection('D');
            }
            if(key.getCode() == KeyCode.A) {
                // snakey go left
                context.getSnake().setDirection('L');
            }
            if(key.getCode() == KeyCode.D) {
                // snakey go right
                context.getSnake().setDirection('R');
            }
            if(key.getCode() == KeyCode.SPACE) {
                // SPACE is used to pause and resume the game loop
                toggleRunning();

                if(!gameRunning) context.stopGame();
                else context.resumeGame();
            }
        });

    }
}
