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
import lombok.Setter;

public class Controller implements Initializable {
  private Song song;
  @Setter
  private Player player;
  @Setter
  private PlayerSliderBinder playerSliderBinder;

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
      playerSliderBinder.bindSliderToPlayer(player, timelineSlider); // Bind the slider to the player
      song = newSelectedSong; // Update the current song
    });

    // volumeController.setupVolumeControl(volumeSlider, player, volumeIcon); //
    // Setup volume control

    volumeSlider.setValue(100);

    volumeSlider.setOnScroll(event -> {
      double direction = Math.signum(event.getDeltaY());
      if (direction > 0) {
        volumeSlider.setValue(Math.min(volumeSlider.getValue() + 5, 100)); // Increase volume
      } else if (direction < 0) {
        volumeSlider.setValue(Math.max(volumeSlider.getValue() - 5, 0)); // Decrease volume
      }
    });

    volumeSlider.valueProperty().addListener((_, _, newValue) -> {
      if (newValue.intValue() == 0) {
        volumeIcon.setText("🔇"); // Mute icon
      } else if (newValue.intValue() < 30) {
        volumeIcon.setText("🔈"); // Low volume icon
      } else if (newValue.intValue() < 70) {
        volumeIcon.setText("🔉"); // Medium volume icon
      } else {
        volumeIcon.setText("🔊"); // High volume icon
      }
      player.setVolume(newValue.doubleValue() / 100.0); // Set volume based on slider value
    });
  }

  public void setup() {

  }
}
