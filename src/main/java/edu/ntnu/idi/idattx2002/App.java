package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.gui.common.controller.StartMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BORED GAMES");

        StartMenuController startMenuController = new StartMenuController();

        Scene scene = new Scene(startMenuController.getMainPane(), 300, 200);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

