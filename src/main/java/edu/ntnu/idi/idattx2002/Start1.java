package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.gui.common.controller.StartMenuController;
import edu.ntnu.idi.idattx2002.gui.common.view.CreatePlayerWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Start1 extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BORED GAMES");

        StackPane mainPane = new StackPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.ROSYBROWN.darker().darker(), null, null)));

        StartMenuController startMenuController = new StartMenuController(mainPane);
        startMenuController.getView().show();

        Scene scene = new Scene(mainPane, 300, 200);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

