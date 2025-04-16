package edu.ntnu.idi.idattx2002.logic1.chessLogic.pieces;

import edu.ntnu.idi.idattx2002.logic1.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.board.Square;


public class Bishop extends Piece{

  public Bishop(Square startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
  }

  @Override
  public boolean isMovePossible(Square square) {
    int yDiff = Math.abs(square.getYCoordinate() - currentSquare.getYCoordinate());
    int xDiff = Math.abs(square.getXCoordinate() - currentSquare.getXCoordinate());
    return xDiff == yDiff;
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
