package org.example;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class Controller implements Initializable {
  private Song song;
  private Player player;
  private Loader loader;

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
    if (player.isPlaying() || player.isPaused()) {
      player.stop();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    player = Player.getInstance();
    loader = Loader.getInstance();

    ObservableList<Song> observableList = FXCollections.observableArrayList();
    List<Song> songs = loader.loadSongsFromJson();
    if (songs != null && !songs.isEmpty()) {
      observableList.addAll(songs);
    }

    listView.setItems(observableList);
    listView.getSelectionModel().selectedItemProperty().addListener((_, _, newSelectedSong) -> {
      player.play(newSelectedSong); // Play the selected song
      song = newSelectedSong; // Update the current song
    });
  }
}
