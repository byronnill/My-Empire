package Controller;

import Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.util.*;

public class NameAndGameTypeController {

  private int nPlayers;
  private Game masterObject;
  private ArrayList <TextField> nameFields = new ArrayList <> ();

  @FXML
  private AnchorPane container;

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
  private ImageView backdrop;

  public void setNumPlayers (int nNum) {

    nPlayers = nNum;

    if (nPlayers == 2) {

      player3Name.setVisible(false);
      player4Name.setVisible(false);

    } else if (nPlayers == 3) {

      player4Name.setVisible(false);

    }

  }

  public void initialize () {

    Image backdropImage = new Image("/Images/Main/Name Screen.png");
    backdrop.setImage(backdropImage);

    confirmButton.setDefaultButton(true);
    confirmButton.setStyle("-fx-cursor: hand");

    player1Name.setDisable(true);
    player1Name.setDisable(false);

    nameFields.add(player1Name);
    nameFields.add(player2Name);
    nameFields.add(player3Name);
    nameFields.add(player4Name);


  }

  public void setNames () {

    masterObject.getPlayerList().get(0).setName(player1Name.getText());
    masterObject.getPlayerList().get(1).setName(player2Name.getText());

    if (masterObject.getNumPlayers() >= 3)
      masterObject.getPlayerList().get(2).setName(player3Name.getText());

    if (masterObject.getNumPlayers() == 4)
      masterObject.getPlayerList().get(3).setName(player4Name.getText());

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
