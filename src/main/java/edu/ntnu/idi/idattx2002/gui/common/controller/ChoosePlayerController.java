package edu.ntnu.idi.idattx2002.gui.common.controller;

import edu.ntnu.idi.idattx2002.gui.common.view.ChoosePlayerView;
import edu.ntnu.idi.idattx2002.gui.common.view.CreatePlayerWindow;
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

  private List<List<String>> chosenPlayers;

  private int minPlayers;
  private int maxPlayers;

  public ChoosePlayerController(Pane mainPane, int minPlayers, int maxPlayers) throws IOException {
    this.mainPane = mainPane;

    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;

    //Init method?
    chosenPlayers = new ArrayList<>();
    playerIO = new PlayerIO();

    choosePlayerView = new ChoosePlayerView(mainPane, this);
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

  public List<List<String>> getChosenPlayers() {
    return chosenPlayers;
  }

  public void handlePlayerBtnClick(ArrayList<String> player, Button btn) {
    if ("Unselected".equals(btn.getText())) {
      btn.setText("Selected");
      btn.setBackground(Background.fill(Color.GREEN));
      btn.setTextFill(Color.WHITESMOKE);

      addToList(player);
    }
    else {
      btn.setText("Unselected");
      btn.setBackground(Background.fill(Color.RED));
      btn.setTextFill(Color.WHITESMOKE);

      removeFromList(player);
    }


  }

  public void openCreatePlayerWindow() {
    CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();
    createPlayerWindow.openPlayerInput();
  }

  public void addToList(ArrayList<String> player) {
    chosenPlayers.add(player);
  }

  public void removeFromList(ArrayList<String> player) {
    chosenPlayers.remove(player);
  }

  public void showView() {
    choosePlayerView.show();
  }

}
