package Controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.*;

import java.io.*;
import java.util.*;

public class InstructionsController {

  @FXML
  private ImageView backdrop, instructions, muteButton;

  @FXML
  private Button beginning, players, spaces, chanceCards, ending, goBack, backToTop;

  @FXML
  private Button corners, unownable, property, railutil;

  @FXML
  private Button actions, cash, owned;

  private ArrayList <Button> mainControls, spaceControls, playerControls;

  private Image leftImage, beginImage, chanceImage, endImage, cornerImage, unownableImage, propImage, railUtilImage, actionsImage, cashImage, ownedImage;
  private MediaPlayer player;

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

  public void initialize () {

    leftImage = new Image("/Images/Main/Instructions/Left.png");
    beginImage = new Image("/Images/Main/Instructions/Beginning.png");
    chanceImage = new Image("/Images/Main/Instructions/Chance.png");
    endImage = new Image("/Images/Main/Instructions/Ending.png");

    cornerImage = new Image("/Images/Main/Instructions/Space Corner.png");
    unownableImage = new Image("/Images/Main/Instructions/Space Unownable.png");
    propImage = new Image("/Images/Main/Instructions/Space Property.png");
    railUtilImage = new Image("/Images/Main/Instructions/Space RailUtil.png");

    actionsImage = new Image("/Images/Main/Instructions/Player Actions.png");
    cashImage = new Image("/Images/Main/Instructions/Player Cash.png");
    ownedImage = new Image("/Images/Main/Instructions/Player Properties.png");

    mainControls = new ArrayList <> (6);
    spaceControls = new ArrayList <> (4);
    playerControls = new ArrayList <> (3);

    mainControls.add(beginning);
    mainControls.add(players);
    mainControls.add(spaces);
    mainControls.add(chanceCards);
    mainControls.add(ending);
    mainControls.add(goBack);

    spaceControls.add(corners);
    spaceControls.add(unownable);
    spaceControls.add(property);
    spaceControls.add(railutil);

    playerControls.add(actions);
    playerControls.add(cash);
    playerControls.add(owned);

    backdrop.setImage(leftImage);
    instructions.setImage(beginImage);

    disableGroup(spaceControls);
    disableGroup(playerControls);

    beginning.setDisable(true);
    backToTop.setVisible(false);

    for (Button b : mainControls)
      b.setStyle("-fx-cursor: hand");

    for (Button b : spaceControls)
      b.setStyle("-fx-cursor: hand");

    for (Button b : playerControls)
      b.setStyle("-fx-cursor: hand");

    muteButton.setStyle("-fx-cursor: hand");

  }

  public void disableGroup (ArrayList <Button> toDisable) {

    for (Button b : toDisable)
      b.setVisible(false);

  }

  public void enableGroup (ArrayList <Button> toDisable) {

    for (Button b : toDisable) {
      b.setDisable(false);
      b.setVisible(true);
    }

  }

  public void handleBegin () {

    enableGroup(mainControls);

    instructions.setImage(beginImage);
    beginning.setDisable(true);

  }

  public void handlePlayers () {

    instructions.setImage(actionsImage);

    disableGroup(mainControls);
    enableGroup(playerControls);

    actions.setDisable(true);
    backToTop.setVisible(true);

    goBack.setVisible(false);

  }

  public void handleSpaces () {

    instructions.setImage(cornerImage);

    disableGroup(mainControls);
    enableGroup(spaceControls);

    corners.setDisable(true);
    backToTop.setVisible(true);

    goBack.setVisible(false);

  }

  public void handleChance () {

    enableGroup(mainControls);

    instructions.setImage(chanceImage);
    chanceCards.setDisable(true);

  }

  public void handleEnd () {

    enableGroup(mainControls);

    instructions.setImage(endImage);
    ending.setDisable(true);

  }

  public void handleCorners () {

    enableGroup(spaceControls);

    instructions.setImage(cornerImage);
    corners.setDisable(true);

  }

  public void handleUnownable () {

    enableGroup(spaceControls);

    instructions.setImage(unownableImage);
    unownable.setDisable(true);

  }

  public void handleProps () {

    enableGroup(spaceControls);

    instructions.setImage(propImage);
    property.setDisable(true);

  }

  public void handleRailUtil () {

    enableGroup(spaceControls);

    instructions.setImage(railUtilImage);
    railutil.setDisable(true);

  }

  public void handleActions () {

    enableGroup(playerControls);

    instructions.setImage(actionsImage);
    actions.setDisable(true);

  }

  public void handleCash () {

    enableGroup(playerControls);

    instructions.setImage(cashImage);
    cash.setDisable(true);

  }

  public void handleOwned () {

    enableGroup(playerControls);

    instructions.setImage(ownedImage);
    owned.setDisable(true);

  }

  public void handleTop () {

    disableGroup(playerControls);
    disableGroup(spaceControls);

    enableGroup(mainControls);
    instructions.setImage(beginImage);

    beginning.setDisable(true);

    backToTop.setVisible(false);

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
