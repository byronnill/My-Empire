package Controller;

import Model.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.event.*;

import java.io.*;

public class BoardStartUpRandomController {

  private Game masterObject;

  @FXML
  private ImageView img0, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31;

  @FXML
  private ImageView backdrop;

  @FXML
  private Button startGame, randomizeAgain;

  private Image gray0_, gray1_, purple0_, purple1_, purple2_, pink0_, pink1_, pink2_, green0_, green1_, green2_, blue0_, blue1_, blue2_, orange0_, orange1_, yellow0_, yellow1_, rail0_, rail1_, rail2_, util0_, util1_, chance, income, luxury, start, park, jail, service, backdropImage;

  public void setGame (Game game) {

    this.masterObject = game;
    masterObject.setGameBoard(new Board(masterObject.getNumPlayers()));

    backdrop.setImage(backdropImage);

    startGame.setDefaultButton(true);

    startGame.setStyle("-fx-cursor: hand");
    randomizeAgain.setStyle("-fx-cursor: hand");

    for (int i = 0; i < 32; i++) {

      Space currSpace;

      currSpace = game.getGameBoard().getBoardSpaces().get(i);

      if (i == 0 || i == 8 || i == 16 || i == 24) {

        switch (i) {
          case 0: img0.setImage(start);
          case 8: img8.setImage(park);
          case 16: img16.setImage(jail);
          case 24: img24.setImage(service);

        }

      } else if (currSpace instanceof ChanceSpace) {
        returnImageView(i).setImage(chance);

      } else if (currSpace instanceof TaxSpace) {

        if (((TaxSpace) currSpace).isIncome())
          returnImageView(i).setImage(income);
        else
          returnImageView(i).setImage(luxury);

      } else if (currSpace instanceof OwnableSpace) {

        returnImageView(i).setImage(returnImage(((OwnableSpace) currSpace).getID()));

      }

      if (i > 0 && i < 8)
        returnImageView(i).setRotate(180);
      else if (i > 8 && i < 16)
        returnImageView(i).setRotate(270);
      else if (i > 24 && i < 32)
        returnImageView(i).setRotate(90);

    }

  }

  public void initialize () {

    backdropImage = new Image("/Images/Main/Board Setup Screen Random.png");
    gray0_ = new Image("/Images/Property Space/Gray/Almond Drive/0.png");
    gray1_ = new Image("/Images/Property Space/Gray/Kasoy Street/0.png");
    purple0_ = new Image("/Images/Property Space/Purple/Rodeo Drive/0.png");
    purple1_ = new Image("/Images/Property Space/Purple/Orange Street/0.png");
    purple2_ = new Image("/Images/Property Space/Purple/Ventura Street/0.png");
    pink0_ = new Image("/Images/Property Space/Pink/Juan Luna/0.png");
    pink1_ = new Image("/Images/Property Space/Pink/Ylaya/0.png");
    pink2_ = new Image("/Images/Property Space/Pink/J Abad Santos/0.png");
    green0_ = new Image("/Images/Property Space/Green/Madison/0.png");
    green1_ = new Image("/Images/Property Space/Green/Annapolis/0.png");
    green2_ = new Image("/Images/Property Space/Green/Connecticut/0.png");
    blue0_ = new Image("/Images/Property Space/Blue/Bougainvilla/0.png");
    blue1_ = new Image("/Images/Property Space/Blue/Dama De Noche/0.png");
    blue2_ = new Image("/Images/Property Space/Blue/Acacia/0.png");
    orange0_ = new Image("/Images/Property Space/Orange/Solar Street/0.png");
    orange1_ = new Image("/Images/Property Space/Orange/Galaxy Street/0.png");
    yellow0_ = new Image("/Images/Property Space/Yellow/9th Street/0.png");
    yellow1_ = new Image("/Images/Property Space/Yellow/5th Avenue/0.png");
    rail0_ = new Image("/Images/Railroad Space/North Line/1.png");
    rail1_ = new Image("/Images/Railroad Space/South Line/1.png");
    rail2_ = new Image("/Images/Railroad Space/Metro Line/1.png");
    util0_ = new Image("/Images/Utility Space/Water/1.png");
    util1_ = new Image("/Images/Utility Space/Electric/1.png");
    chance = new Image("/Images/Tax - Chance Space/Chance.png");
    income = new Image("/Images/Tax - Chance Space/Income Tax.png");
    luxury = new Image("/Images/Tax - Chance Space/Luxury Tax.png");
    start = new Image("/Images/Corner Space/Start.png");
    jail = new Image("/Images/Corner Space/Jail.png");
    park = new Image("/Images/Corner Space/Free Parking.png");
    service = new Image("/Images/Corner Space/Community Service.png");

  }

  public void handleRandomize () {

    Board newBoard = new Board(masterObject.getNumPlayers());
    masterObject.setGameBoard(newBoard);

    setGame(masterObject);

  }

  public void handleStart (ActionEvent event) {

    masterObject.getGameBoard().consolePrintBoard();
    masterObject.setGameDeck(new Deck(masterObject.getGameBoard()));

    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GameBoard.fxml"));
      Parent root = loader.load();
      GameBoardController gameBoardController = loader.getController();

      gameBoardController.setGame(masterObject);

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

  public Image returnImage(int nID){
    switch(nID){
      case 0: return gray0_;
      case 1: return gray1_;
      case 10: return purple0_;
      case 11: return purple1_;
      case 12: return purple2_;
      case 20: return pink0_;
      case 21: return pink1_;
      case 22: return pink2_;
      case 30: return green0_;
      case 31: return green1_;
      case 32: return green2_;
      case 40: return blue0_;
      case 41: return blue1_;
      case 42: return blue2_;
      case 50: return orange0_;
      case 51: return orange1_;
      case 60: return yellow0_;
      case 61: return yellow1_;
      case 100: return rail0_;
      case 200: return rail1_;
      case 300: return rail2_;
      case 1000: return util0_;
      case 2000: return util1_;
      default: return null;
    }
  }

  public ImageView returnImageView(int nIndex){
    switch(nIndex){
      case 1: return img1;
      case 2: return img2;
      case 3: return img3;
      case 4: return img4;
      case 5: return img5;
      case 6: return img6;
      case 7: return img7;
      case 9: return img9;
      case 10: return img10;
      case 11: return img11;
      case 12: return img12;
      case 13: return img13;
      case 14: return img14;
      case 15: return img15;
      case 17: return img17;
      case 18: return img18;
      case 19: return img19;
      case 20: return img20;
      case 21: return img21;
      case 22: return img22;
      case 23: return img23;
      case 25: return img25;
      case 26: return img26;
      case 27: return img27;
      case 28: return img28;
      case 29: return img29;
      case 30: return img30;
      case 31: return img31;
      default: return null;
    }
  }

}
