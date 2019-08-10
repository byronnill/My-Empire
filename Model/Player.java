package Model;

import java.util.*;

/**
 * Class handling the transactions and movement of its instances in a master <b><i>Game</i></b> object. Objects of this
 * type move along a <b><i>Board</i></b> instance during application run and can buy, pay rent on, and trade objects of
 * type <b><i>OwnableSpace</i></b> with each other.
 */

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
  private double dWorth;

  /**
   * Constructor for a <b><i>Player</i></b> object with <i>nPlayerNum</i> as parameter. For easier accessing of elements
   * in a <b><i>Game</i></b>'s <b>playerList</b> attribute, the <i>nPlayerNum</i> parameter (in this constructor) and
   * attribute (in this class) will be in the range of 0-3, inclusive, depending on the number of players in the
   * <b><i>Game</i></b>
   *
   * <p>
   * At instantiation, all attribute of this class are already given values except for <b>dWorth</b>, which will be
   * calculated only at <b><i>Game</i></b> termination, and the ArrayList attributes that are only instantiated as such.
   *
   * @param nPlayerNum Integer holding the initial position of a <b><i>Player</i></b> in a <b><i>Game</i></b>'s
   *                   <b>playerList</b> attribute.
   */

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

  /**
   * <b><i>Player</i></b> moving method that changes the <b>nLocationIndex</b> attribute of that object. Further actions
   * will also be done dependent on the <b>boardSpaces</b> index that is traversed and landed upon method execution.
   *
   * @param board <b><i>Board</i></b> object on which the movement will be done.
   * @param numSteps Integer that holds the number of steps that the <b><i>Player</i></b> should "take".
   * @param willCollectOnStart Boolean variable that determines whether or not the active <b><i>Player</i></b>'s
   *                           <b>dCash</b> attribute will be increased by 200 upon passing index 0 of
   *                           <b>boardSpaces</b>.
   * @param bank <b><i>Bank</i></b> object from which 200 will be deducted if the <b><i>Player</i></b> passes index 0 of
   *             <b>boardSpaces</b> and <i>willCollectOnStart</i> is set to true.
   * @return Boolean variable that is set to false if, after the movement, the <b>dValue</b> attribute of the <i>bank</i>
   *         parameter is reduced to at most 0. True, otherwise.
   */

  public boolean movePlayer (Board board, int numSteps, boolean willCollectOnStart, Bank bank) {

    if (this.nLocationIndex + numSteps >= 32 && willCollectOnStart) {
      this.dCash += 200;
      bank.addOrDeduct(-200);
    }

    this.nLocationIndex = (this.nLocationIndex + numSteps) % 32;

    if (board.getBoardSpaces().get(this.nLocationIndex) instanceof Property)
      ((Property) board.getBoardSpaces().get(this.nLocationIndex)).addFootTraffic();

    return !(bank.getValue() <= 0);//bank is not bankrupt

  }

  /**
   * Checks for difference in <b>dCash</b> and a certain value. Returns whether or not an instance of
   * <b><i>Player</i></b> will not be bankrupt after payment.
   *
   * @param dToPay Double-precision floating point number that holds the amount that is to be paid.
   * @return Boolean variable the is set to false if the instance is able to pay without its <b>dCash</b> being
   *         reduced to at most 0. True, otherwise.
   */

  public boolean isPaymentPossible (double dToPay) {
    return this.dCash > dToPay;
  }

  /**
   * <b><i>Player</i></b> payment method. Reduces the <b>dCash</b> attribute of one <b><i>Player</i></b> instance while increasing
   * another's.
   *
   * @param dToPay Double-precision floating point number that holds the amount that is to be transferred.
   * @param playerPayee <b><i>Player</i></b> object whose <b>dCash</b> will be increased.
   */

  public void payPlayer (double dToPay, Player playerPayee) {

    this.dCash -= dToPay;
    playerPayee.dCash += dToPay;

  }

  /**
   * <b><i>Bank</i></b> payment method. Reduces the <b>dCash</b> attribute of one <b><i>Player</i></b> instance while increasing
   * the <b>dValue</b> of a <b><i>Bank</i></b> object.
   *
   * @param dToPay Double-precision floating point number that holds the amount that is to be transferred.
   * @param bank <b><i>Bank</i></b> object whose <b>dValue</b> will be increased.
   */

  public void payBank (double dToPay, Bank bank) {

    this.dCash -= dToPay;
    bank.addOrDeduct(dToPay);

  }

  /**
   * Method that increases the <b>dRent</b> attribute of <b><i>Railroad</i></b> and <b><i>Utility</i></b> instances.
   * The increase is dependent on the number of objects of those types that a <b><i>Player</i></b> owns.
   *
   * @param nType Integer holding the type of <b><i>OwnableSpace</i></b> that is to be checked.
   *              7 for <b><i>Railroad</i></b> and 8 for <b><i>Utility</i></b>.
   */

  private void adjustPropertyRents (int nType) {

    if (this.nOwnedPerType[nType] == 1) {

      for (OwnableSpace s : this.ownedSpaces) {

        if (s instanceof Railroad && nType == 7)
          s.setRent(25 * s.getMultplier());

        else if (s instanceof Utility && nType == 8)
          s.setRent(4 * s.getMultplier());

      }

    } else if (this.nOwnedPerType[nType] == 2) {

      for (OwnableSpace s : this.ownedSpaces) {

        if (s instanceof Railroad && nType == 7)
          s.setRent(50 * s.getMultplier());

        else if (s instanceof Utility && nType == 8)
          s.setRent(10 * s.getMultplier());

      }

    } else if (this.nOwnedPerType[nType] == 3) {

      for (OwnableSpace s : this.ownedSpaces) {

        if (s instanceof Railroad && nType == 7)
          s.setRent(150 * s.getMultplier());

      }

    }

  }

  /**
   * Adds <i>currSpace</i> to the <b><i>Player</i></b>'s <b>ownedSpaces</b> attribute. Simulates a purchasing action
   * wherein a <b><i>Player</i></b> pays the <i>bank</i> the amount corresponding to the <b>PRICE</b> attribute of
   * the <i>currSpace</i> parameter.
   *
   * @param currSpace <b><i>OwnableSpace</i></b> object that will be bought by the active <b><i>Player</i></b> instance.
   * @param bank <b><i>Bank</i></b> object that will collect the payment for purchase.
   */

  public void buyProperty (OwnableSpace currSpace, Bank bank) {

    currSpace.setOwner(this);

    this.payBank(currSpace.getPrice(), bank);
    this.addSpaceOwned(currSpace);

  }

  /**
   * Increases the <b>dRent</b> and <b>nDevelopment</b> attributes of <i>toDevelop</i>. Simulates a development action
   * wherein a <b><i>Player</i></b> pays the <i>bank</i> the amount corresponding to the <b>HOUSE_PRICE</b> attribute of
   * the <i>currSpace</i> parameter.
   *
   * @param toDevelop <b><i>Property</i></b> object that will developed by the active <b><i>Player</i></b> instance.
   * @param bank <b><i>Bank</i></b> object that will collect the payment for development.
   */

  public void developProperty (Property toDevelop, Bank bank) {

    toDevelop.addToDevelopment();
    toDevelop.setRent(Reference.RENT[toDevelop.getColorIndex()][toDevelop.getPropertyIndex()][toDevelop.getDevelopment()] * toDevelop.getMultplier());

    if (toDevelop.getDevelopment() == 5) {

      this.payBank(toDevelop.getHotelPrice(), bank);
      toDevelop.addToWorth(toDevelop.getHotelPrice());

    } else {

      this.payBank(toDevelop.getHousePrice(), bank);
      toDevelop.addToWorth(toDevelop.getHousePrice());

    }

  }

  /**
   * Exchanges the <b>owner</b> attributes of two <b><i>OwnableSpace</i></b> instances.
   *
   * @param playerSpace <b><i>OwnableSpace</i></b> instance that will be given to <i>otherPlayer</i>.
   * @param otherSpace <b><i>OwnableSpace</i></b> instance that will be given to the active <b><i>Player</i></b>.
   * @param otherPlayer <b><i>Player</i></b> instance that will partake in the trade.
   */


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

  /**
   * Checks for completion of <b><i>OwnableSpace</i></b> sets. This method is used when checking for the possibility
   * of <b><i>Game</i></b> termination.
   *
   * @param nType Integer holding the value of the set to be checked.
   * @return Boolean variable that is false if the set to be checked is incomplete. True, otherwise.
   */

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

  /**
   * Getter method for the <b>nPlayerNum</b> attribute of this class.
   *
   * @return nPlayerNum Integer holding the position of an instance in a <b><i>Game</i></b>'s <b>playerList</b>.
   */

  public int getPlayerNum () {
    return nPlayerNum;
  }

  /**
   * Getter method for the <b>nLocationIndex</b> attribute of this class.
   *
   * @return nLocationIndex Integer holding the location of an instance in a <b><i>Game</i></b>'s <b>gameBoard</b>.
   */

  public int getLocationIndex () {
    return nLocationIndex;
  }

  /**
   * Getter method for the <b>strName</b> attribute of this class.
   *
   * @return strName String representation of an instance's preferred name.
   */

  public String getName () {
    return this.strName;
  }

  /**
   * Getter method for the <b>dCash</b> attribute of this class.
   *
   * @return dCash Double-precision floating point number holding the amount of cash an instance can use.
   */

  public double getCash () {
    return dCash;
  }

  /**
   * Getter method for the <b>nOwned</b> attribute of this class.
   *
   * @return dWorth Integer holding the number of <b><i>OwnableSpace</i></b> objects that an instance owns.
   */

  public int getOwned () {
    return nOwned;
  }

  /**
   * Getter method for one integer in the <b>nOwnedPerType</b> attribute of this class.
   *
   * @param nType Integer holding the index of <b>nOwnedPerType</b> from which the returnable value will be taken.
   * @return nOwnedPerType[nType] Integer holding the number of <b><i>OwnableSpace</i></b> objects of a certain type
   *         that an instance owns.
   */

  public int getOwnedPerType (int nType) {
    return nOwnedPerType[nType];
  }

  /**
   * Getter method for the <b>ownedSpaces</b> attribute of this class.
   *
   * @return ownedSpace ArrayList of <b><i>OwnableSpace</i></b> objects holding the number of <b><i>OwnableSpace</i></b>
   *         objects of a certain type that an instance owns.
   */

  public ArrayList <OwnableSpace> getOwnedSpaces () {
    return ownedSpaces;
  }

  /**
   * Getter method for the <b>bInJail</b> attribute of this class.
   *
   * @return Boolean holding the jail status of an instance.
   */

  public boolean isInJail () {
    return bInJail;
  }

  /**
   * Getter method for the <b>jailChanceCards</b> attribute of this class.
   *
   * @return ArrayList of <b><i>CardGroup1</i></b> objects.
   */

  public ArrayList <CardGroup1> getJailChanceCards () {
    return this.jailChanceCards;
  }

  /**
   * Getter method for the <b>dWorth</b> attribute of this class.
   *
   * @return Double-precision floating point number holding the total net worth of an instance.
   */

  public double getWorth () {
    return this.dWorth;
  }

  /**
   * Setter method for the <b>nPlayerNum</b> attribute of this class. Mainly used in <b><i>Player</i></b> reordering.
   *
   * @param nPlayerNum Integer holding the new <b>nPlayerNum</b> attribute.
   */

  public void setPlayerNum (int nPlayerNum) {
    this.nPlayerNum = nPlayerNum;
  }

  /**
   * Setter method for the <b>strName</b> attribute of this class.
   *
   * @param strName String holding the new <b>strName</b> attribute.
   */

  public void setName (String strName) {
    this.strName = strName;
  }

  /**
   * Equivalent setter method for the <b>dCash</b> attribute of this class. Instead of changing the entire value,
   * a certain amount is either added or deducted to that attribute.
   *
   * @param dToAdd Double-precision floating point number that is to be added to the <b>dCash</b> attribute of an
   *               instance. The caller of this function sets this parameter negative for value deduction.
   */

  public void addOrDeductCash (double dToAdd) {
    this.dCash += dToAdd;
  }

  /**
   * Equivalent setter method for the <b>ownedSpaces</b> attribute of this class. Instead of changing the entire
   * collection, a new instance of <b><i>OwnableSpace</i></b> is just added.
   *
   * @param toAdd <b><i>OwnableSpace</i></b> object that is to be added.
   */

  public void addSpaceOwned (OwnableSpace toAdd) {

    ownedSpaces.add(toAdd);
    nOwned++;

    if (toAdd instanceof Property) {

      nOwnedPerType[((Property) toAdd).getColorIndex()]++;

    } else if (toAdd instanceof Railroad) {

      nOwnedPerType[7]++;
      adjustPropertyRents(7);

    } else if (toAdd instanceof Utility) {

      nOwnedPerType[8]++;
      adjustPropertyRents(8);

    }

  }

  /**
   * Equivalent setter method for the <b>ownedSpaces</b> attribute of this class. Instead of changing the entire
   * collection, an instance of <b><i>OwnableSpace</i></b> is just removed.
   *
   * @param toRemove <b><i>OwnableSpace</i></b> object that is to be removed.
   */

  public void removeSpaceOwned (OwnableSpace toRemove) {

    this.ownedSpaces.remove(toRemove);
    this.nOwned--;

    if (toRemove instanceof Property) {

      nOwnedPerType[((Property) toRemove).getColorIndex()]--;

    } else if (toRemove instanceof Railroad) {

      nOwnedPerType[7]--;
      adjustPropertyRents(7);

    } else if (toRemove instanceof Utility) {

      nOwnedPerType[8]--;
      adjustPropertyRents(8);

    }

  }

  /**
   * Setter method for the <b>bInJail</b> attribute of this class.
   *
   * @param bInJail Boolean variable holding the new <b>bInJail</b> attribute.
   */

  public void setInJail (boolean bInJail) {
    this.bInJail = bInJail;
  }

  /**
   * Equivalent setter method for the <b>jailChanceCards</b> attribute of this class. Instead of changing the entire
   * collection, a new instance of <b><i>CardGroup1</i></b> is just added.
   *
   * @param toAdd <b><i>CardGroup1</i></b> object that is to be added.
   */

  public void addJailChance (CardGroup1 toAdd) {
    this.jailChanceCards.add(toAdd);
  }

  /**
   * Equivalent setter method for the <b>jailChanceCards</b> attribute of this class. Instead of changing the entire
   * collection, the top instance of <b><i>CardGroup1</i></b> in the collection is just removed.
   *
   */

  public void removeJailChance () {
    this.jailChanceCards.remove(0);
  }

  /**
   * Setter method for the <b>dWorth</b> attribute of this class.
   *
   * @param dWorth Double-precision floating point value holding the new <b>dWorth</b> attribute.
   */

  public void setWorth (double dWorth) {
    this.dWorth = dWorth;
  }

  /**
   * Overriden <b>toString()</b> method from the Object class. Used only in program testing and confirmation.
   *
   * @return String representation of the attributes of this class.
   * @see Object#toString()
   */

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
