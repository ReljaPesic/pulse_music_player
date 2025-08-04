package org.example;

import javafx.scene.control.Slider;
import javafx.util.Duration;

public class PlayerSliderBinder {
  public void bindSliderToPlayer(Player player, Slider slider) {
    player.setOnReady(() -> {
      slider.setMax(player.getTotalDuration());
      slider.setValue(0);
    });

    player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
      if (!slider.isValueChanging()) {
        slider.setValue(newValue.toSeconds());
      }
    });

    slider.setOnMousePressed(event -> {
      player.pause(); // Pause the player when the slider is pressed
    });

    slider.setOnMouseReleased(event -> {
      double newTime = slider.getValue();
      player.getMediaPlayer().seek(Duration.seconds(newTime));
    });

    slider.valueChangingProperty().addListener((observable, wasChanging, isChanging) -> {
      if (!isChanging) {
        double newTime = slider.getValue();
        player.getMediaPlayer().seek(Duration.seconds(newTime));
      }
    });
  }
}
