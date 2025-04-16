package edu.ntnu.idi.idattx2002.logic1.chessLogic.pieces;

import edu.ntnu.idi.idattx2002.logic1.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.board.Square;

public class Rook extends Piece{

  public boolean hasMoved;

  public Rook(Square startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
    hasMoved = false;
  }

  @Override
  public boolean isMovePossible(Square square) {
    int yDiff = square.getYCoordinate() - currentSquare.getYCoordinate();
    int xDiff = square.getXCoordinate() - currentSquare.getXCoordinate();
    return (xDiff == 0 || yDiff == 0);
  }

  @Override
  public boolean threatens(Square square, Chess chess) {
    return isMovePossible(square) && super.threatens(square, chess);
  }

  @Override
  public boolean isMoveLegal(Square square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  @Override
  public void move(Square square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
      hasMoved = true;
    }
  }
}
