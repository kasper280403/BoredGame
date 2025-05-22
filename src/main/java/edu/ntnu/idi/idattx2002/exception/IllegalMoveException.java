package edu.ntnu.idi.idattx2002.exception;


/**
 * Exception thrown to indicate that an illegal move was attempted in the game.
 * <p>
 * This exception extends {@code GameException} and is typically used when a move
 * violates the rules of the game.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class IllegalMoveException extends GameException{

  public IllegalMoveException(String message) {
    super(message);
  }
}
