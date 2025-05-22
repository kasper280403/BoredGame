package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

public class Rook extends Piece{

  public boolean hasMoved;

  public Rook(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
    hasMoved = false;
  }

  @Override
  public boolean isMovePossible(ChessSquare square) {
    int yDiff = square.getYCoordinate() - currentSquare.getYCoordinate();
    int xDiff = square.getXCoordinate() - currentSquare.getXCoordinate();
    return (xDiff == 0 || yDiff == 0);
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
      hasMoved = true;
    }
  }
}
