package dk.cosby.games.snaketdd.unittest;

import dk.cosby.games.snaketdd.Apple;
import dk.cosby.games.snaketdd.GameUpdateHandler;
import dk.cosby.games.snaketdd.Snake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameUpdateHandlerTest {

    @Test
    void startGameLoop_should_start_new_game_thread_which_should_be_alive() {
        GameUpdateHandler.startGameLoop();
        Thread thread = GameUpdateHandler.getThread();

        assertTrue(thread.isAlive());
    }

    @Test
    void startGameLoop_should_set_running_to_true() {
        GameUpdateHandler.startGameLoop();

        assertTrue(GameUpdateHandler.isRunning());
    }

    @Test
    void stopGame_should_kill_thread() {
        GameUpdateHandler.startGameLoop();
        GameUpdateHandler.stopGame();

        assertFalse(GameUpdateHandler.isRunning());
    }

    @Test
    void gameObjects_should_not_contain_objects_at_creation() {
        assertEquals(0, GameUpdateHandler.getGameObjects().size());
    }

    @Test
    void registerGameObject_should_contain_registered_objects() {

        Snake snake = new Snake();
        Apple apple = new Apple();

        GameUpdateHandler.registerGameObject(snake);
        GameUpdateHandler.registerGameObject(apple);

        assertTrue(GameUpdateHandler.getGameObjects().contains(snake));
        assertTrue(GameUpdateHandler.getGameObjects().contains(apple));

    }
}