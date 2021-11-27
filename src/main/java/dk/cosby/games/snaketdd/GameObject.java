package dk.cosby.games.snaketdd;

public interface GameObject {

    void update();

    void checkAndHandleCollision(GameObject other);

}
