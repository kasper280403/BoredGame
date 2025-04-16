/* not working!!!
public class SnakesAndLaddersTest {

  private SnakesAndLadders snakesAndLadders;

  @BeforeEach
  void setUp() {
    //arrange
    snakesAndLadders = new SnakesAndLadders();
    snakesAndLadders.addPlayer("Sindre", 1);
    snakesAndLadders.addPlayer("Kasper", 3);

  }

  //TODO test setLandActions, and playTurn

  @Test
  void testGetPlayers() {
    //act
    HashMap<Integer, Player> players = snakesAndLadders.getPlayers();
    //assert
    assertEquals(2, players.size());
  }

  @Test
  void testCheckForWin() {
    //arrange
    snakesAndLadders.getPlayers().get(1).movePlayerToTile(90);
    //act
    boolean win = snakesAndLadders.checkForWin(snakesAndLadders.getPlayers().get(1));
    //assert
    assertTrue(win);
  }

  @Test
  void testAddPlayer() {
    //act
    snakesAndLadders.addPlayer("teiteKasper", 1);
    snakesAndLadders.addPlayer("kuleSindre", 2);
    //assert
    assertEquals(4, snakesAndLadders.getPlayers().size());
  }
}

 */
