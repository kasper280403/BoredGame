package edu.ntnu.idi.idattx2002.logic.chessLogic.pieces;

import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.Square;

public class Horse extends Piece{

  public Horse(Square startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
  }

  @Override
  public boolean isMovePossible(Square square) {
    int yDiff = Math.abs(square.getYCoordinate() - currentSquare.getYCoordinate());
    int xDiff = Math.abs(square.getXCoordinate() - currentSquare.getXCoordinate());
    return (yDiff == 2 && xDiff == 1) || (xDiff == 2 && yDiff == 1);
  }

  @Override
  public boolean threatens(Square square, Chess chess) {
    return isMovePossible(square) &&  super.threatens(square, chess);
  }

  @Override
  public boolean isMoveLegal(Square square, Chess chess) {
    return isMovePossible(square) &&  super.isMoveLegal(square, chess);
  }

  @Override
  public void move(Square square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
    }
  }
}
