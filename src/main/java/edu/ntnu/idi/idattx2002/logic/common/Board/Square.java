package edu.ntnu.idi.idattx2002.logic.common.Board;

public abstract class Square {

  public int squareId;

  public Square(int squareId) {
    validateSquareId(squareId);
    this.squareId = squareId;
  }

    private void validateSquareId(int squareId) {
      if (squareId < 0) {
        throw new IllegalArgumentException("SquareId has to be an positive integer");
      }
    }

    public int getSquareId() {
      return squareId;
    }
  }

