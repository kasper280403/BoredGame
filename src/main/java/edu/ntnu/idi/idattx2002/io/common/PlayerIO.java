package edu.ntnu.idi.idattx2002.io.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and writing player data to a CSV file.
 *
 * <p>Supports adding, retrieving, and deleting players. The data is stored in {@code
 * GameData/playerData.csv} in the format: {@code name,imgID}.
 *
 * @author Kasper Karlsen
 * @version 1.0
 */
public class PlayerIO {

  /** Constructs a new {@code PlayerIO} instance. */
  public PlayerIO() {}

  /**
   * Appends a new player's data to the CSV file.
   *
   * @param name the player's name
   * @param imgID the player's icon/image ID
   * @throws RuntimeException if the data cannot be written
   */
  public void writePlayer(String name, int imgID) {
    try {
      FileWriter writer = new FileWriter("gameData/playerData.csv", true);
      writer.write(name + "," + imgID + "\n");
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException("could not write player data");
    }
  }

  /**
   * Reads all players from the CSV file.
   *
   * @return a list of player data, each as a list of two strings: [name, imgID]
   * @throws RuntimeException if the file cannot be read
   */
  public List<List<String>> getPlayers() {
    List<List<String>> players = new ArrayList<>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader("gameData/playerData.csv"));

      String line;
      while ((line = reader.readLine()) != null) {
        ArrayList<String> player = new ArrayList<>();
        String[] parts = line.split(",");
        player.add(parts[0]);
        player.add(parts[1]);
        players.add(player);
      }

      reader.close();
      return players;
    } catch (IOException e) {
      throw new RuntimeException("couldnt read playerdata");
    }
  }

  /**
   * Deletes a player entry from the CSV file based on their name.
   *
   * @param nameToDelete the name of the player to remove
   * @throws RuntimeException if the file cannot be read or written
   */
  public void deletePlayer(String nameToDelete) {
    ArrayList<String> updatedLines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("gameData/playerData.csv"))) {
      String line;

      while ((line = reader.readLine()) != null) {
        if (!line.startsWith(nameToDelete + ",")) {
          updatedLines.add(line);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not manage to delete player");
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("gameData/playerData.csv"))) {
      for (String updatedLine : updatedLines) {
        writer.write(updatedLine);
        writer.newLine();
      }
    } catch (IOException e) {
      throw new RuntimeException("Couldnt write to file");
    }
  }
}
