package edu.ntnu.idi.idattx2002.Modules.Games;

import edu.ntnu.idi.idattx2002.view.GameWindow;
import edu.ntnu.idi.idattx2002.Modules.Board.Board;
import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import java.util.HashMap;

public class SnakesAndLadders {

  private Board board;
  private HashMap<Integer, Player> players;
  private Dice dice;
  private GameWindow gameWindow;

  public SnakesAndLadders() {
    this.board = new Board();
    this.players = new HashMap<>();
    this.dice = new Dice();
  }

  public void createBoard() {
    board.createBoard(90);

  }



}
