package edu.ntnu.idi.idattx2002.module.common.Player;

/**
 * Represents an abstract player with a name and an icon identifier.
 * <p>
 * Subclasses should define specific player behavior or attributes.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public abstract class Player {

  private final String name;
  private final int iconId;

  /**
   * Constructs a new Player with the specified name and icon ID.
   *
   * @param name the name of the player; must be non-empty and contain no whitespace
   * @param iconId the identifier of the player's icon; must be between 1 and 4
   * @throws IllegalArgumentException if name is invalid or iconId is out of range
   */
  public Player(String name, int iconId) {
    verifyName(name);
    verifyIconId(iconId);

    this.name = name;
    this.iconId = iconId;
  }

  private void verifyIconId(int iconId) {
    if(iconId <= 0 || iconId > 4) {
      throw new IllegalArgumentException("IconIs must be between 1 and 4");
    }
  }

  private void verifyName(String name) {
    if(name.isBlank() || name.contains(" ")) {
      throw new IllegalArgumentException("Name cannot be empty or contain white spaces");
    }
  }

  /**
   * Returns the name of the player.
   *
   * @return the player's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the icon identifier of the player.
   *
   * @return the player's icon ID
   */
  public int getIconId() {
    return iconId;
  }
}
