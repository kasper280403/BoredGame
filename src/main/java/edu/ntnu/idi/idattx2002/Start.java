package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.view.ChoosePlayerWindow;
import edu.ntnu.idi.idattx2002.view.PieceWindow;
import edu.ntnu.idi.idattx2002.view.CreatePlayerWindow;
import edu.ntnu.idi.idattx2002.view.SnakesAndLadderWindow;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Start extends Application {

    public Stage primaryStage;
    public CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();

    //Remove
    PieceWindow pieceWindow = new PieceWindow();

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


    private void openGamesWindow() {
        VBox layout = new VBox(10);
        Label heading = new Label("Choose Game");
        ChoosePlayerWindow choosePlayerWindow = new ChoosePlayerWindow(primaryStage);


        Button snakesAndLadders = new Button("Snakes and ladders");



        snakesAndLadders.setOnAction(e -> {
            choosePlayerWindow.selectPlayers(2);
        });

        layout.getChildren().addAll(heading, snakesAndLadders);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

