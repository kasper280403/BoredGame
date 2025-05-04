package edu.ntnu.idi.idattx2002.gui.chessGui.controller;

import edu.ntnu.idi.idattx2002.gui.chessGui.view.PlayerInfoView;
import edu.ntnu.idi.idattx2002.gui.common.view.WinView2;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import java.io.IOException;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.gui.chessGui.view.ChessView;
import edu.ntnu.idi.idattx2002.gui.chessGui.view.WinView;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import javafx.scene.layout.VBox;

public class ChessController {

  private Chess chess;
  private BoardController boardController;
  private ChessView chessView;
  private WinView2 winView;

  public ChessController(Pane mainPane, Chess chess) throws IOException {
    this.chess = chess;
    chessView = new ChessView(mainPane);
    boardController = new BoardController(chess, chessView);
    winView = new WinView2(mainPane);
    chess.addObserver(winView);
  }

  public void showView() {
    //chessView.getChildren().add(new PlayerInfoView(chess.getPlayer(ChessColor.BLACK)));
    chessView.showView();
    boardController.showView();
  }

}
