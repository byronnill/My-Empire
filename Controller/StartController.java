package Controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

import java.io.*;

public class StartController {

  @FXML
  private Label TitleBar;

  @FXML
  private RadioButton players2;
  @FXML
  private RadioButton players3;
  @FXML
  private RadioButton players4;

  @FXML
  private Button startButton;

  @FXML
  private ImageView backdrop;

  ToggleGroup choices;

  public void initialize () {
    Image backdropImage = new Image("/Images/Main/Start Up.png");
    backdrop.setImage(backdropImage);

    choices = new ToggleGroup();

    players2.setToggleGroup(choices);
    players2.setStyle("-fx-cursor: hand");

    players3.setToggleGroup(choices);
    players3.setStyle("-fx-cursor: hand");

    players4.setToggleGroup(choices);
    players4.setStyle("-fx-cursor: hand");

    startButton.setDefaultButton(true);
    startButton.setStyle("-fx-cursor: hand");

    choices.selectToggle(players3);

  }

  public void handleStart (ActionEvent event) {

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NameAndGameType.fxml"));
      Parent root = loader.load();
      NameAndGameTypeController controller = loader.getController();

      controller.setNumPlayers(Integer.parseInt(((RadioButton) choices.getSelectedToggle()).getAccessibleText()));

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
