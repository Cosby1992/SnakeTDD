package dk.cosby.games.snaketdd.unittest;

import dk.cosby.games.snaketdd.GameConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameConstantsTest {

    @Test
    void grid_size_should_be_int_over_0() {
        assertTrue(GameConstants.GRID_SIZE > 0);
    }

    @Test
    void tile_width_should_be_int_over_0() {
        assertTrue(GameConstants.TILE_WIDTH > 0);
    }

    @Test
    void tile_height_should_be_int_over_0() {
        assertTrue(GameConstants.TILE_HEIGHT > 0);
    }

}