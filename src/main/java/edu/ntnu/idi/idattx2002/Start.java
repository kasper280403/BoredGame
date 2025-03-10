package edu.ntnu.idi.idattx2002;

import edu.ntnu.idi.idattx2002.view.SnakesAndLadderWindow;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static edu.ntnu.idi.idattx2002.view.PieceWindow.setImageSize;

public class Start extends Application {

    private Stage primaryStage;
    private List<String> playerNames = new ArrayList<>();
    private List<Integer> playerPieces = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Chose game");

        Label label = new Label("Chose game:");
        Button snakesAndLaddersButton = new Button("Snakes and Ladders");
        snakesAndLaddersButton.setOnAction(e -> openPlayerInput());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, snakesAndLaddersButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Må refaktoreres litt her, denne kan være generell for flere spill men kan ikke kalle på startsnakeAndladders herifra
    private void openPlayerInput() {
        VBox layout = new VBox(10);
        Label heading = new Label("Add players!");
        Region spacing = new Region();
        spacing.setMinHeight(20);

        Label playerNameLabel = new Label("Player name:");
        TextField playerNameField = new TextField();
        HBox playerNameHBox = new HBox(20);
        playerNameHBox.setAlignment(Pos.CENTER);
        playerNameHBox.getChildren().addAll(playerNameLabel, playerNameField);


        HBox pieces = new HBox(10);
        ListView<String> playerListView = new ListView<>();
        HashMap<Integer, ImageView> images = getPiecesImg();
        HashMap<Integer, Button> pieceButtons = new HashMap<>();

        final int[] selectedPiece = {1};

        for (Integer key : images.keySet()) {
            VBox pieceBox = new VBox(10);
            Label imgTxt = new Label("Piece " + key);
            Button selectPieceButton = new Button("Pick me");

            selectPieceButton.setOnAction(e -> {
                selectedPiece[0] = key;
                pieceButtons.values().forEach(b -> b.setDisable(false));
                selectPieceButton.setDisable(true);
            });

            pieceBox.getChildren().addAll(images.get(key), imgTxt, selectPieceButton);
            pieces.getChildren().add(pieceBox);
            pieceButtons.put(key, selectPieceButton);
        }

        pieces.setAlignment(Pos.CENTER);

        Button addPlayerButton = new Button("Add player");
        Button startGameButton = new Button("Start game");
        startGameButton.setDisable(true);

        addPlayerButton.setOnAction(e -> {
            String name = playerNameField.getText().trim();
            if (!name.isEmpty() && !playerNames.contains(name)) {
                playerNames.add(name);
                playerPieces.add(selectedPiece[0]);
                playerListView.getItems().add(name + " (Piece " + selectedPiece[0] + ")");
                playerNameField.clear();
            }
            if (playerNames.size() >= 2) {
                startGameButton.setDisable(false);
            }
        });

        startGameButton.setOnAction(e -> startSnakesAndLadders());

        layout.getChildren().addAll(heading, playerNameHBox, pieces, spacing, addPlayerButton, playerListView, startGameButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
    }

    private void startSnakesAndLadders() {
        new SnakesAndLadderWindow(primaryStage, playerNames, playerPieces);
    }

    public HashMap<Integer, ImageView> getPiecesImg() {
        HashMap<Integer, ImageView> imageViewMap = new HashMap<>();
        Image pieceImage1 = new Image("/images/pieces/frogPiece.png");
        Image pieceImage2 = new Image("/images/pieces/catPiece.png");
        Image pieceImage3 = new Image("/images/pieces/punkPiece.png");
        Image pieceImage4 = new Image("/images/pieces/filipPiece.png");

        ImageView imageView1 = new ImageView(pieceImage1);
        ImageView imageView2 = new ImageView(pieceImage2);
        ImageView imageView3 = new ImageView(pieceImage3);
        ImageView imageView4 = new ImageView(pieceImage4);

        double imageViewSize = 100;
        setImageSize(imageViewSize, imageView1);
        setImageSize(imageViewSize, imageView2);
        setImageSize(imageViewSize, imageView3);
        setImageSize(imageViewSize, imageView4);

        imageViewMap.put(1, imageView1);
        imageViewMap.put(2, imageView2);
        imageViewMap.put(3, imageView3);
        imageViewMap.put(4, imageView4);

        return imageViewMap;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

