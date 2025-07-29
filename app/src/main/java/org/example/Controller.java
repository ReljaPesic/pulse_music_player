package org.example;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXML;

public class Controller {
  private Song song;

  @FXML
  public void onPlayButton() {
    if (song == null) {
      loadSong();
      song.playsong();
    } else {
      song.playsong();
    }
  }

  @FXML
  public void onPauseButton() {
    if (song != null) {
      song.stopsong();
    }
  }

  @FXML
  public void onStopButton() {
    if (song != null) {
      song.stopsong();
      song = null; // Reset the song to null after stopping
    }
  }

  public void loadSong() {
    String path = getClass().getResource("/song1.wav").getPath();
    File file = new File(path);
    try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      song = new Song("Song 1", "Sins We Carry", 300, clip);
    } catch (UnsupportedAudioFileException e) {
      System.err.println("Unsupported audio file: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("IO error while accessing the file: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Some other error playing file: " + e.getMessage());
    }
  }

}
