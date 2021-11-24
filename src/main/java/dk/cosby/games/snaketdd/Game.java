package dk.cosby.games.snaketdd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Game extends Application {

    //"file:resources/dk/cosby/games/snaketdd/images/snake-logo.png"
    private final Image LOGO = new Image(Objects.requireNonNull(getClass().getResource("images/snake-logo.png")).toString());

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Snake");
        stage.getIcons().add(LOGO);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}