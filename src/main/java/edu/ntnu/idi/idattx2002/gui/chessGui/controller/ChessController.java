package edu.ntnu.idi.idattx2002.gui.chessGui.controller;

import edu.ntnu.idi.idattx2002.gui.common.view.WinView;
import java.io.IOException;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.gui.chessGui.view.ChessView;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;

public class ChessController {

  private final BoardController boardController;
  private final ChessView chessView;
  private final WinView winView;

  public ChessController(Pane mainPane, Chess chess) {
    chessView = new ChessView(mainPane);
    boardController = new BoardController(chess, chessView);

    winView = new WinView(mainPane);
    chess.addObserver(winView);
  }

  public void showView() {
    chessView.showView();
    boardController.showView();
  }

}
