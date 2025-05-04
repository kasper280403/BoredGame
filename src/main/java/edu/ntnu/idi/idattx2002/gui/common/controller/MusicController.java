package edu.ntnu.idi.idattx2002.gui.common.controller;

import edu.ntnu.idi.idattx2002.gui.common.MusicPlayer;
import edu.ntnu.idi.idattx2002.gui.common.view.MusicView;
import javafx.scene.layout.Pane;

public class MusicController {

  private MusicPlayer musicPlayer;
  private MusicView musicView;

  public MusicController(Pane mainPane) {
    musicPlayer = new MusicPlayer();
    musicView = new MusicView(this, mainPane);
  }

  public MusicView getView() {
    return musicView;
  }

  public void playMusic() {
    musicPlayer.playNext();
  }

  public void pauseMusic() {
    musicPlayer.pause();
  }



}
