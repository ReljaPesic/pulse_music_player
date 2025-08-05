package org.example;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

public class Controller implements Initializable {
  private Song song;
  private Player player;

  private PlayerSliderBinder playerSliderBinder;
  private VolumeController volumeController;

  @FXML
  private ListView<Song> listView;
  @FXML
  private Slider timelineSlider;
  @FXML
  private Slider volumeSlider;
  @FXML
  private Label volumeIcon;

  @FXML
  public void onPlayButton() {
    if (song == null) {
      return;
    } else if (player.isPaused()) {
      player.resume();
    } else {
      player.play(song);
      playerSliderBinder.bindSliderToPlayer(player, timelineSlider);
    }
  }

  @FXML
  public void onPauseButton() {
    player.pause(); // Pause the current song
  }

  @FXML
  public void onStopButton() {
    player.stop(); // Stop the current song
    timelineSlider.setValue(0); // Reset the slider to the start
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    Loader loader = Loader.getInstance();

    ObservableList<Song> observableList = FXCollections.observableArrayList();
    List<Song> songs = loader.loadSongsFromJson();
    if (songs != null && !songs.isEmpty()) {
      observableList.addAll(songs);
    }

    listView.setItems(observableList);
    listView.getSelectionModel().selectedItemProperty().addListener((_, _, newSelectedSong) -> {
      player.play(newSelectedSong); // Play the selected song
      playerSliderBinder.bindSliderToPlayer(player, timelineSlider);
      song = newSelectedSong; // Update the current song
    });
  }

  public void setPlayer(Player player) {
    this.player = player;
    if (timelineSlider != null) {
      this.playerSliderBinder = new PlayerSliderBinder(player, timelineSlider);
    }
    if (volumeSlider != null && volumeIcon != null) {
      this.volumeController = new VolumeController(volumeSlider, player, volumeIcon);
    }
  }
}
