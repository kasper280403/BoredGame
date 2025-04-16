package edu.ntnu.idi.idattx2002.gui1.chessGui.controller;

import java.io.IOException;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.gui1.chessGui.view.ChessView;
import edu.ntnu.idi.idattx2002.gui1.chessGui.view.WinView;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.Chess;

public class ChessController {

  private Chess chess;
  private BoardController boardController;
  private ChessView chessView;
  private WinView  winView;

  public ChessController(Pane mainPane, Chess chess) throws IOException {
    chessView = new ChessView(mainPane);
    boardController = new BoardController(chess, chessView);
    winView = new WinView();
    chess.addObserver(winView);
  }

  public void showView() {
    chessView.showView();
    boardController.showView();
  }

}
