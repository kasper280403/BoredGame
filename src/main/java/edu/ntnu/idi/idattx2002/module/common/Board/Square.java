package edu.ntnu.idi.idattx2002.module.common.Board;


/**
 * Represents an abstract square on a board with a unique id.
 * <p>
 * Subclasses should define specific behaviors or properties for different types of squares.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public abstract class Square {

  public int squareId;

  /**
   * Constructs a new Square with the given square ID.
   *
   * @param squareId the unique identifier of the square; must be a positive integer
   * @throws IllegalArgumentException if {@code squareId} is negative
   */

  public Square(int squareId) {
    validateSquareId(squareId);
    this.squareId = squareId;
  }

    private void validateSquareId(int squareId) {
      if (squareId < 0) {
        throw new IllegalArgumentException("SquareId has to be an positive integer");
      }
    }

  /**
   * Returns the unique identifier of this square.
   *
   * @return the square ID
   */
    public int getSquareId() {
      return squareId;
    }
  }

