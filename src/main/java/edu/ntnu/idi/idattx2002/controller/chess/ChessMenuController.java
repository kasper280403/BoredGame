package edu.ntnu.idi.idattx2002.controller.chess;

import edu.ntnu.idi.idattx2002.controller.common.ChoosePlayerController;
import edu.ntnu.idi.idattx2002.exception.GameException;
import edu.ntnu.idi.idattx2002.exception.IlliegalGameArgumentException;
import edu.ntnu.idi.idattx2002.io.chess.PositionIO;
import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.player.HumanChessPlayer;
import edu.ntnu.idi.idattx2002.view.chess.ChessMenuView;
import java.util.List;
import javafx.scene.layout.Pane;

/**
 * Controller for the chess game setup menu.
 *
 * <p>Handles player selection, start position loading, and game initialization for the chess
 * module.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class ChessMenuController {

  private final ChessMenuView chessMenuView;
  private final PositionIO positionIO;
  private final ChoosePlayerController choosePlayerController;

  public ChessMenuController(Pane mainPane) {
    positionIO = new PositionIO();
    this.chessMenuView = new ChessMenuView(this, mainPane);
    choosePlayerController = new ChoosePlayerController(chessMenuView.getMiddleBox(), 2, 2);
    choosePlayerController.showView();
  }

  public ChessMenuView getView() {
    return chessMenuView;
  }

  public List<String> getStartPositions() {
    return positionIO.getAllStartPositionEndPaths();
  }

  private void addPlayers(List<List<String>> players, Chess chess) {
    List<String> player1 = players.get(0);
    List<String> player2 = players.get(1);

    HumanChessPlayer chessPlayer1 =
        new HumanChessPlayer(
            player1.getFirst(), Integer.parseInt(player1.getLast()), ChessColor.WHITE);
    HumanChessPlayer chessPlayer2 =
        new HumanChessPlayer(
            player2.getFirst(), Integer.parseInt(player2.getLast()), ChessColor.BLACK);

    chess.addPlayer(chessPlayer1);
    chess.addPlayer(chessPlayer2);
  }

  public void startGame(Pane mainPane) {
    int amountOfPlayers = choosePlayerController.getChosenPlayers().size();

    try {
      if (amountOfPlayers >= choosePlayerController.getMinPlayers()
          && amountOfPlayers <= choosePlayerController.getMaxPlayers()) {
        Chess chess = new Chess();

        addPlayers(choosePlayerController.getChosenPlayers(), chess);

        String positionPath = chessMenuView.getGameModeSelection().getValue();
        chess.initPosition(positionPath);

        ChessController chessController = new ChessController(mainPane, chess);

        chessController.showView();
      } else {
        throw new IlliegalGameArgumentException("Wrong number of chosen players");
      }
    } catch (GameException e) {
      chessMenuView.setUserFeedback(e.getMessage());
    }
  }
}
