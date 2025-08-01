package org.example;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

public class Controller implements Initializable {
  private Song song;
  private Player player;
  private Loader loader;

  @FXML
  private ListView<Song> listView;

  @FXML
  private Slider timelineSlider;

  @FXML
  public void onPlayButton() {
    if (song != null) {
      player.play(song); // Play the current song
    } else {
      System.out.println("No song selected to play.");
    }
  }

  @FXML
  public void onPauseButton() {
    player.pause(); // Pause the current song
  }

  @FXML
  public void onStopButton() {
    player.stop(); // Stop the current song
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
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

  public void setPlayer(Player player) {
    this.player = player;
  }
}
