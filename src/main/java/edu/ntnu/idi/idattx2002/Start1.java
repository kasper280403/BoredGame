package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.MainController;
import edu.ntnu.idi.idattx2002.gui.common.Setup.ChoosePlayerWindow;
import edu.ntnu.idi.idattx2002.gui.common.Setup.CreatePlayerWindow;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start1 extends Application {

    public Stage primaryStage;
    public CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Choose game");

        Label label = new Label("Choose game:");
        Button chooseGameButton = new Button("Choose Game");
        chooseGameButton.setOnAction(e -> openGamesWindow());

        Button createPlayerButton = new Button("Create Player");
        createPlayerButton.setOnAction(e -> createPlayerWindow.openPlayerInput());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, createPlayerButton,  chooseGameButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    //TODO should be its own class
    private void openGamesWindow() {
        VBox layout = new VBox(10);
        Label heading = new Label("Choose Game");
        ChoosePlayerWindow choosePlayerWindow = new ChoosePlayerWindow(primaryStage);

        Button snakesAndLadders = new Button("Snakes and ladders");
        Button chessBtn = new Button("Chess");

        snakesAndLadders.setOnAction(e -> {
            choosePlayerWindow.selectPlayers(2);
        });
        chessBtn.setOnAction(e -> {
          try {
            startChess();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

        layout.getChildren().addAll(heading, snakesAndLadders, chessBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
    }

    public void startChess() throws IOException {
        MainController mainController = new MainController();
        Scene scene = new Scene(mainController.getView(), 320, 240);
        primaryStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

