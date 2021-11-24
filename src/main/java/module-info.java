module dk.cosby.games.snaketdd {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.cosby.games.snaketdd to javafx.fxml;
    exports dk.cosby.games.snaketdd;
}