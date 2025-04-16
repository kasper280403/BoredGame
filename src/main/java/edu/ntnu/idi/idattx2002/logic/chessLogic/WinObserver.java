package edu.ntnu.idi.idattx2002.logic.chessLogic;

import edu.ntnu.idi.idattx2002.logic.chessLogic.player.ChessPlayer;

public interface WinObserver {
  void update(ChessPlayer player);
}
