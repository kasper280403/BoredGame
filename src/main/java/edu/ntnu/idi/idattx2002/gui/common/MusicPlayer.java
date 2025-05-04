package edu.ntnu.idi.idattx2002.gui.common;

import java.io.File;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

  private MediaPlayer mediaPlayer;

  public MusicPlayer() {
    init();
  }

  private void init() {
    File musicFolder = new File("GameData/music");
  }

}