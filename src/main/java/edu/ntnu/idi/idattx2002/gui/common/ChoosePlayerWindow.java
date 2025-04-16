package edu.ntnu.idi.idattx2002.gui.common;

import edu.ntnu.idi.idattx2002.io.ladderGameIO.PlayerIO;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChoosePlayerWindow {
    Stage primaryStage;
    PlayerIO playerIO;
    Integer nChoosenPlayers;
    Button startGameButton;
    ArrayList<String> choosenPlayerNames;
    ArrayList<Integer> choosenPlayerPieces;

    //TODO implement controller pattern
    public ChoosePlayerWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.playerIO = new PlayerIO();
        this.nChoosenPlayers = 0;
        this.choosenPlayerNames = new ArrayList<>();
        this.choosenPlayerPieces = new ArrayList<>();
    }

    public void selectPlayers(Integer minimumPlayers, Integer maximumPlayers) {

        VBox layout = new VBox(10);
        Label heading1 = new Label("Choose players, minimum: " + minimumPlayers);
        Label heading2 = new Label("Choose players, maximum: " + maximumPlayers);
        layout.getChildren().addAll(heading1, heading2);

        ArrayList<ArrayList<String>> playerList = null;
        try {
            playerList = getPlayersList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (ArrayList<String> player : playerList){
            HBox playerBox = makePlayerBox(player, minimumPlayers, maximumPlayers);
            layout.getChildren().add(playerBox);
        }

        startGameButton = makeStartGameButton();

        layout.getChildren().add(startGameButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
    }


    //Factored out
    public ArrayList<ArrayList<String>> getPlayersList() throws IOException {
        return playerIO.getPlayers();
    }

    //Unsure if should stay
    public String playerToString(ArrayList<String> player) {
        return player.get(0) + " (Piece " + player.get(1) + ")";
    }

    //Remove max and min?
    public HBox makePlayerBox(ArrayList<String> player, int minimumPlayers, int maximumPlayers) {
        HBox box = new HBox();

        Label playerInfo = new Label(playerToString(player));
        Button chooseMe = createSelectPlayerBtn(minimumPlayers, maximumPlayers, player);

        box.getChildren().addAll(playerInfo, chooseMe);

        return box;
    }

    public Button createSelectPlayerBtn(int minimumPlayers, int maximumPlayers, ArrayList<String> player){
        Button button = new Button("Unselected");
        button.setBackground(Background.fill(Color.RED));
        button.setTextFill(Color.WHITESMOKE);

        button.setOnAction(e -> {
            if ("Unselected".equals(button.getText())) {
                button.setText("Selected");
                button.setBackground(Background.fill(Color.GREEN));
                button.setTextFill(Color.WHITESMOKE);

                nChoosenPlayers++;
                addToList(player);
                changeStartButtonStatus(minimumPlayers, maximumPlayers);
            } else {
                button.setText("Unselected");
                button.setBackground(Background.fill(Color.RED));
                button.setTextFill(Color.WHITESMOKE);
                nChoosenPlayers--;
                removeFromList(player);
                changeStartButtonStatus(minimumPlayers, maximumPlayers);
            }
        });
        return button;
    }

    //Moved Action
    public Button makeStartGameButton() {

        Button button = new Button("Start Game");
        button.setOnAction(e -> {
            new SnakesAndLaddersController(primaryStage, choosenPlayerNames, choosenPlayerPieces);
        });

        button.setDisable(true);

        return button;
    }

    //Moved
    public void addToList(ArrayList<String> player) {
        choosenPlayerNames.add(player.get(0));
        choosenPlayerPieces.add(Integer.parseInt(player.get(1)));
    }

    //Moved
    public void removeFromList(ArrayList<String> player) {
        int index = choosenPlayerNames.indexOf(player.get(0));
        choosenPlayerNames.remove(index);
        choosenPlayerPieces.remove(index);
    }

    //Moved but not finished
    public void changeStartButtonStatus(Integer minimumPlayers, Integer maximumPlayers){
        if (nChoosenPlayers < minimumPlayers || nChoosenPlayers > maximumPlayers) {
            startGameButton.setDisable(true);
        } else {
            startGameButton.setDisable(false);
        }
    }
}
