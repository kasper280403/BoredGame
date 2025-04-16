package edu.ntnu.idi.idattx2002.logic.chessLogic.player;

import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;

public class HumanPlayer extends Player {

  String name;

  public HumanPlayer(String name, ChessColor chessColor) {
    super(chessColor);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
