package edu.ntnu.idi.idattx2002.gui.common;

import edu.ntnu.idi.idattx2002.io.ladderGameIO.PlayerIO;
import java.util.List;
import javafx.scene.layout.Pane;

public class ChoosePlayerController {

  private Pane mainPane;
  private PlayerIO playerIO;
  private int nrOfPlayerChosen;
  private List<String> chosenPlayerNames;

  public ChoosePlayerController(Pane mainPane) {
    this.mainPane = mainPane;
  }

}
