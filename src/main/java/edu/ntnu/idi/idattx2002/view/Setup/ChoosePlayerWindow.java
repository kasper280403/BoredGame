package edu.ntnu.idi.idattx2002.view.Setup;

import edu.ntnu.idi.idattx2002.Modules.Player.PlayerDAO;
import edu.ntnu.idi.idattx2002.controller.SnakesAndLaddersController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChoosePlayerWindow {
    Stage primaryStage;
    PlayerDAO playerDAO;
    Integer nChoosenPlayers;
    Button startGameButton;
    ArrayList<String> choosenPlayerNames;
    ArrayList<Integer> choosenPlayerPieces;

    public ChoosePlayerWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.playerDAO = new PlayerDAO();
        this.nChoosenPlayers = 0;
        this.choosenPlayerNames = new ArrayList<>();
        this.choosenPlayerPieces = new ArrayList<>();
    }


    public void selectPlayers(Integer minimumPlayers) {

        VBox layout = new VBox(10);
        Label heading = new Label("Choose players, minimum: " + minimumPlayers);
        layout.getChildren().add(heading);

        ArrayList<ArrayList<String>> playerList = null;
        try {
            playerList = getPlayersList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (ArrayList<String> player : playerList){
            HBox playerBox = makePlayerBox(player, minimumPlayers);
            layout.getChildren().add(playerBox);
        }

        startGameButton = makeStartGameButton();

        layout.getChildren().add(startGameButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
    }


    public ArrayList<ArrayList<String>> getPlayersList() throws IOException {
        return playerDAO.getPlayers();
    }

    public String playerToString(ArrayList<String> player) {
        return player.get(0) + " (Piece " + player.get(1) + ")";
    }

    public HBox makePlayerBox(ArrayList<String> player, Integer minimumPlayers) {
        HBox box = new HBox();

        Label playerInfo = new Label(playerToString(player));
        Button chooseMe = selectButton(minimumPlayers, player);

        box.getChildren().addAll(playerInfo, chooseMe);
        return box;
    }

    public Button selectButton(Integer minimumPlayers, ArrayList<String> player){
        Button button = new Button("Unselected");
        button.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        button.setOnAction(e -> {
            if ("Unselected".equals(button.getText())) {
                button.setText("Selected");
                button.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                nChoosenPlayers++;
                addToList(player);
                changeStartButtonStatus(minimumPlayers);
            } else {
                button.setText("Unselected");
                button.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                nChoosenPlayers--;
                removeFromList(player);
                changeStartButtonStatus(minimumPlayers);
            }
        });
        return button;
    }

    public Button makeStartGameButton() {

        Button button = new Button("Start Game");
        button.setOnAction(e -> {
            new SnakesAndLaddersController(primaryStage, choosenPlayerNames, choosenPlayerPieces);
        });

        button.setDisable(true);

        return button;
    }

    public void addToList(ArrayList<String> player) {
        choosenPlayerNames.add(player.get(0));
        choosenPlayerPieces.add(Integer.parseInt(player.get(1)));
    }

    public void removeFromList(ArrayList<String> player) {
        int index = choosenPlayerNames.indexOf(player.get(0));
        choosenPlayerNames.remove(index);
        choosenPlayerPieces.remove(index);
    }

    public void changeStartButtonStatus(Integer minimumPlayers){
        if (nChoosenPlayers < minimumPlayers) {
            startGameButton.setDisable(true);
        } else {
            startGameButton.setDisable(false);
        }
    }
}
