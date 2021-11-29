package dk.cosby.games.snaketdd.integrationtest;

import dk.cosby.games.snaketdd.Apple;
import dk.cosby.games.snaketdd.Snake;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AppleCollisionTest {

    @RepeatedTest(100)
    void apple_should_move_when_colliding_with_snake_in_same_position_as_apple() {
        Apple apple = new Apple();
        apple.createNewApple();

        Snake snake = new Snake();
        snake.createNewSnake();

        apple.moveApple(2,0);

        double appleCenterX = apple.getApple().getCenterX();
        double appleCenterY = apple.getApple().getCenterY();

        apple.checkAndHandleCollision(snake);

        assertNotEquals((int) appleCenterX, (int) apple.getApple().getCenterX());
        assertNotEquals((int) appleCenterY, (int) apple.getApple().getCenterY());

    }





}
