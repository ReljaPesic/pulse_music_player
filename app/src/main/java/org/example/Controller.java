package org.example;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXML;

public class Controller {

  @FXML
  public void onPlayButton() {
    String path = getClass().getResource("/song1.wav").getPath();
    System.out.println("Play Button clicked! Path: " + path);
    File file = new File(path);
    try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (UnsupportedAudioFileException e) {
      System.err.println("Unsupported audio file: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("IO error while accessing the file: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Some other error playing file: " + e.getMessage());
    }
  }

  @FXML
  public void onPauseButton() {
    System.out.println("Pause Button clicked!");
  }

  @FXML
  public void onStopButton() {
    System.out.println("Stop Button clicked!");
  }

}
