package edu.ntnu.idi.idattx2002.gui.common.view;

import edu.ntnu.idi.idattx2002.io.common.PlayerIO;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CreatePlayerWindow{

    public List<String> playerNames;

    private final Stage createPlayerStage;
    private final PlayerIO playerIO;
    private final PlayerIconWindow playerIconWindow;

    //TODO implement controller pattern
    public CreatePlayerWindow(){
        createPlayerStage = new Stage();
        playerIO = new PlayerIO();
        playerIconWindow = new PlayerIconWindow(50);
    }

    public ListView<String> getPlayersListView() {
        List<List<String>> playerList = playerIO.getPlayers();
        ListView<String> playerListView = new ListView<>();

        for (List<String> player : playerList) {
            playerListView.getItems().add(player.get(0) + " (Piece " + player.get(1) + ")");
        }

        return playerListView;
    }

    public List<String> getPlayerNames() {
        List<List<String>> playerList = playerIO.getPlayers();
        List<String> playerNames = new ArrayList<>();

        for (List<String> player : playerList) {
            playerNames.add(player.get(0));
        }

        return playerNames;
    }

    public void openPlayerInput(){
        playerNames = getPlayerNames();

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

        ListView<String> playerListView = getPlayersListView();

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
        Button goBackButton = new Button("BACK");

        ListView<String> finalPlayerListView = playerListView;
        addPlayerButton.setOnAction(e -> {
            String name = playerNameField.getText().trim();
            if (!name.isEmpty() && !playerNames.contains(name)) {
                playerNames.add(name);
                finalPlayerListView.getItems().add(name + " (Piece " + selectedPiece[0] + ")");
                playerNameField.clear();

                updateDAO(name, selectedPiece[0]);
            }
        });

        goBackButton.setOnAction(e -> goBack());

        layout.getChildren().addAll(heading, playerNameHBox, pieces, spacing, addPlayerButton, playerListView, goBackButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 500, 400);
        createPlayerStage.setScene(scene);
        createPlayerStage.show();
    }

    private HashMap<Integer, ImageView> getPiecesImg() {
        HashMap<Integer, ImageView> imageViewMap = new HashMap<>();

        imageViewMap.put(1, playerIconWindow.getImageView(1));
        imageViewMap.put(2, playerIconWindow.getImageView(2));
        imageViewMap.put(3, playerIconWindow.getImageView(3));
        imageViewMap.put(4, playerIconWindow.getImageView(4));

        return imageViewMap;
    }

    private void goBack() {
        createPlayerStage.close();
    }

    private void updateDAO(String playerName, int selectedPiece) {
        playerIO.writePlayer(playerName, selectedPiece);
    }
}
