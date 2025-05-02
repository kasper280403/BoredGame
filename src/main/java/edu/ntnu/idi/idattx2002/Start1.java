package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.ChessMenuController;
import edu.ntnu.idi.idattx2002.gui.common.controller.ChoosePlayerController;
import edu.ntnu.idi.idattx2002.gui.common.view.CreatePlayerWindow;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersMenuController;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersMenuView;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start1 extends Application {

    public Stage primaryStage;
    private StackPane mainPane;
    public CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Choose game");

        Label label = new Label("Choose game:");
        Button chooseGameButton = new Button("Choose Game");
        chooseGameButton.setOnAction(e -> {
          try {
            openGamesWindow();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

        Button createPlayerButton = new Button("Create Player");
        createPlayerButton.setOnAction(e -> createPlayerWindow.openPlayerInput());

        mainPane = new StackPane();
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, createPlayerButton,  chooseGameButton);
        mainPane.getChildren().add(layout);

        Scene scene = new Scene(mainPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    //TODO should be its own class
    private void openGamesWindow() throws IOException {
        VBox layout = new VBox(10);
        Label heading = new Label("Choose Game");

        Button snakesAndLaddersBtn = new Button("Snakes and ladders");
        Button chessBtn = new Button("Chess");

        snakesAndLaddersBtn.setOnAction(e -> {
            try {
                startSnakesAndLadders();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        chessBtn.setOnAction(e -> {
          try {
            startChess();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

        mainPane.getChildren().clear();
        layout.getChildren().addAll(heading, snakesAndLaddersBtn, chessBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        mainPane.getChildren().add(layout);
    }

    public void startChess() throws IOException {
        ChessMenuController mainController = new ChessMenuController();

        mainPane.getChildren().clear();
        mainPane.getChildren().add(mainController.getView());
    }

    public void startSnakesAndLadders() throws IOException {
        SnakesAndLaddersMenuController mainController = new SnakesAndLaddersMenuController();

        mainPane.getChildren().clear();
        mainPane.getChildren().add(mainController.getView());
    }



    public static void main(String[] args) {
        launch(args);
    }
}

