package dk.cosby.games.snaketdd;

import javafx.application.Platform;

import java.util.ArrayList;

/**
 * This class handles the game-loop and
 * updates every object registered in the gameObjects array
 */
public class GameUpdateHandler {

    // gameObject contains all gameObjects influenced by the game-loop
    private static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static Thread thread;
    private static boolean run;

    /**
     * This method initializes a game-loop on a new thread
     */
    public static void startGameLoop() {

        run = true;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long lastUpdate = System.currentTimeMillis();
                long time;

                while (run) {
                    time = System.currentTimeMillis();

                    if(time - lastUpdate > 125) { // 8 ticks per second

                        // Platform.runLater is used to not block JavaFX main/ui thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                // Update each gameObject
                                for(GameObject go : gameObjects) {
                                    go.update();

                                    // Check collitions between current object and
                                    // all other objects in gameObjects
                                    for(GameObject go2 : gameObjects) {
                                        go.checkAndHandleCollision(go2);
                                    }

                                }
                            }
                        });

                        lastUpdate = time;
                    }
                }
            }
        });

        thread.start();
    }

    public static void registerGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public static void unregisterGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public static void stopGame() {
        run = false;
    }


}

