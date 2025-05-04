package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.gui.common.controller.StartMenuController;
import edu.ntnu.idi.idattx2002.gui.common.view.CreatePlayerWindow;
import edu.ntnu.idi.idattx2002.gui.common.view.MainView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.iq80.snappy.Main;

public class Start1 extends Application {

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

