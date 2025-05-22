package edu.ntnu.idi.idattx2002.exception;

/**
 * Represents a custom runtime exception for game-related errors.
 * <p>
 * This exception is used to signal general issues occurring during game execution.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class GameException extends RuntimeException {

  /**
   * Constructs a new {@code GameException} with the specified detail message.
   *
   * @param message the detail message explaining the reason for the exception
   */
  public GameException(String message) {
    super(message);
  }
}
