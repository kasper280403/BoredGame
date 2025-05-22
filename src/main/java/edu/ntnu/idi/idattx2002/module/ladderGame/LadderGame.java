package edu.ntnu.idi.idattx2002.module.ladderGame;

import edu.ntnu.idi.idattx2002.exception.IlliegalGameArgumentException;
import edu.ntnu.idi.idattx2002.io.ladderGame.BoardIO;
import edu.ntnu.idi.idattx2002.module.common.WinObserver;
import edu.ntnu.idi.idattx2002.module.ladderGame.board.LadderGameBoard;
import edu.ntnu.idi.idattx2002.module.ladderGame.dice.Dice;
import edu.ntnu.idi.idattx2002.module.ladderGame.player.LadderGamePlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the main game logic for a ladder game.
 *
 * <p>Handles player turns, dice rolls, movement, board setup, and win detection.
 *
 * @author Sindre Mjøs and Kasper Karlsen
 * @version 1.0
 */
public class LadderGame {

  /** Static map holding all players indexed by their unique IDs. */
  public static Map<Integer, LadderGamePlayer> players;

  private final LadderGameBoard board;

  private Dice dice1;
  private Dice dice2;
  private List<Dice> dices;

  private int playerToMoveID;

  private WinObserver observer;

  /** Constructs a new {@code LadderGame} instance and initializes the game components. */
  public LadderGame() {
    this.board = new LadderGameBoard();
    players = new HashMap<>();
    playerToMoveID = 1;

    init();
  }

  /**
   * Returns the list of dice used in the game.
   *
   * @return the list of {@code Dice} objects
   */
  public List<Dice> getDices() {
    return dices;
  }

  /**
   * Returns the game board used in the current game.
   *
   * @return the {@code LadderGameBoard} instance
   */
  public LadderGameBoard getBoard() {
    return board;
  }

  /**
   * Returns the player whose turn it is to move.
   *
   * @return the current player
   */
  public LadderGamePlayer getPlayerToMove() {
    return players.get(playerToMoveID);
  }

  /**
   * Adds a win observer to be notified when a player wins the game.
   *
   * @param observer the observer to register
   */
  public void addObserver(WinObserver observer) {
    this.observer = observer;
  }

  /**
   * Creates and initializes the board layout and tile actions.
   *
   * @param gamemode the ID or name used to determine action configuration
   */
  public void createBoard(String gamemode) {
    board.createBoard(9, 10);
    setActions(gamemode);
  }

  /**
   * Executes a single turn for the current player: rolls dice, moves the player, checks for win
   * condition, performs tile actions, and advances the turn.
   */
  public void playTurn() {
    LadderGamePlayer player = getPlayerToMove();

    int steps = 0;
    for (Dice dice : dices) {
      steps += dice.throwDice();
    }

    player.movePlayerBySteps(steps);
    checkForWin(player);

    if(player.getCurrentTileId() > board.getLastTile().getSquareId()) {
      board.getLastTile().landPlayer(player);
    }
    else {
      board.getTile(player.getCurrentTileId()).landPlayer(player);
    }
    updatePlayerToMove();
  }

  /** Advances the turn to the next player. */
  private void updatePlayerToMove() {
    if (playerToMoveID >= players.size()) {
      playerToMoveID = 1;
    } else {
      playerToMoveID += 1;
    }
  }

  /**
   * Checks if the given player has reached the final tile and triggers the win observer if so.
   *
   * @param player the player to check
   */
  public void checkForWin(LadderGamePlayer player) {
    if (player.getCurrentTileId() >= board.getSquareMap().size()) {
      observer.update(player);
    }
  }

  /**
   * Adds a player to the game after validating uniqueness of name and icon.
   *
   * @param player the player to add
   * @throws IlliegalGameArgumentException if player name or icon is not unique
   */
  public void addPlayer(LadderGamePlayer player) {
    validatePlayer(player);
    players.put(player.getPlayerID(), player);
  }

  /**
   * Validates that the player name and icon are unique among all registered players.
   *
   * @param player the player to validate
   * @throws IlliegalGameArgumentException if name or icon ID already exists
   */
  private void validatePlayer(LadderGamePlayer player) {
    for (LadderGamePlayer existingPlayer : players.values()) {
      if (player.getName() == existingPlayer.getName()) {
        throw new IlliegalGameArgumentException("Two players can not have the same name");
      }
      if (player.getIconId() == existingPlayer.getIconId()) {
        throw new IlliegalGameArgumentException("Two players can not have the same icon");
      }
    }
  }

  /**
   * Loads and sets land actions for the board based on the given game ID.
   *
   * @param gameID the identifier used to load action configuration
   */
  private void setActions(String gameID) {
    BoardIO boardIO = new BoardIO(board);
    boardIO.setActions(gameID);
  }

  /** Initializes the dice used in the game. */
  private void initDices() {
    this.dice1 = new Dice();
    this.dice2 = new Dice();

    dices = new ArrayList<>();
    dices.add(dice1);
    dices.add(dice2);
  }

  /** Initializes all game components. */
  private void init() {
    initDices();
  }
}
