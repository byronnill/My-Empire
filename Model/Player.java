package Model;

import java.util.*;

public class Player {

  private int nPlayerNum; //[0, 4)
  private int nLocationIndex;
  private String strName;
  private double dCash;
  private int nOwned;
  private int[] nOwnedPerType;
  private ArrayList <OwnableSpace> ownedSpaces;
  private boolean bInJail;
  private ArrayList <CardGroup1> jailChanceCards;

  public Player (int nPlayerNum) {

    this.nPlayerNum = nPlayerNum;
    this.nLocationIndex = 0;

    this.strName = "Player " + nPlayerNum; //default name, changeable in GUI

    this.dCash = 1500;

    this.nOwned = 0;
    this.nOwnedPerType = new int[9];
    for (int i = 0; i < 9; i++)
      this.nOwnedPerType[i] = 0;

    this.ownedSpaces = new ArrayList <> ();

    this.bInJail = false;
    this.jailChanceCards = new ArrayList <> ();

  }

  public boolean isFreedomPossible () {

    if (!this.jailChanceCards.isEmpty()) {

      this.removeJailChance();
      this.bInJail = false;
      return true;

    } else if (this.dCash > 50) {

      this.dCash -= 50;
      this.bInJail = false;
      return true;

    } else {

      return false;

    }

  }

  public boolean movePlayer (Board board, int numSteps, boolean willCollectOnStart, Bank bank) {

    for (int i = 0; i < numSteps; i++) {

      this.nLocationIndex = this.nLocationIndex + 1 % 32;

      if (board.getBoardSpaces().get(this.nLocationIndex) instanceof Property)
        ((Property) board.getBoardSpaces().get(this.nLocationIndex)).addFootTraffic();

      else if (this.nLocationIndex == 0 && willCollectOnStart) {//start

        if (bank.getValue() <= 200)
          return false;

        this.dCash += 200;
        bank.addOrDeduct(-200);

      }

    }

    return true; //bank is not bankrupt

  }

  public boolean isPaymentPossible (double dToPay) {
    return this.dCash > dToPay;
  }

  public void payPlayer (double dToPay, Player playerPayee) {

    this.dCash -= dToPay;
    playerPayee.dCash += dToPay;

  }

  public void payBank (double dToPay, Bank bank) {

    this.dCash -= dToPay;
    bank.addOrDeduct(dToPay);

  }

  private void adjustPropertyRents (int nType) {

    if (this.nOwnedPerType[nType] == 1) {

      for (OwnableSpace s : this.ownedSpaces) {

        if (s instanceof Property && ((Property) s).getColorIndex() == nType)
          s.setRent(Reference.RENT[((Property) s).getColorIndex()][((Property) s).getPropertyIndex()][((Property) s).getDevelopment()]);

        else if (s instanceof Railroad && nType == 7)
          s.setRent(50);

        else if (s instanceof Utility && nType == 8)
          s.setRent(10);

      }

    } else if (this.nOwnedPerType[nType] == 2) {

      for (OwnableSpace s : this.ownedSpaces) {

        if (s instanceof Property && ((Property) s).getColorIndex() == nType)
          s.setRent(Reference.RENT[((Property) s).getColorIndex()][((Property) s).getPropertyIndex()][((Property) s).getDevelopment()] + 10);

        else if (s instanceof Railroad && nType == 7)
          s.setRent(50);

        else if (s instanceof Utility && nType == 8)
          s.setRent(10);

      }

    } else if (this.nOwnedPerType[nType] == 3) {

      for (OwnableSpace s : this.ownedSpaces) {

        if (s instanceof Property && ((Property) s).getColorIndex() == nType)
          s.setRent(Reference.RENT[((Property) s).getColorIndex()][((Property) s).getPropertyIndex()][((Property) s).getDevelopment()] + 20);

        else if (s instanceof Railroad && nType == 7)
          s.setRent(150);

      }

    }

  }

  public boolean buyProperty (OwnableSpace currSpace, Bank bank) {

    if (!currSpace.canBeBought(this))
      return false;

    else {

      currSpace.setOwner(this);

      this.payBank(currSpace.getPrice(), bank);
      this.addSpaceOwned(currSpace);

      return true;

    }

  }

  public boolean developProperty (Property toDevelop, Bank bank) {

    if (!toDevelop.canBeDeveloped(this))
      return false;

    else {

      toDevelop.addToDevelopment();

      if (toDevelop.getDevelopment() == 5) {
        this.payBank(toDevelop.getHotelPrice(), bank);
        toDevelop.addToWorth(toDevelop.getHotelPrice());
      }

      else {
        this.payBank(toDevelop.getHousePrice(), bank);
        toDevelop.addToWorth(toDevelop.getHousePrice());
      }

      this.adjustPropertyRents(toDevelop.getColorIndex());

      return true;
    }

  }

  public boolean payRent (OwnableSpace currSpace, Player otherOwner, Game game, Deck deck) {

    double dToPay;

    if (currSpace instanceof Property) {

      dToPay = currSpace.getRent();

      if (((Property) currSpace).isDoubleRent()) {

        ((Property) currSpace).setDoubleRent(false);
        currSpace.setRent(currSpace.getRent() / 2);

        deck.discardCard(((Property) currSpace).getDoubleRentHolder());

        ((Property) currSpace).setDoubleRentHolder(null);

      }

    }

    else if (currSpace instanceof Railroad)
      dToPay = currSpace.getRent();

    else
      dToPay = currSpace.getRent() * game.rollDice();


    if (!this.isPaymentPossible(dToPay))
      return false;

    else {

      payPlayer(dToPay, otherOwner);

      if (currSpace instanceof Property)
        ((Property) currSpace).addToCollected();

      return true;

    }

  }

