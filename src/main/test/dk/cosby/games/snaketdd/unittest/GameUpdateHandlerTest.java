package dk.cosby.games.snaketdd.unittest;

import dk.cosby.games.snaketdd.Apple;
import dk.cosby.games.snaketdd.GameUpdateHandler;
import dk.cosby.games.snaketdd.Snake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameUpdateHandlerTest {

    @Test
    void startGameLoop_should_start_new_game_thread_which_should_be_alive() {
        GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();
        gameUpdateHandler.startGameLoop();
        Thread thread = gameUpdateHandler.getThread();

        assertTrue(thread.isAlive());
    }

    @Test
    void startGameLoop_should_set_running_to_true() {
        GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();
        gameUpdateHandler.startGameLoop();

        assertTrue(gameUpdateHandler.isRunning());
    }

    @Test
    void stopGame_should_kill_thread() {
        GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();
        gameUpdateHandler.startGameLoop();
        gameUpdateHandler.stopGame();

        assertFalse(gameUpdateHandler.isRunning());
    }

    @Test
    void gameObjects_should_not_contain_objects_at_creation() {
        GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();

        assertEquals(0, gameUpdateHandler.getGameObjects().size());
    }

    @Test
    void registerGameObject_should_contain_registered_objects() {
        GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();

        Snake snake = new Snake();
        Apple apple = new Apple();

        gameUpdateHandler.registerGameObject(snake);
        gameUpdateHandler.registerGameObject(apple);

        assertTrue(gameUpdateHandler.getGameObjects().contains(snake));
        assertTrue(gameUpdateHandler.getGameObjects().contains(apple));

    }

    @Test
    void registerGameObject_should_be_empty_when_registered_objects_are_unregistered() {
        GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();

        Snake snake = new Snake();
        Apple apple = new Apple();

        gameUpdateHandler.registerGameObject(snake);
        gameUpdateHandler.registerGameObject(apple);

        gameUpdateHandler.unregisterGameObject(snake);
        gameUpdateHandler.unregisterGameObject(apple);

        assertEquals(0, gameUpdateHandler.getGameObjects().size());
    }

    @Test
    void registerGameObject_should_contain_snake_when_apple_is_removed() {
        GameUpdateHandler gameUpdateHandler = new GameUpdateHandler();

        Snake snake = new Snake();
        Apple apple = new Apple();

        gameUpdateHandler.registerGameObject(snake);
        gameUpdateHandler.registerGameObject(apple);

        gameUpdateHandler.unregisterGameObject(apple);

        assertTrue(gameUpdateHandler.getGameObjects().contains(snake));
        assertFalse(gameUpdateHandler.getGameObjects().contains(apple));
    }
}