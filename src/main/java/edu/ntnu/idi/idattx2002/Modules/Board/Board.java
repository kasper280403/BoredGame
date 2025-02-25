package edu.ntnu.idi.idattx2002.Modules.Board;
import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
import java.util.HashMap;


public class Board {

  private HashMap<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }

  public void addTile(Tile tile) {
    tiles.put(tile.getTileId(), tile);
  }

  public void createBoard(int boardSize) {
    for (int i = 1; i < boardSize + 1; i++) {
      addTile(new Tile(i));
    }
  }
}
