package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class BoardStartUpDragController {

  private Game masterObject;
  private int count = 28;

  @FXML
  private ImageView img0, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31;

  @FXML
  private ImageView imgCurrent, imgSelect, imgGray0, imgGray1, imgPurple0, imgPurple1, imgPurple2, imgPink0, imgPink1, imgPink2, imgGreen0, imgGreen1, imgGreen2, imgBlue0, imgBlue1, imgBlue2, imgOrange0, imgOrange1, imgYellow0, imgYellow1, imgRail0, imgRail1, imgRail2, imgUtil0, imgUtil1, imgChance0, imgChance1, imgChance2, imgIncome, imgLuxury;

  @FXML
  private ImageView backdrop, instructionImg;

  @FXML
  private Button startGame, randomizeInstead;

  @FXML
  private GridPane gridCards;

  private ImageView imgTemp = new ImageView();
  private Image gray0, gray0_, gray1, gray1_, purple0, purple0_, purple1, purple1_, purple2, purple2_, pink0, pink0_, pink1, pink1_, pink2, pink2_, green0, green0_, green1, green1_, green2, green2_, blue0, blue0_, blue1, blue1_, blue2, blue2_, orange0, orange0_, orange1, orange1_, yellow0, yellow0_, yellow1, yellow1_, rail0, rail0_, rail1, rail1_, rail2, rail2_, util0, util0_, util1, util1_, chance0, chance1, chance2, income, luxury, start, park, jail, service, backdropImage, instruction1, instruction2;
  private ArrayList <ImageView> centerGrid;
  private ArrayList <ImageView> sideGrid;

  public void setGame (Game game) {

    this.masterObject = game;
    masterObject.setGameBoard(new Board());

    startGame.setDefaultButton(true);
    startGame.setVisible(false);

    startGame.setStyle("-fx-cursor: hand");
    randomizeInstead.setStyle("-fx-cursor: hand");

    centerGrid = new ArrayList <> ();
    sideGrid = new ArrayList <> ();

    backdrop.setImage(backdropImage);
    instructionImg.setImage(instruction1);
    imgGray0.setImage(gray0);
    imgGray1.setImage(gray1);
    imgPurple0.setImage(purple0);
    imgPurple1.setImage(purple1);
    imgPurple2.setImage(purple2);
    imgPink0.setImage(pink0);
    imgPink1.setImage(pink1);
    imgPink2.setImage(pink2);
    imgGreen0.setImage(green0);
    imgGreen1.setImage(green1);
    imgGreen2.setImage(green2);
    imgBlue0.setImage(blue0);
    imgBlue1.setImage(blue1);
    imgBlue2.setImage(blue2);
    imgOrange0.setImage(orange0);
    imgOrange1.setImage(orange1);
    imgYellow0.setImage(yellow0);
    imgYellow1.setImage(yellow1);
    imgRail0.setImage(rail0);
    imgRail1.setImage(rail1);
    imgRail2.setImage(rail2);
    imgUtil0.setImage(util0);
    imgUtil1.setImage(util1);
    imgChance0.setImage(chance0);
    imgChance1.setImage(chance1);
    imgChance2.setImage(chance2);
    imgIncome.setImage(income);
    imgLuxury.setImage(luxury);
    img0.setImage(start);
    img8.setImage(park);
    img16.setImage(jail);
    img24.setImage(service);

    sideGrid.add(imgGray0);
    sideGrid.add(imgGray1);
    sideGrid.add(imgPurple0);
    sideGrid.add(imgPurple1);
    sideGrid.add(imgPurple2);
    sideGrid.add(imgPink0);
    sideGrid.add(imgPink1);
    sideGrid.add(imgPink2);
    sideGrid.add(imgGreen0);
    sideGrid.add(imgGreen1);
    sideGrid.add(imgGreen2);
    sideGrid.add(imgBlue0);
    sideGrid.add(imgBlue1);
    sideGrid.add(imgBlue2);
    sideGrid.add(imgOrange0);
    sideGrid.add(imgOrange1);
    sideGrid.add(imgYellow0);
    sideGrid.add(imgYellow1);
    sideGrid.add(imgRail0);
    sideGrid.add(imgRail1);
    sideGrid.add(imgRail2);
    sideGrid.add(imgUtil0);
    sideGrid.add(imgUtil1);
    sideGrid.add(imgChance0);
    sideGrid.add(imgChance1);
    sideGrid.add(imgChance2);
    sideGrid.add(imgIncome);
    sideGrid.add(imgLuxury);

    centerGrid.add(img1);
    centerGrid.add(img2);
    centerGrid.add(img3);
    centerGrid.add(img4);
    centerGrid.add(img5);
    centerGrid.add(img6);
    centerGrid.add(img7);
    centerGrid.add(img9);
    centerGrid.add(img10);
    centerGrid.add(img11);
    centerGrid.add(img12);
    centerGrid.add(img13);
    centerGrid.add(img14);
    centerGrid.add(img15);
    centerGrid.add(img17);
    centerGrid.add(img18);
    centerGrid.add(img19);
    centerGrid.add(img20);
    centerGrid.add(img21);
    centerGrid.add(img22);
    centerGrid.add(img23);
    centerGrid.add(img25);
    centerGrid.add(img26);
    centerGrid.add(img27);
    centerGrid.add(img28);
    centerGrid.add(img29);
    centerGrid.add(img30);
    centerGrid.add(img31);

    changeCursorSelection();

  }

  public void initialize () {

    backdropImage = new Image("/Images/Main/Board Setup Screen.png");
    instruction1 = new Image("/Images/Main/Drag Instructions Choose.png");
    instruction2 = new Image("/Images/Main/Drag Instructions Place.png");
    gray0 = new Image("/Images/Property Space/Gray/Almond Drive/Full.png");
    gray0_ = new Image("/Images/Property Space/Gray/Almond Drive/0.png");
    gray1 = new Image("/Images/Property Space/Gray/Kasoy Street/Full.png");
    gray1_ = new Image("/Images/Property Space/Gray/Kasoy Street/0.png");
    purple0 = new Image("/Images/Property Space/Purple/Rodeo Drive/Full.png");
    purple0_ = new Image("/Images/Property Space/Purple/Rodeo Drive/0.png");
    purple1 = new Image("/Images/Property Space/Purple/Orange Street/Full.png");
    purple1_ = new Image("/Images/Property Space/Purple/Orange Street/0.png");
    purple2 = new Image("/Images/Property Space/Purple/Ventura Street/Full.png");
    purple2_ = new Image("/Images/Property Space/Purple/Ventura Street/0.png");
    pink0 = new Image("/Images/Property Space/Pink/Juan Luna/Full.png");
    pink0_ = new Image("/Images/Property Space/Pink/Juan Luna/0.png");
    pink1 = new Image("/Images/Property Space/Pink/Ylaya/Full.png");
    pink1_ = new Image("/Images/Property Space/Pink/Ylaya/0.png");
    pink2 = new Image("/Images/Property Space/Pink/J Abad Santos/Full.png");
    pink2_ = new Image("/Images/Property Space/Pink/J Abad Santos/0.png");
    green0 = new Image("/Images/Property Space/Green/Madison/Full.png");
    green0_ = new Image("/Images/Property Space/Green/Madison/0.png");
    green1 = new Image("/Images/Property Space/Green/Annapolis/Full.png");
    green1_ = new Image("/Images/Property Space/Green/Annapolis/0.png");
    green2 = new Image("/Images/Property Space/Green/Connecticut/Full.png");
    green2_ = new Image("/Images/Property Space/Green/Connecticut/0.png");
    blue0 = new Image("/Images/Property Space/Blue/Bougainvilla/Full.png");
    blue0_ = new Image("/Images/Property Space/Blue/Bougainvilla/0.png");
    blue1 = new Image("/Images/Property Space/Blue/Dama De Noche/Full.png");
    blue1_ = new Image("/Images/Property Space/Blue/Dama De Noche/0.png");
    blue2 = new Image("/Images/Property Space/Blue/Acacia/Full.png");
    blue2_ = new Image("/Images/Property Space/Blue/Acacia/0.png");
    orange0 = new Image("/Images/Property Space/Orange/Solar Street/Full.png");
    orange0_ = new Image("/Images/Property Space/Orange/Solar Street/0.png");
    orange1 = new Image("/Images/Property Space/Orange/Galaxy Street/Full.png");
    orange1_ = new Image("/Images/Property Space/Orange/Galaxy Street/0.png");
    yellow0 = new Image("/Images/Property Space/Yellow/9th Street/Full.png");
    yellow0_ = new Image("/Images/Property Space/Yellow/9th Street/0.png");
    yellow1 = new Image("/Images/Property Space/Yellow/5th Avenue/Full.png");
    yellow1_ = new Image("/Images/Property Space/Yellow/5th Avenue/0.png");
    rail0 = new Image("/Images/Railroad Space/North Line/Full.png");
    rail0_ = new Image("/Images/Railroad Space/North Line/1.png");
    rail1 = new Image("/Images/Railroad Space/South Line/Full.png");
    rail1_ = new Image("/Images/Railroad Space/South Line/1.png");
    rail2 = new Image("/Images/Railroad Space/Metro Line/Full.png");
    rail2_ = new Image("/Images/Railroad Space/Metro Line/1.png");
    util0 = new Image("/Images/Utility Space/Water/Full.png");
    util0_ = new Image("/Images/Utility Space/Water/1.png");
    util1 = new Image("/Images/Utility Space/Electric/Full.png");
    util1_ = new Image("/Images/Utility Space/Electric/1.png");
    chance0 = new Image("/Images/Tax - Chance Space/Chance.png");
    chance1 = new Image("/Images/Tax - Chance Space/Chance.png");
    chance2 = new Image("/Images/Tax - Chance Space/Chance.png");
    income = new Image("/Images/Tax - Chance Space/Income Tax.png");
    luxury = new Image("/Images/Tax - Chance Space/Luxury Tax.png");
    start = new Image("/Images/Corner Space/Start.png");
    jail = new Image("/Images/Corner Space/Jail.png");
    park = new Image("/Images/Corner Space/Free Parking.png");
    service = new Image("/Images/Corner Space/Community Service.png");

  }

  public void changeCursorSelection () {

    for (ImageView iv : sideGrid) {
      if (iv.getImage() != null)
        iv.setStyle("-fx-cursor: hand");
      else
        iv.setStyle("-fx-cursor: default");
    }

    for (ImageView iv : centerGrid) {
      iv.setStyle("-fx-cursor: default");
    }

  }

  public void changeCursorSelected () {

    for (ImageView iv : centerGrid) {
      if (iv.getImage() != null)
        iv.setStyle("-fx-cursor: default");
      else
        iv.setStyle("-fx-cursor: hand");
    }

    for (ImageView iv : sideGrid) {
      iv.setStyle("-fx-cursor: default");
    }

    imgSelect.setStyle("-fx-cursor: hand");

  }

  public void reduceCount () {
    this.count--;
  }

  public int getCount () {
    return count;
  }

  public void enlargeGray0() {
    imgCurrent.setImage(imgGray0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeGray1() {
    imgCurrent.setImage(imgGray1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargePurple0() {
    imgCurrent.setImage(imgPurple0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargePurple1() {
    imgCurrent.setImage(imgPurple1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargePurple2() {
    imgCurrent.setImage(imgPurple2.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargePink0() {
    imgCurrent.setImage(imgPink0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargePink1() {
    imgCurrent.setImage(imgPink1.getImage());
    imgCurrent.setVisible(true);

  }

  public void enlargePink2() {
    imgCurrent.setImage(imgPink2.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeGreen0() {
    imgCurrent.setImage(imgGreen0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeGreen1() {
    imgCurrent.setImage(imgGreen1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeGreen2() {
    imgCurrent.setImage(imgGreen2.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeBlue0() {
    imgCurrent.setImage(imgBlue0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeBlue1() {
    imgCurrent.setImage(imgBlue1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeBlue2() {
    imgCurrent.setImage(imgBlue2.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeOrange0() {
    imgCurrent.setImage(imgOrange0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeOrange1() {
    imgCurrent.setImage(imgOrange1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeYellow0() {
    imgCurrent.setImage(imgYellow0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeYellow1() {
    imgCurrent.setImage(imgYellow1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeRail0() {
    imgCurrent.setImage(imgRail0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeRail1() {
    imgCurrent.setImage(imgRail1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeRail2() {
    imgCurrent.setImage(imgRail2.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeUtil0() {
    imgCurrent.setImage(imgUtil0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeUtil1() {
    imgCurrent.setImage(imgUtil1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeChance0() {
    imgCurrent.setImage(imgChance0.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeChance1() {
    imgCurrent.setImage(imgChance1.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeChance2() {
    imgCurrent.setImage(imgChance2.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeIncome() {
    imgCurrent.setImage(imgIncome.getImage());
    imgCurrent.setVisible(true);
  }

  public void enlargeLuxury() {
    imgCurrent.setImage(imgLuxury.getImage());
    imgCurrent.setVisible(true);
  }

  public void revertCard() {
    imgCurrent.setVisible(false);
  }

  public void revertSelected(){
    for (int i = 0; i < 28; i++){
      if (imgSelect.getImage() == returnImageCards(i).getImage()){
        gridCards.setDisable(false);
        imgSelect.setImage(null);
        imgTemp.setImage(null);
        returnImageCards(i).setVisible(true);
      }
    }
    instructionImg.setImage(instruction1);
    changeCursorSelection();
  }

  public void selectGray0() {
    imgSelect.setImage(gray0);
    imgSelect.setVisible(true);

    imgTemp.setImage(gray0_);
    imgTemp.setAccessibleText("0");

    imgGray0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectGray1() {
    imgSelect.setImage(gray1);
    imgSelect.setVisible(true);

    imgTemp.setImage(gray1_);
    imgTemp.setAccessibleText("1");

    imgGray1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectPurple0() {
    imgSelect.setImage(purple0);
    imgSelect.setVisible(true);

    imgTemp.setImage(purple0_);
    imgTemp.setAccessibleText("10");

    imgPurple0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectPurple1() {
    imgSelect.setImage(purple1);
    imgSelect.setVisible(true);

    imgTemp.setImage(purple1_);
    imgTemp.setAccessibleText("11");

    imgPurple1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectPurple2() {
    imgSelect.setImage(purple2);
    imgSelect.setVisible(true);

    imgTemp.setImage(purple2_);
    imgTemp.setAccessibleText("12");

    imgPurple2.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectPink0() {
    imgSelect.setImage(pink0);
    imgSelect.setVisible(true);

    imgTemp.setImage(pink0_);
    imgTemp.setAccessibleText("20");

    imgPink0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectPink1() {
    imgSelect.setImage(pink1);
    imgSelect.setVisible(true);

    imgTemp.setImage(pink1_);
    imgTemp.setAccessibleText("21");

    imgPink1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectPink2() {
    imgSelect.setImage(pink2);
    imgSelect.setVisible(true);

    imgTemp.setImage(pink2_);
    imgTemp.setAccessibleText("22");

    imgPink2.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectGreen0() {
    imgSelect.setImage(green0);
    imgSelect.setVisible(true);

    imgTemp.setImage(green0_);
    imgTemp.setAccessibleText("30");

    imgGreen0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectGreen1() {
    imgSelect.setImage(green1);
    imgSelect.setVisible(true);

    imgTemp.setImage(green1_);
    imgTemp.setAccessibleText("31");

    imgGreen1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectGreen2() {
    imgSelect.setImage(green2);
    imgSelect.setVisible(true);

    imgTemp.setImage(green2_);
    imgTemp.setAccessibleText("32");

    imgGreen2.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectBlue0() {
    imgSelect.setImage(blue0);
    imgSelect.setVisible(true);

    imgTemp.setImage(blue0_);
    imgTemp.setAccessibleText("40");

    imgBlue0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectBlue1() {
    imgSelect.setImage(blue1);
    imgSelect.setVisible(true);

    imgTemp.setImage(blue1_);
    imgTemp.setAccessibleText("41");

    imgBlue1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectBlue2() {
    imgSelect.setImage(blue2);
    imgSelect.setVisible(true);

    imgTemp.setImage(blue2_);
    imgTemp.setAccessibleText("42");

    imgBlue2.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectOrange0() {
    imgSelect.setImage(orange0);
    imgSelect.setVisible(true);

    imgTemp.setImage(orange0_);
    imgTemp.setAccessibleText("50");

    imgOrange0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectOrange1() {
    imgSelect.setImage(orange1);
    imgSelect.setVisible(true);

    imgTemp.setImage(orange1_);
    imgTemp.setAccessibleText("51");

    imgOrange1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectYellow0() {
    imgSelect.setImage(yellow0);
    imgSelect.setVisible(true);

    imgTemp.setImage(yellow0_);
    imgTemp.setAccessibleText("60");

    imgYellow0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectYellow1() {
    imgSelect.setImage(yellow1);
    imgSelect.setVisible(true);

    imgTemp.setImage(yellow1_);
    imgTemp.setAccessibleText("61");

    imgYellow1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectRail0() {
    imgSelect.setImage(rail0);
    imgSelect.setVisible(true);

    imgTemp.setImage(rail0_);
    imgTemp.setAccessibleText("100");

    imgRail0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectRail1() {
    imgSelect.setImage(rail1);
    imgSelect.setVisible(true);

    imgTemp.setImage(rail1_);
    imgTemp.setAccessibleText("200");

    imgRail1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectRail2() {
    imgSelect.setImage(rail2);
    imgSelect.setVisible(true);

    imgTemp.setImage(rail2_);
    imgTemp.setAccessibleText("300");

    imgRail2.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectUtil0() {
    imgSelect.setImage(util0);
    imgSelect.setVisible(true);

    imgTemp.setImage(util0_);
    imgTemp.setAccessibleText("1000");

    imgUtil0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectUtil1() {
    imgSelect.setImage(util1);
    imgSelect.setVisible(true);

    imgTemp.setImage(util1_);
    imgTemp.setAccessibleText("2000");

    imgUtil1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectChance0() {
    imgSelect.setImage(chance0);
    imgSelect.setVisible(true);

    imgTemp.setImage(chance0);
    imgTemp.setAccessibleText("-1");

    imgChance0.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectChance1() {
    imgSelect.setImage(chance1);
    imgSelect.setVisible(true);

    imgTemp.setImage(chance1);
    imgTemp.setAccessibleText("-1");

    imgChance1.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectChance2() {
    imgSelect.setImage(chance2);
    imgSelect.setVisible(true);

    imgTemp.setImage(chance2);
    imgTemp.setAccessibleText("-1");

    imgChance2.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectIncome() {
    imgSelect.setImage(income);
    imgSelect.setVisible(true);

    imgTemp.setImage(income);
    imgTemp.setAccessibleText("-2");

    imgIncome.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }
  public void selectLuxury() {
    imgSelect.setImage(luxury);
    imgSelect.setVisible(true);

    imgTemp.setImage(luxury);
    imgTemp.setAccessibleText("-3");

    imgLuxury.setVisible(false);
    imgCurrent.setVisible(false);
    gridCards.setDisable(true);

    instructionImg.setImage(instruction2);
    changeCursorSelected();
  }

  public void img1Press() {
    if (imgTemp.getImage() != null) {
      img1.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img1.setDisable(true);
      reduceCount();
      img1.setRotate(img1.getRotate() + 180);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(1, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img2Press() {
    if (imgTemp.getImage() != null) {
      img2.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img2.setDisable(true);
      reduceCount();
      img2.setRotate(img2.getRotate() + 180);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(2, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img3Press() {
    if (imgTemp.getImage() != null) {
      img3.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img3.setDisable(true);
      reduceCount();
      img3.setRotate(img3.getRotate() + 180);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(3, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img4Press() {
    if (imgTemp.getImage() != null) {
      img4.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img4.setDisable(true);
      reduceCount();
      img4.setRotate(img4.getRotate() + 180);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(4, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img5Press() {
    if (imgTemp.getImage() != null) {
      img5.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img5.setDisable(true);
      reduceCount();
      img5.setRotate(img5.getRotate() + 180);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(5, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img6Press() {
    if (imgTemp.getImage() != null) {
      img6.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img6.setDisable(true);
      reduceCount();
      img6.setRotate(img6.getRotate() + 180);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(6, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img7Press() {
    if (imgTemp.getImage() != null) {
      img7.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img7.setDisable(true);
      reduceCount();
      img7.setRotate(img7.getRotate() + 180);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(7, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img9Press() {
    if (imgTemp.getImage() != null) {
      img9.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img9.setDisable(true);
      reduceCount();
      img9.setRotate(img9.getRotate() + 270);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(9, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img10Press() {
    if (imgTemp.getImage() != null) {
      img10.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img10.setDisable(true);
      reduceCount();
      img10.setRotate(img10.getRotate() + 270);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(10, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img11Press() {
    if (imgTemp.getImage() != null) {
      img11.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img11.setDisable(true);
      reduceCount();
      img11.setRotate(img11.getRotate() + 270);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(11, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img12Press() {
    if (imgTemp.getImage() != null) {
      img12.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img12.setDisable(true);
      reduceCount();
      img12.setRotate(img12.getRotate() + 270);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(12, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img13Press() {
    if (imgTemp.getImage() != null) {
      img13.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img13.setDisable(true);
      reduceCount();
      img13.setRotate(img13.getRotate() + 270);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(13, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img14Press() {
    if (imgTemp.getImage() != null) {
      img14.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img14.setDisable(true);
      reduceCount();
      img14.setRotate(img14.getRotate() + 270);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(14, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img15Press() {
    if (imgTemp.getImage() != null) {
      img15.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img15.setDisable(true);
      reduceCount();
      img15.setRotate(img15.getRotate() + 270);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(15, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img17Press() {
    if (imgTemp.getImage() != null) {
      img17.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img17.setDisable(true);
      reduceCount();
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(17, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img18Press() {
    if (imgTemp.getImage() != null) {
      img18.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img18.setDisable(true);
      reduceCount();
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(18, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img19Press() {
    if (imgTemp.getImage() != null) {
      img19.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img19.setDisable(true);
      reduceCount();
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(19, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img20Press() {
    if (imgTemp.getImage() != null) {
      img20.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img20.setDisable(true);
      reduceCount();
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(20, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img21Press() {
    if (imgTemp.getImage() != null) {
      img21.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img21.setDisable(true);
      reduceCount();
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(21, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img22Press() {
    if (imgTemp.getImage() != null) {
      img22.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img22.setDisable(true);
      reduceCount();
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(22, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img23Press() {
    if (imgTemp.getImage() != null) {
      img23.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img23.setDisable(true);
      reduceCount();
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(23, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img25Press() {
    if (imgTemp.getImage() != null) {
      img25.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img25.setDisable(true);
      reduceCount();
      img25.setRotate(img25.getRotate() + 90);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(25, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img26Press() {
    if (imgTemp.getImage() != null) {
      img26.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img26.setDisable(true);
      reduceCount();
      img26.setRotate(img26.getRotate() + 90);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(26, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img27Press() {
    if (imgTemp.getImage() != null) {
      img27.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img27.setDisable(true);
      reduceCount();
      img27.setRotate(img27.getRotate() + 90);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(27, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img28Press() {
    if (imgTemp.getImage() != null) {
      img28.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img28.setDisable(true);
      reduceCount();
      img28.setRotate(img28.getRotate() + 90);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(28, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img29Press() {
    if (imgTemp.getImage() != null) {
      img29.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img29.setDisable(true);
      reduceCount();
      img29.setRotate(img29.getRotate() + 90);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(29, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img30Press() {
    if (imgTemp.getImage() != null) {
      img30.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img30.setDisable(true);
      reduceCount();
      img30.setRotate(img30.getRotate() + 90);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(30, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }
  public void img31Press() {
    if (imgTemp.getImage() != null) {
      img31.setImage(imgTemp.getImage());
      imgTemp.setImage(null);
      gridCards.setDisable(false);
      img31.setDisable(true);
      reduceCount();
      img31.setRotate(img31.getRotate() + 90);
      imgSelect.setVisible(false);
      if (getCount() == 0) {
        startGame.setVisible(true);
        startGame.setDisable(false);
        gridCards.setVisible(false);
      }
      masterObject.getGameBoard().setNewSpace(31, Integer.parseInt(imgTemp.getAccessibleText()), masterObject.getNumPlayers());

      instructionImg.setImage(instruction1);

      changeCursorSelection();
    }
  }

  public Image returnImage(int nID) {
    switch(nID) {
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

  public ImageView returnImageView(int nIndex) {
    switch(nIndex) {
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

  public ImageView returnImageCards(int nIndex){
    switch(nIndex){
      case 0: return imgGray0;
      case 1: return imgGray1;
      case 2: return imgPurple0;
      case 3: return imgPurple1;
      case 4: return imgPurple2;
      case 5: return imgPink0;
      case 6: return imgPink1;
      case 7: return imgPink2;
      case 8: return imgGreen0;
      case 9: return imgGreen1;
      case 10: return imgGreen2;
      case 11: return imgBlue0;
      case 12: return imgBlue1;
      case 13: return imgBlue2;
      case 14: return imgOrange0;
      case 15: return imgOrange1;
      case 16: return imgYellow0;
      case 17: return imgYellow1;
      case 18: return imgRail0;
      case 19: return imgRail1;
      case 20: return imgRail2;
      case 21: return imgUtil0;
      case 22: return imgUtil1;
      case 23: return imgChance0;
      case 24: return imgChance1;
      case 25: return imgChance2;
      case 26: return imgIncome;
      case 27: return imgLuxury;
      default: return null;
    }
  }

  public void handleStart(ActionEvent event) {

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

  public void handleRandomize () { //toEdit
    gridCards.setDisable(true);
    gridCards.setVisible(false);

    startGame.setVisible(true);
    startGame.setDisable(false);

    instructionImg.setVisible(false);

    randomizeInstead.setText("Randomize Again");

    masterObject.setGameBoard(new Board(masterObject.getNumPlayers()));
    masterObject.getGameBoard().consolePrintBoard();

    for (int i = 0; i < 32; i++) {

      Space currSpace;

      currSpace = masterObject.getGameBoard().getBoardSpaces().get(i);

      if (i == 0 || i == 8 || i == 16 || i == 24) {


        switch (i) {
          case 0: img0.setImage(start);
          case 8: img8.setImage(park);
          case 16: img16.setImage(jail);
          case 24: img24.setImage(service);
        }

      } else if (currSpace instanceof ChanceSpace) {
        returnImageView(i).setImage(chance0);

      } else if (currSpace instanceof TaxSpace) {

        if (((TaxSpace) currSpace).isIncome())
          returnImageView(i).setImage(income);
        else
          returnImageView(i).setImage(luxury);

      } else if (currSpace instanceof Property) {

        returnImageView(i).setImage(returnImage(((OwnableSpace) currSpace).getID()));

      } else if (currSpace instanceof Railroad) {

        returnImageView(i).setImage(returnImage(((OwnableSpace) currSpace).getID()));

      } else if (currSpace instanceof Utility) {

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

}
