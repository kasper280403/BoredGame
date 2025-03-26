package edu.ntnu.idi.idattx2002.controller;

import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.view.TilesWindow;

public class TilesController {

  private TilesWindow view;
  private SnakesAndLadders game;

  public TilesController(TilesWindow view, SnakesAndLadders game) {
    this.view = view;
    this.game = game;
  }


}
