package edu.ntnu.idi.idattx2002.io.ladderGame;
import edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.Board.LadderGameBoard;
import java.util.List;
import java.util.function.BiConsumer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BoardIO {

    private final LadderGameBoard board;
    private final List<String> actionNames;
    private final List<List<List<Integer>>> actions;
    private final Map<String, BiConsumer<Integer, Integer>> actionMethods;
    private final String filePath;

    public BoardIO(LadderGameBoard board) {
        this.board = board;
        actionNames = new ArrayList<>();
        actions = new ArrayList<>();

         actionMethods = new HashMap<>();

        actionMethods.put("LadderActions", this::ladderAction);
        actionMethods.put("SwitchActions", (a, b) -> switchWithRandomAction(a));

        filePath = "src/main/resources/ladderGameResources/ladderGamePositions/boardSetUp.csv";
    }

    public ArrayList<String> getBoards()  {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            boolean insideTarget = false;
            ArrayList<String> boardNames = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.endsWith("(")) {
                    if (!insideTarget) {
                        boardNames.add(line.substring(0, line.length() - 1));
                    }
                    insideTarget = true;
                }
                if (line.endsWith(")")) {
                    insideTarget = false;
                }
            }
            reader.close();
            return boardNames;

        } catch(IOException e) {
            throw new RuntimeException("Couldnt load file");
        }
    }

    public void setActions(String gameID){
        readFile(gameID);

        int index = 0;
        for (String actionName : actionNames) {
            List<List<Integer>> thisAction = actions.get(index);
            for (List<Integer> oneAction : thisAction) {
                actionMethods.get(actionName).accept(oneAction.get(0), oneAction.get(1));
            }
            index++;
        }
    }

    private void readFile(String gameID)  {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            boolean insideTargetGame = false;
            boolean insideAction = false;

            int actionTypeIndex = -1;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith(gameID + "(")) {
                    insideTargetGame = true;
                    continue;
                }

                if (insideTargetGame) {
                    if (line.equals(")")) {
                        break;
                    }

                    if (line.startsWith("{") && !insideAction) {
                        insideAction = true;
                        String name = line.substring(1); // fjerner {
                        actionNames.add(name);
                        actions.add(new ArrayList<>());
                        actionTypeIndex++;
                        continue;
                    }

                    if (line.startsWith("}")) {
                        insideAction = false;
                        continue;
                    }

                    if (insideAction) {
                        String[] parts = line.split(",");
                        ArrayList<Integer> entry = new ArrayList<>();
                        for (String part : parts) {
                            entry.add(Integer.parseInt(part.trim()));
                        }
                        if (entry.size() == 1) {
                            entry.add(0);
                        }
                        actions.get(actionTypeIndex).add(entry);
                    }
                }
            }

            reader.close();
        } catch(IOException e) {
            throw new IllegalArgumentException("Could not read from file");
        }
    }

    private void ladderAction(int tile, int destination){
        board.getTile(tile).setLandAction(new LadderAction(destination));
    }

    private void switchWithRandomAction(int tile){
        board.getTile(tile).setLandAction(new SwitchWithRandomAction());
    }

}

