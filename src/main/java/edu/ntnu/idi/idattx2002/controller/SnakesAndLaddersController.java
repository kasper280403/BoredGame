package edu.ntnu.idi.idattx2002.controller;

import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.view.TilesWindow;

public class SnakesAndLaddersController {

  private TilesWindow view;
  private SnakesAndLadders game;

  public SnakesAndLaddersController(TilesWindow view, SnakesAndLadders game) {
    this.view = view;
    this.game = game;
  }


}
