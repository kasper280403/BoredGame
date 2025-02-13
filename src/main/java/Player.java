public class Player {
    String name;
    int playerID;
    int pieceID;
    int currentTile = 0;

    public Player(String name, int playerID, int pieceID) {
        this.name = name;
        this.playerID = playerID;
        this.pieceID = pieceID;
    }

    public void movePlayer(int n){
        currentTile += n;
    }

}
