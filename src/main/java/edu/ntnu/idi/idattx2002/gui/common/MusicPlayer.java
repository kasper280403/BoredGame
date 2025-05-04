package edu.ntnu.idi.idattx2002.gui.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

  private MediaPlayer mediaPlayer;
  private List<File> musicList;

  private int currentSongID;

  public MusicPlayer() {
    init();
  }

  public void playNext() {
    if(currentSongID > musicList.size()) {
      currentSongID = 0;
    }

    File songFile = musicList.get(currentSongID);
    Media songMedia = new Media(songFile.toURI().toString());
    mediaPlayer = new MediaPlayer(songMedia);

    mediaPlayer.play();
    mediaPlayer.setOnEndOfMedia(this::playNext);
    currentSongID ++;
  }

  private void loadMusic() {
    File musicFolder = new File("GameData/music");

    for (File file : musicFolder.listFiles()) {
      if(file.getName().toLowerCase().endsWith(".mp3")) {
        musicList.add(file);
      }
    }
  }

  private void init() {
    currentSongID = 0;
    musicList = new ArrayList<>();
    loadMusic();
  }

}