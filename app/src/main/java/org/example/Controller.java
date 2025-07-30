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
  private int currentSongIndex = 0;
  private Player player;

  @FXML
  private ListView<Song> listView;

  @FXML
  public void onPlayButton() {
    if (player.isPaused()) {
      player.resume(); // Resume if paused
    } else {
      player.play(song);
    }
  }

  @FXML
  public void onPauseButton() {
    if (player.isPlaying()) {
      player.pause();
    }
  }

  @FXML
  public void onStopButton() {
    if (player.isPlaying()) {
      player.stop();
    }
  }

  private Song loadSong(Path path) {
    File file = new File(path.toString());
    Song song = null;
    try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      song = new Song(file.getName(), file.getAbsolutePath(), path);
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
              Song song = loadSong(path);
              playlist.addSong(song);
            });
      } catch (IOException e) {
        System.err.println("Error reading playlist directory: " + e.getMessage());
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // Initialize player
    player = Player.getInstance();

    // load the playlist from the file
    // TODO change to load to json file
    loadPlaylistFromFile();

    ObservableList<Song> observableList = FXCollections.observableArrayList();
    observableList.addAll(playlist.getSongsInPlaylist());
    listView.setItems(observableList);

    listView.getSelectionModel().selectedItemProperty().addListener((_, _, newSelectedSong) -> {
      player.play(newSelectedSong); // Play the selected song
      song = newSelectedSong; // Update the current song
    });
  }
}
