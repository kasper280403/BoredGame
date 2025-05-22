package edu.ntnu.idi.idattx2002.exception;

/**
 * Exception thrown to indicate that an illegal argument was passed to a game method.
 *
 * <p>Typically used to enforce constraints on game logic and input validation.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class IlliegalGameArgumentException extends GameException {

  /**
   * Constructs a new {@code IlliegalGameArgumentException} with the specified detail message.
   *
   * @param message the detail message explaining the illegal argument
   */
  public IlliegalGameArgumentException(String message) {
    super(message);
  }
}
