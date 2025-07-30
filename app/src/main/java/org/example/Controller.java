package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class Controller implements Initializable {
  private Song song;
  private Playlist playlist;
  @FXML
  private ListView<String> listView;

  @FXML
  public void onPlayButton() {
    // playlist.playCurrentSong();
  }

  @FXML
  public void onPauseButton() {
    // if (song != null) {
    // song.stopsong();
    // }
  }

  @FXML
  public void onStopButton() {
    // if (song != null) {
    // song.stopsong();
    // song = null; // Reset the song to null after stopping
    // }
  }

  private Song loadSong(String path) {
    File file = new File(path);
    Song song = null;
    try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      song = new Song(file.getName(), file.getAbsolutePath(), 300, clip);
    } catch (UnsupportedAudioFileException e) {
      System.err.println("Unsupported audio file: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("IO error while accessing the file: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Some other error playing file: " + e.getMessage());
    }
    return song;
  }

  private void loadPlaylistFromFile() {
    File dir = new File(getClass().getResource("/playlist").getPath());
    playlist = new Playlist();
    if (dir.isDirectory()) {
      try (Stream<Path> paths = Files.walk(dir.toPath())) {
        paths.filter(Files::isRegularFile)
            .forEach(path -> {
              System.out.println("Loading song from: " + path);
              Song song = loadSong(path.toString());
              playlist.addSong(song);
            });
      } catch (IOException e) {
        System.err.println("Error reading playlist directory: " + e.getMessage());
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    ObservableList<String> observableList = FXCollections.observableArrayList();
    loadPlaylistFromFile();
    System.out.println("Playlist loaded with " + playlist);
    for (Song song : playlist.getSongs()) {
      observableList.add(song.toString());
    }
    listView.setItems(observableList);
  }

}
