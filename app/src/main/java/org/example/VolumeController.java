package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class VolumeController {
  public void setupVolumeControl(Slider volumeSlider, Player player, Label volumeIcon) {
    // volumeSlider.setValue(100);
    //
    // volumeSlider.setOnScroll(event -> {
    // double direction = Math.signum(event.getDeltaY());
    // if (direction > 0) {
    // volumeSlider.setValue(Math.min(volumeSlider.getValue() + 5, 100)); //
    // Increase volume
    // } else if (direction < 0) {
    // volumeSlider.setValue(Math.max(volumeSlider.getValue() - 5, 0)); // Decrease
    // volume
    // }
    // });
    //
    // volumeSlider.valueProperty().addListener((_, _, newValue) -> {
    // if (newValue.intValue() == 0) {
    // volumeIcon.setText("🔇"); // Mute icon
    // } else if (newValue.intValue() < 30) {
    // volumeIcon.setText("🔈"); // Low volume icon
    // } else if (newValue.intValue() < 70) {
    // volumeIcon.setText("🔉"); // Medium volume icon
    // } else {
    // volumeIcon.setText("🔊"); // High volume icon
    // }
    // player.setVolume(newValue.doubleValue() / 100.0); // Set volume based on
    // slider value
    // });

  }
}
