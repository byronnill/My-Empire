package Controller;

import Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.*;

import java.io.*;
import java.util.*;

public class NameAndGameTypeController {

  private int nPlayers;
  private Game masterObject;
  private ArrayList <TextField> nameFields = new ArrayList <> ();
  private MediaPlayer player;

  @FXML
  private TextField player1Name;
  @FXML
  private TextField player2Name;
  @FXML
  private TextField player3Name;
  @FXML
  private TextField player4Name;

  @FXML
  private Button confirmButton;

  @FXML
  private ImageView backdrop, muteButton;

  @FXML
  private Label confirmation, noDups;

  public void initialize () {

    Image backdropImage = new Image("/Images/Main/Screens/Name Screen.png");
    backdrop.setImage(backdropImage);

    muteButton.setStyle("-fx-cursor: hand");
    confirmButton.setStyle("-fx-cursor: hand");

    player1Name.setDisable(true);
    player1Name.setDisable(false);

    nameFields.add(player1Name);
    nameFields.add(player2Name);
    nameFields.add(player3Name);
    nameFields.add(player4Name);

    noDups.setVisible(false);

  }

  public void setNumPlayers (int nNum) {

    nPlayers = nNum;

    if (nPlayers == 2) {

      player3Name.setVisible(false);
      player4Name.setVisible(false);

    } else if (nPlayers == 3) {

      player4Name.setVisible(false);

    }

  }

  public void setPlayer (MediaPlayer player) {
    this.player = player;

    if (player.getVolume() > 0)
      muteButton.setImage(new Image ("/Images/Main/Misc/Sound on.png"));

    else
      muteButton.setImage(new Image ("/Images/Main/Misc/Sound off.png"));
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

  public void setNames () {

    masterObject.getPlayerList().get(0).setName(player1Name.getText());
    masterObject.getPlayerList().get(1).setName(player2Name.getText());

    if (masterObject.getNumPlayers() >= 3)
      masterObject.getPlayerList().get(2).setName(player3Name.getText());

    if (masterObject.getNumPlayers() == 4)
      masterObject.getPlayerList().get(3).setName(player4Name.getText());

  }

  public void checkMax1 () {

    if (player1Name.getText().length() >= 25) {

      int pos = player1Name.getCaretPosition();
      player1Name.setText(player1Name.getText(0, 24));
      player1Name.positionCaret(pos);

    }

  }

  public void checkMax2 () {

    if (player2Name.getText().length() >= 25) {

      int pos = player2Name.getCaretPosition();
      player2Name.setText(player2Name.getText(0, 24));
      player2Name.positionCaret(pos);

    }

  }

  public void checkMax3 () {

    if (player3Name.getText().length() >= 25) {

      int pos = player3Name.getCaretPosition();
      player3Name.setText(player3Name.getText(0, 24));
      player3Name.positionCaret(pos);

    }

  }

  public void checkMax4 () {

    if (player4Name.getText().length() >= 25) {

      int pos = player4Name.getCaretPosition();
      player4Name.setText(player4Name.getText(0, 24));
      player4Name.positionCaret(pos);

    }

  }

  public void checkFill () {

    boolean bFilled = true;
    boolean bOkay = true;

    for (int i = 0; i < nPlayers; i++) {

      if (nameFields.get(i).getText().trim().equals(""))
        bFilled = false;

    }

    if (bFilled) {

      confirmation.setVisible(false);
      confirmButton.setText("Get Player Order");
      confirmButton.setDefaultButton(true);

    } else {

      confirmation.setVisible(true);
      confirmButton.setText("Accept and Get Player Order");
      confirmButton.setDefaultButton(false);

    }

    for (int i = 0; i < nPlayers - 1; i++) {

      for (int j = i + 1; j < nPlayers; j++)

        if (nameFields.get(i).getText().equals(nameFields.get(j).getText()) && !nameFields.get(i).getText().trim().equals(""))
          bOkay = false;

    }

    if (!bOkay) {

      confirmButton.setDisable(true);
      noDups.setVisible(true);

    } else {

      confirmButton.setDisable(false);
      noDups.setVisible(false);

    }

  }

  public void handleConfirm (ActionEvent event) {

    masterObject = new Game(nPlayers);

    for (TextField tf : nameFields)
      if (tf.getText().trim().equals(""))
        tf.setText(tf.getAccessibleText());

    setNames();

    for (Player p : masterObject.getPlayerList())
      System.out.println(p.getName());

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/OrderPlayers.fxml"));
      Parent root = loader.load();
      OrderPlayersController orderPlayersController = loader.getController();

      orderPlayersController.setPlayer(player);
      orderPlayersController.setGame(masterObject);

      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      stage.setResizable(false);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

}
