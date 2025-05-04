package edu.ntnu.idi.idattx2002.gui.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

  private MediaPlayer mediaPlayer;

  private Random random;

  private List<File> musicList;

  private int currentSongID;

  public MusicPlayer() {
    init();
  }

  public void playNext() {
    if (mediaPlayer != null) {
      MediaPlayer.Status status = mediaPlayer.getStatus();
      if (status == MediaPlayer.Status.PLAYING || status == MediaPlayer.Status.PAUSED) {
        mediaPlayer.stop();
      }
      mediaPlayer.dispose();
    }

    if(musicList.isEmpty()) {
      loadMusic();
    }

    int randomSongId = random.nextInt(musicList.size());

    File songFile = musicList.get(randomSongId);
    Media songMedia = new Media(songFile.toURI().toString());
    mediaPlayer = new MediaPlayer(songMedia);

    mediaPlayer.play();
    mediaPlayer.setOnEndOfMedia(this::playNext);
    musicList.remove(randomSongId);
  }

  public void pause() {
    mediaPlayer.pause();
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

    random = new Random();

    musicList = new ArrayList<>();

    loadMusic();
  }

}