package edu.ntnu.idi.idattx2002.logic.chessLogic.player;

import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;

public class HumanChessPlayer extends ChessPlayer {

  String name;

  public HumanChessPlayer(String name, ChessColor chessColor) {
    super(chessColor);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
