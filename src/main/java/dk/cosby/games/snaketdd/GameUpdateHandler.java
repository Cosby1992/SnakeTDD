package dk.cosby.games.snaketdd;

import javafx.application.Platform;

import java.util.ArrayList;

/**
 * This class handles the game-loop and
 * updates every object registered in the gameObjects array
 */
public class GameUpdateHandler {

    // gameObject contains all gameObjects influenced by the game-loop
    private static final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static Thread thread;
    private static boolean running;

    /**
     * This method initializes a game-loop on a new thread
     */
    public static void startGameLoop() {

        running = true;

        thread = new Thread(() -> {
            long lastUpdate = System.currentTimeMillis();
            long time;

            while (running) {
                time = System.currentTimeMillis();

                if(time - lastUpdate > 125) { // 8 ticks per second

                    // Platform.runLater is used to not block JavaFX main/ui thread
                    Platform.runLater(() -> {

                        // Update each gameObject
                        for(GameObject go : gameObjects) {
                            go.update();

                            // Check collisions between current object and
                            // all other objects in gameObjects
                            for(GameObject go2 : gameObjects) {
                                go.checkAndHandleCollision(go2);
                            }

                        }
                    });

                    lastUpdate = time;
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
        running = false;
    }

    public static Thread getThread() {
        return thread;
    }

    public static boolean isRunning() {
        return running;
    }

    public static ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}

