package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

public class Horse extends Piece{

  public Horse(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
  }

  @Override
  public boolean isMovePossible(ChessSquare square) {
    int yDiff = Math.abs(square.getYCoordinate() - currentSquare.getYCoordinate());
    int xDiff = Math.abs(square.getXCoordinate() - currentSquare.getXCoordinate());
    return (yDiff == 2 && xDiff == 1) || (xDiff == 2 && yDiff == 1);
  }

  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    return isMovePossible(square) &&  super.threatens(square, chess);
  }

  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) &&  super.isMoveLegal(square, chess);
  }

  @Override
  public void move(ChessSquare square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
    }
  }
}
