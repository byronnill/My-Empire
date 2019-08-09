package Controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.*;

import java.io.*;

public class CreditsController {

  @FXML
  private ImageView backdrop, muteButton;

  @FXML
  private Button goBack;

  private MediaPlayer player;

  public void initialize () {

    Image bg = new Image ("/Images/Main/Screens/Credits.png");

    backdrop.setImage(bg);
    goBack.setStyle("-fx-cursor: hand");

    muteButton.setStyle("-fx-cursor: hand");

  }

  public void setPlayer (MediaPlayer player) {
    this.player = player;

    if (player.getVolume() > 0)
      muteButton.setImage(new Image("Images/Main/Misc/Sound on.png"));

    else
      muteButton.setImage(new Image("Images/Main/Misc/Sound off.png"));
  }

  public void handleMute () {

    if (player.getVolume() > 0) {

      player.setVolume(0);
      muteButton.setImage(new Image("/Images/Main/Misc/Sound off.png"));

    } else {

      player.setVolume(9);
      muteButton.setImage(new Image("/Images/Main/Misc/Sound on.png"));

    }

  }

  public void handleBack (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Start.fxml"));
      Parent root = loader.load();
      StartController controller = loader.getController();

      controller.setPlayer(player);

      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      stage.setResizable(false);
      stage.centerOnScreen();
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
