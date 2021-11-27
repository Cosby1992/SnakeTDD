package dk.cosby.games.snaketdd;

import javafx.application.Platform;

import java.util.ArrayList;

public class GameUpdateHandler {

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static Thread thread;
    private static boolean run;

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

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //System.out.println("Game Tick");
                                for(GameObject go : gameObjects) {
                                    go.update();

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

