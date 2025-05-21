package edu.ntnu.idi.idattx2002.io.common;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerIO {

    public PlayerIO() {
    }


    public void writePlayer(String name, int imgID) throws IOException {
        FileWriter writer = new FileWriter("GameData/playerData.csv", true);
        writer.write(name +","+imgID+"\n");
        writer.close();
    }

    public List<List<String>> getPlayers() throws IOException {
        List<List<String>> players = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("GameData/playerData.csv"));

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
    }

    public void deletePlayer(String nameToDelete) throws IOException {
        ArrayList<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("GameData/playerData.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(nameToDelete + ",")) {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("GameData/playerData.csv"))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
