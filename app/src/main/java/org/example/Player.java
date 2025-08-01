package org.example;

import javafx.scene.media.MediaPlayer;

public class Player {
  private MediaPlayer mediaPlayer;

  public void play(Song song) {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.dispose();
    }
    mediaPlayer = new MediaPlayer(song.getMedia());
    mediaPlayer.play();
  }

  public void pause() {
    if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
      mediaPlayer.pause();
    }
  }

  public void resume() {
    if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
      mediaPlayer.play();
    }
  }

  public void stop() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.dispose();
      mediaPlayer = null;
    }
  }

  public MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }
}
