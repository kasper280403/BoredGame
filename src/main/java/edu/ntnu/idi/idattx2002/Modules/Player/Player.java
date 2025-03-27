package edu.ntnu.idi.idattx2002.Modules.Player;

import edu.ntnu.idi.idattx2002.Modules.Observer;
import java.util.ArrayList;
import java.util.List;

public class Player {
    String playerName;
    int playerID;
    int pieceID;
    int currentTileId = 1;
    List<Observer> observers;

    public Player(String playerName, int playerID, int pieceID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.pieceID = pieceID;

        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Added observer");
    }

    public void movePlayerBySteps(int numberOfSteps){
        currentTileId += numberOfSteps;
        notifyObservers();
    }

    public void movePlayerToTile(int tileId) {
        currentTileId = tileId;
        notifyObservers();
    }

    private void notifyObservers() {
        System.out.println("Entered notify observers");
        for(Observer observer : observers) {
            observer.update(this);
        }
    }

    public int getCurrentTile(){
        return currentTileId;
    }

    public int getPieceID() {
        return pieceID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
