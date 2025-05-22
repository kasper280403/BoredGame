package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.controller.common.StartMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class App extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BORED GAMES");

        StartMenuController startMenuController = new StartMenuController();

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(startMenuController.getMainPane(), screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