  public void tradeProperty (OwnableSpace playerSpace, OwnableSpace otherSpace, Player otherPlayer) {

    //giving otherSpace to this player

    otherSpace.setOwner(this);

    this.addSpaceOwned(otherSpace);
    otherPlayer.removeSpaceOwned(otherSpace);

    //giving playerSpace to otherPlayer

    playerSpace.setOwner(otherPlayer);

    otherPlayer.addSpaceOwned(playerSpace);
    this.removeSpaceOwned(playerSpace);

  }

  /*public void tradeProperty (Space currSpace, Player oldOwner) {

    if (currSpace instanceof Property) {

      ((Property) currSpace).setOwner(this);

      this.addSpaceOwned(currSpace);
      this.adjustPropertyRents(((Property) currSpace).getColorIndex());

      oldOwner.removeSpace(currSpace);
      oldOwner.nOwned--;
      oldOwner.nOwnedPerType[((Property) currSpace).getColorIndex()]--;
      oldOwner.adjustPropertyRents(((Property) currSpace).getColorIndex());

    } else if (currSpace instanceof Railroad) {

      ((Railroad) currSpace).setOwner(this);

      this.addSpaceOwned(currSpace);
      this.adjustPropertyRents(7);

      oldOwner.removeSpace(currSpace);
      oldOwner.nOwned--;
      oldOwner.nOwnedPerType[7]--;
      oldOwner.adjustPropertyRents(7);

    } else if (currSpace instanceof Utility) {

      ((Utility) currSpace).setOwner(this);

      this.addSpaceOwned(currSpace);
      this.adjustPropertyRents(8);

      oldOwner.removeSpace(currSpace);
      oldOwner.nOwned--;
      oldOwner.nOwnedPerType[8]--;
      oldOwner.adjustPropertyRents(8);

    }

  }*/

  public boolean isTypeComplete (int nType) {

    switch (nType) {

      case 0:
      case 5:
      case 6:
      case 8: return this.nOwnedPerType[nType] == 2;

      case 1:
      case 2:
      case 3:
      case 4:
      case 7: return this.nOwnedPerType[nType] == 3;

    }

    return true;

  }

  public int getPlayerNum () {
    return nPlayerNum;
  }

  public int getLocationIndex () {
    return nLocationIndex;
  }

  public String getName () {
    return this.strName;
  }

  public double getCash () {
    return dCash;
  }

  public int getOwned () {
    return nOwned;
  }

  public int getOwnedPerType (int nType) {
    return nOwnedPerType[nType];
  }

  public ArrayList <OwnableSpace> getOwnedSpaces () {
    return ownedSpaces;
  }

  public OwnableSpace getSpace (int nID) {

    for (OwnableSpace s : ownedSpaces) {

      if (nID == s.getID())
        return s;

//      if (s instanceof Property && nID == s.getID())
//        return s;
//
//      else if (s instanceof Railroad && nID == s.getID())
//        return s;
//
//      else if (s instanceof Utility && nID == s.getID())
//        return s;

    }

    return null;

  }

  public boolean isInJail () {
    return bInJail;
  }

  public ArrayList <CardGroup1> getJailChanceCards () {
    return this.jailChanceCards;
  }

  public void setPlayerNum (int nPlayerNum) {
    this.nPlayerNum = nPlayerNum;
  }

  public void setLocationIndex (int nLocationIndex) {
    this.nLocationIndex = nLocationIndex;
  }

  public void setName (String strName) {
    this.strName = strName;
  }

  public void addOrDeductCash (double dToAdd) {
    this.dCash += dToAdd;
  }

  public void addSpaceOwned (OwnableSpace toAdd) {

    ownedSpaces.add(toAdd);
    nOwned++;

    if (toAdd instanceof Property) {

      nOwnedPerType[((Property) toAdd).getColorIndex()]++;
      adjustPropertyRents(((Property) toAdd).getColorIndex());

    } else if (toAdd instanceof Railroad) {

      nOwnedPerType[7]++;
      adjustPropertyRents(7);

    } else if (toAdd instanceof Utility) {

      nOwnedPerType[8]++;
      adjustPropertyRents(8);

    }

  }

  public void removeSpaceOwned (OwnableSpace toRemove) {

    this.ownedSpaces.remove(toRemove);
    this.nOwned--;

    if (toRemove instanceof Property) {

      nOwnedPerType[((Property) toRemove).getColorIndex()]--;
      adjustPropertyRents(((Property) toRemove).getColorIndex());

    } else if (toRemove instanceof Railroad) {

      nOwnedPerType[7]--;
      adjustPropertyRents(7);

    } else if (toRemove instanceof Utility) {

      nOwnedPerType[8]--;
      adjustPropertyRents(8);

    }

  }

  public void setInJail (boolean bInJail) {
    this.bInJail = bInJail;
  }

  public void addJailChance (CardGroup1 toAdd) {
    this.jailChanceCards.add(toAdd);
  }

  public void removeJailChance () {
    this.jailChanceCards.remove(0);
  }

  @Override
  public String toString () {

    String toReturn = "PLAYER NUMBER: " + this.nPlayerNum +
                      "\nLOCATION: " + this.nLocationIndex +
                      "\nNAME: " + this.strName +
                      "\nCASH ON HAND: " + this.dCash +
                      "\nOWNED PROPERTIES: " + this.nOwned +
                      "\nOWNED PER TYPE: [ ";

    for (int i = 0; i < 9; i++)
      toReturn = toReturn + nOwnedPerType[i] + " ";

    toReturn = toReturn + "]\n" + "IN JAIL: " + this.bInJail;

    return toReturn;
  }

}
