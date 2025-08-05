package org.example;

import javafx.scene.control.Slider;
import javafx.util.Duration;

public class PlayerSliderBinder {
  private final Player player;
  private final Slider slider;

  public PlayerSliderBinder(Player player, Slider slider) {
    this.player = player;
    this.slider = slider;
  }

  public void bindSliderToPlayer(Player player, Slider slider) {
    player.setOnReady(() -> {
      slider.setMax(player.getTotalDuration());
      slider.setValue(0);
    });

    player.currentTimeProperty().addListener((_, _, newValue) -> {
      if (!slider.isValueChanging()) {
        slider.setValue(newValue.toSeconds());
      }
    });

    slider.setOnMouseDragged(_ -> {
      player.pause(); // Pause the player while dragging
    });

    slider.setOnMouseReleased(_ -> {
      double newTime = slider.getValue();
      player.seek(Duration.seconds(newTime));
      player.resume(); // Resume the player after dragging
    });

    slider.setOnMousePressed(_ -> {
      double newTime = slider.getValue();
      player.seek(Duration.seconds(newTime));
      if (player.isPlaying()) {
        player.resume(); // Pause the player while dragging
      }
    });

    slider.valueChangingProperty().addListener((_, _, isChanging) -> {
      if (!isChanging) {
        double newTime = slider.getValue();
        player.seek(Duration.seconds(newTime));
      }
    });
  }
}
