package edu.ntnu.idi.idattx2002.gui.common;

import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersController;
import edu.ntnu.idi.idattx2002.io.ladderGameIO.PlayerIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ChoosePlayerController {

  private Pane mainPane;
  private ChoosePlayerView choosePlayerView;

  private PlayerIO playerIO;

  private List<String> chosenPlayerNames;
  private List<Integer> chosenPlayerPieces;

  //TODO remove nr and just check size of array
  private int nrOfPlayerChosen;
  private int minPlayers;
  private int maxPlayers;

  public ChoosePlayerController(Pane mainPane, int minPlayers, int maxPlayers) throws IOException {
    this.mainPane = mainPane;
    choosePlayerView = new ChoosePlayerView(mainPane, this);

    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;

    //Init method?
    chosenPlayerNames = new ArrayList<>();
    chosenPlayerPieces = new ArrayList<>();
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public int getMinPlayers() {
    return minPlayers;
  }

  public ArrayList<ArrayList<String>> getPlayerList() throws IOException {
    return playerIO.getPlayers();
  }

  public void handlePlayerBtnClick(ArrayList<String> player, Button btn) {
    if ("Unselected".equals(btn.getText())) {
      btn.setText("Selected");
      btn.setBackground(Background.fill(Color.GREEN));
      btn.setTextFill(Color.WHITESMOKE);

      addToList(player);
      updateStartBtn();
    }
    else {
      btn.setText("Unselected");
      btn.setBackground(Background.fill(Color.RED));
      btn.setTextFill(Color.WHITESMOKE);

      removeFromList(player);
      updateStartBtn();
    }


  }

  public void addToList(ArrayList<String> player) {
    chosenPlayerNames.add(player.get(0));
    chosenPlayerPieces.add(Integer.parseInt(player.get(1)));
    nrOfPlayerChosen ++;
  }

  public void removeFromList(ArrayList<String> player) {
    int playerIndex = chosenPlayerNames.indexOf(player.get(0));
    chosenPlayerNames.remove(playerIndex);
    chosenPlayerPieces.remove(playerIndex);
    nrOfPlayerChosen ++;
  }

  //Add get methods for btn
  public void updateStartBtn() {
    if (nrOfPlayerChosen < minPlayers || nrOfPlayerChosen > maxPlayers) {
      choosePlayerView.getStartBtn().setDisable(true);
    } else {
      choosePlayerView.getStartBtn().setDisable(false);
    }
  }

  //Make sNlCont take Pane?
  public void startGame() {
    //new SnakesAndLaddersController(primaryStage, chosenPlayerNames, chosenPlayerPieces);
  }

  public void showView() {

  }

}
