package dk.cosby.games.snaketdd.unittest;

import dk.cosby.games.snaketdd.Apple;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static dk.cosby.games.snaketdd.GameConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class AppleTest {

    @Test
    void apple_should_exist_after_creation() {
        Apple apple = new Apple();
        apple.createNewApple();

        assertEquals("apple", apple.getApple().getId());
    }

    @Test
    void apple_should_exist_within_grid() {
        Apple apple = new Apple();
        apple.createNewApple();

        assertTrue(apple.getApple().getCenterX() > 0);
        assertTrue(apple.getApple().getCenterY() > 0);
        assertTrue(apple.getApple().getCenterX() < (GRID_SIZE * TILE_WIDTH));
        assertTrue(apple.getApple().getCenterY() < (GRID_SIZE * TILE_HEIGHT));
    }

    @Test
    void move_apple_should_still_be_within_grid() {
        Apple apple = new Apple();
        apple.createNewApple();

        assertTrue(apple.getApple().getCenterX() > 0);
        assertTrue(apple.getApple().getCenterY() > 0);
        assertTrue(apple.getApple().getCenterX() < (GRID_SIZE * TILE_WIDTH));
        assertTrue(apple.getApple().getCenterY() < (GRID_SIZE * TILE_HEIGHT));
    }

    @Test
    void moveApple_should_move_apple_to_predetermined_grid_location() {
        Apple apple = new Apple();
        apple.createNewApple();

        apple.moveApple(2, 0);

        int expectedX = 2 * TILE_WIDTH + (TILE_WIDTH / 2);
        int expectedY = TILE_HEIGHT / 2;

        assertEquals(expectedX, (int) apple.getApple().getCenterX());
        assertEquals(expectedY, (int) apple.getApple().getCenterY());

    }

    @Test
    void apple_should_move_to_grid_location() {
        Apple apple = new Apple();
        apple.createNewApple();

        apple.moveApple(2, 0);

        assertEquals(50, (int) apple.getApple().getCenterX());
        assertEquals(10, (int) apple.getApple().getCenterY());
    }

    @Test
    void apple_should_move_to_grid_location_when_calling_move_apple() {
        Apple apple = new Apple();
        apple.createNewApple();

        apple.moveApple();

        assertTrue(apple.getApple().getCenterX() > 0);
        assertTrue(apple.getApple().getCenterY() > 0);
        assertTrue(apple.getApple().getCenterX() < (GRID_SIZE * TILE_WIDTH));
        assertTrue(apple.getApple().getCenterY() < (GRID_SIZE * TILE_HEIGHT));
    }

    @RepeatedTest(100)
    void apple_should_move_to_new_grid_location_when_calling_move_apple() {
        Apple apple = new Apple();
        apple.createNewApple();

        int x = (int) apple.getApple().getCenterX();
        int y = (int) apple.getApple().getCenterY();

        apple.moveApple();

        assertNotEquals(x, (int) apple.getApple().getCenterX());
        assertNotEquals(y, (int) apple.getApple().getCenterY());
    }

    @Test
    void getApple_should_return_apple() {
        Apple apple = new Apple();
        apple.createNewApple();

        assertEquals("apple", apple.getApple().getId());
    }

    @Test
    void update_should_do_nothing() {
        Apple apple = new Apple();
        apple.createNewApple();

        int expectedX = (int) apple.getApple().getCenterX();
        int expectedY = (int) apple.getApple().getCenterY();
        String expectedId = apple.getApple().getId();

        apple.update();

        assertEquals(expectedX, (int) apple.getApple().getCenterX());
        assertEquals(expectedY, (int) apple.getApple().getCenterY());
        assertEquals(expectedId, apple.getApple().getId());
    }

    @Test
    void moveApple() {
        Apple apple = new Apple();
        apple.createNewApple();

        apple.moveApple(2, 0);

        int expectedX = 2 * TILE_WIDTH + (TILE_WIDTH / 2);
        int expectedY = TILE_HEIGHT / 2;

        assertEquals(expectedX, (int) apple.getApple().getCenterX());
        assertEquals(expectedY, (int) apple.getApple().getCenterY());
    }
}