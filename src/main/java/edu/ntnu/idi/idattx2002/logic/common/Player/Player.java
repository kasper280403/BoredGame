package edu.ntnu.idi.idattx2002.logic.common.Player;

public abstract class Player {

  public String name;
  public int iconId;

  public Player(String name, int iconId) {
    this.name = name;
    this.iconId = iconId;
  }

  public String getName() {
    return name;
  }

  public int getIconId() {
    return iconId;
  }
}
