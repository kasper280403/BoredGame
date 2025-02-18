package edu.ntnu.idi.idattx2002.view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snakes and ladders");

        BorderPane root = new BorderPane();

        TilesWindow tilesWindow = new TilesWindow();
        root.setCenter(TilesWindow.getBoard(0, 0));


        // Scene and window
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


