package edu.ntnu.idi.idattx2002.Modules.Board;
import edu.ntnu.idi.idattx2002.Modules.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.Modules.Board.Actions.SwitchWithRandomAction;
import java.util.function.BiConsumer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BoardDAO {

    Board board;
    ArrayList<String> actionNames;
    ArrayList<ArrayList<ArrayList<Integer>>> actions;
    Map<String, BiConsumer<Integer, Integer>> actionMethods = new HashMap<>();



    public BoardDAO(Board board) {
        this.board = board;
        actionNames = new ArrayList<>();
        actions = new ArrayList<>();
        actionMethods.put("LadderActions", this::ladderAction);
        actionMethods.put("SwitchActions", (a, b) -> switchWithRandomAction(a));

    }

    public void setActions(String gameID){
        try {
            readFile(gameID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int index = 0;
        for (String actionName : actionNames) {
            ArrayList<ArrayList<Integer>> thisAction = actions.get(index);
            for (ArrayList<Integer> oneAction : thisAction) {
                actionMethods.get(actionName).accept(oneAction.get(0), oneAction.get(1));
            }
        }

    }

    public void readFile(String gameID) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("GameData/boardSetUp.csv"));
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
                    // Starter ny action-type
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
                    if (entry.size() == 1){
                        entry.add(0);
                    }
                    actions.get(actionTypeIndex).add(entry);
                }
            }
        }

        reader.close();

        for (int i = 0; i < actionNames.size(); i++) {
            System.out.println("Action type: " + actionNames.get(i));
            for (ArrayList<Integer> pair : actions.get(i)) {
                System.out.println(pair);
            }
        }
    }

    public void ladderAction(int tile, int destination){
        board.getTile(tile).setLandAction(new LadderAction(destination));
    }

    public void switchWithRandomAction(int tile){
        board.getTile(tile).setLandAction(new SwitchWithRandomAction());
    }

}
