package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class VolumeController {
  private final Slider volumeSlider;
  private final Player player;
  private final Label volumeIcon;

  public VolumeController(Slider volumeSlider, Player player, Label volumeIcon) {
    this.volumeSlider = volumeSlider;
    this.player = player;
    this.volumeIcon = volumeIcon;
    setupVolumeControl(volumeSlider, player, volumeIcon);
  }

  public void setupVolumeControl(Slider volumeSlider, Player player, Label volumeIcon) {
    volumeSlider.setValue(100);

    volumeSlider.setOnScroll(event -> {
      double direction = Math.signum(event.getDeltaY());
      if (direction > 0) {
        volumeSlider.setValue(Math.min(volumeSlider.getValue() + 5, 100));
      } else if (direction < 0) {
        volumeSlider.setValue(Math.max(volumeSlider.getValue() - 5, 0));
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
      player.setVolume(newValue.doubleValue() / 100.0);
    });

  }
}
