package edu.ntnu.idi.idattx2002.logic.chessLogic.analysis;

import edu.ntnu.idi.idattx2002.logic.chessLogic.player.Player;
import java.util.HashMap;
import java.util.Map;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.*;

public class PositionEvaluator {

  private Map<Class<? extends Piece>, Double> pieceValueMap;

  private Chess chess;

  public PositionEvaluator(Chess chess) {
    this.chess = chess;
    init();
  }

  public double evaluate() {
    double evaluation = 0;

    evaluation += getMaterialScore(chess.getPlayer(ChessColor.WHITE));
    evaluation -= getMaterialScore(chess.getPlayer(ChessColor.BLACK));

    evaluation += getMobilityScore(chess.getPlayer(ChessColor.WHITE));
    evaluation -= getMobilityScore(chess.getPlayer(ChessColor.BLACK));

    return evaluation;
  }

  public double getMaterialScore(Player player) {
    double pieceScore = 0;
    for(Piece piece : player.getAlivePieces()) {
      pieceScore += pieceValueMap.get(piece.getClass());
    }

    return pieceScore;
  }

  public double getMobilityScore(Player player) {
    double mobilityScore = 0;

    for(Piece piece : player.getAlivePieces()) {
      mobilityScore += 0.1 * nrOfLegalMoves(piece);
    }

    return mobilityScore;
  }

  public int nrOfLegalMoves(Piece piece) {
    int nrOfMoves = 0;
    for(ChessSquare square : chess.getBoard().getSquareMap().values()) {
      if(piece.isMoveLegal(square, chess)) {
        nrOfMoves += 1;
      }
    }
    return nrOfMoves;
  }


  private void createPieceValueMap() {
    pieceValueMap = new HashMap<>();

    pieceValueMap.put(Pawn.class, 1.0);
    pieceValueMap.put(Horse.class, 3.0);
    pieceValueMap.put(Bishop.class, 3.0);
    pieceValueMap.put(Rook.class, 5.0);
    pieceValueMap.put(Queen.class, 9.0);
    pieceValueMap.put(King.class, 20.0);
  }

  private void init() {
    createPieceValueMap();
  }


}
