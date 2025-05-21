package edu.ntnu.idi.idattx2002.io.chessIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.*;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Piece;

public class PositionIO {

  private final String basePath;

  public PositionIO() {
    basePath = "src/main/resources/chessResources/chessPositions/";
  }

  private String getPositionString(Chess chess) {

    String positionString = "";
    positionString += "M" + getColorNotation(chess.getPlayerToMove().getColor());

    for (ChessSquare square : chess.getBoard().getSquareMap().values()) {
      if (square.hasPiece()) {
        positionString += "\n" + getSquareNotation(square);
      }
    }
    return positionString;
  }

  private void loadSquare(Chess chess, String squareNotation) {
    squareNotation = squareNotation.trim();
    String idString = "";
    String pieceString = "";
    String colorString = "";
    for(char c : squareNotation.toCharArray()) {
      if (Character.isDigit(c)) {
        idString += c;
      }
      else if (c == 'b' || c == 'w') {
        colorString += c;
      }
      else {
        pieceString += c;
      }
    }

    ChessColor color = colorString.equals("w") ? ChessColor.WHITE : ChessColor.BLACK;

    int id = Integer.parseInt(idString);
    ChessSquare square = chess.getBoard().getSquare(id);

    Piece piece;
    if(pieceString.equals("P")) {
      piece = new Pawn(square, color);
    }
    else if(pieceString.equals("R")) {
      piece = new Rook(square, color);
    }
    else if(pieceString.equals("N")) {
      piece = new Horse(square, color);
    }
    else if(pieceString.equals("B")) {
      piece = new Bishop(square, color);
    }
    else if(pieceString.equals("Q")) {
      piece = new Queen(square, color);
    }
    else if(pieceString.equals("K")) {
      piece = new King(square, color);
    }
    else {
      throw new RuntimeException("Error resolving piecetype to: " + pieceString);
    }
  }

  private String getColorNotation(ChessColor color) {
    String playerNotation;
    if(color == ChessColor.WHITE) {
      playerNotation = "w";
    }
    else if(color == ChessColor.BLACK) {
      playerNotation = "b";
    }
    else {
      throw new IllegalArgumentException("Couldn't resolve player color");
    }
    return playerNotation;
  }

  private String getSquareNotation(ChessSquare square) {
    Piece piece = square.getPiece();

    String pieceNotation;
    if (piece instanceof Pawn) {
      pieceNotation = "P";
    }
    else if(piece instanceof Bishop) {
      pieceNotation = "B";
    }
    else if(piece instanceof Horse) {
      pieceNotation = "N";
    }
    else if (piece instanceof Rook) {
      pieceNotation = "R";
    }
    else if (piece instanceof Queen) {
      pieceNotation = "Q";
    }
    else if (piece instanceof King) {
      pieceNotation = "K";
    }
    else {
      throw new IllegalArgumentException("Couldn't resolve pieceNotation");
    }

    return square.getSquareId() + pieceNotation + getColorNotation(piece.getColor());
  }

  private void loadPositionFromString(Chess chess, String positionString) {
    String[] lines = positionString.split("\\R");

    for (String line : lines) {
      String squareNotation = line.trim();

      if (squareNotation.equals("Mw")) {
        if (chess.getPlayerToMove().getColor() != ChessColor.WHITE) {
          chess.updatePlayerToMove();
        }
      } else if (squareNotation.equals("Mb")) {
        if (chess.getPlayerToMove().getColor() != ChessColor.BLACK) {
          chess.updatePlayerToMove();
        }
      } else {
        loadSquare(chess, squareNotation);
      }
    }
  }

  public void loadPosition(Chess chess, String fileName) {
    String path = basePath + fileName;

    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String squareNotation = line.trim();

        if (squareNotation.equals("Mw")) {
          if (chess.getPlayerToMove().getColor() != ChessColor.WHITE) {
            chess.updatePlayerToMove();
          }
        } else if (squareNotation.equals("Mb")) {
          if (chess.getPlayerToMove().getColor() != ChessColor.BLACK) {
            chess.updatePlayerToMove();
          }
        } else {
          loadSquare(chess, squareNotation);
        }
      }
    }
    catch(IOException e) {
      throw new IllegalArgumentException("Couldnt read position file");
    }
  }

  public void savePosition(String fileName, Chess chess) {
    String path = basePath + "customPositions/" + fileName;

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      writer.write(getPositionString(chess));
    } catch (IOException e) {
      throw new IllegalArgumentException("Couldnt savePosition");
    }
  }

  public List<String> getAllStartPositionEndPaths(){
    List<String> fileNames = new ArrayList<>();
    ClassLoader loader = getClass().getClassLoader();

    String path = "startPositions";

    URL url = loader.getResource("chessResources/chessPositions/" + path);
    if (url == null) {
      throw new IllegalArgumentException("Couldnt resolve resource folder");
    }

    File positionFolder = new File(url.getFile());
    File[] files = positionFolder.listFiles();

    if(files != null) {
      for(File file : files) {
        fileNames.add(path + "/" + file.getName());
      }
    }
    return fileNames;
  }

}
