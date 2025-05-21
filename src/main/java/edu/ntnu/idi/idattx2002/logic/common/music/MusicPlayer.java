package edu.ntnu.idi.idattx2002.logic.common.music;

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

  private MusicObserver observer;


  public MusicPlayer() {
    init();
  }

  public void addObserver(MusicObserver observer) {
    this.observer = observer;
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

    observer.update(songFile.getName());
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
    random = new Random();
    musicList = new ArrayList<>();
    loadMusic();
  }
}