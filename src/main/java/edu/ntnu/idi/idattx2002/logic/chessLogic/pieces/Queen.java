package edu.ntnu.idi.idattx2002.logic.chessLogic.pieces;

import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;

public class Queen extends Piece{

  public Queen(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
  }

  @Override
  public boolean isMovePossible(ChessSquare square) {
    int yDiff = Math.abs(square.getYCoordinate() - currentSquare.getYCoordinate());
    int xDiff = Math.abs(square.getXCoordinate() - currentSquare.getXCoordinate());
    return xDiff == yDiff || xDiff == 0 || yDiff == 0;
  }

  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.threatens(square, chess);
  }

  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  @Override
  public void move(ChessSquare square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
    }
  }
}
