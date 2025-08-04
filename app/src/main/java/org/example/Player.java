package org.example;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectPropertyBase;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import lombok.Getter;

@Getter
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

  public void setOnReady(Runnable runnable) {
    if (mediaPlayer != null) {
      mediaPlayer.setOnReady(runnable);
    }
  }

  public double getCurrentTime() {
    if (mediaPlayer != null) {
      return mediaPlayer.getCurrentTime().toSeconds();
    }
    return 0;
  }

  public double getTotalDuration() {
    if (mediaPlayer != null) {
      return mediaPlayer.getTotalDuration().toSeconds();
    }
    return 0;
  }

  public MediaPlayer getMediaPlayer() {
    return mediaPlayer != null ? mediaPlayer : null;
  }

  public ReadOnlyObjectProperty<Duration> currentTimeProperty() {
    if (mediaPlayer != null) {
      return mediaPlayer.currentTimeProperty();
    }
    return null;
  }
}
