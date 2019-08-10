package Model;

/**
 * Property type of class <b><i>OwnableSpace</i></b>.
 */

public class Property extends OwnableSpace{

  private final int COLOR_INDEX; // >= 0
  private final int PROPERTY_INDEX; // >= 0
  private final double HOUSE_PRICE;
  private final double HOTEL_PRICE;
  private final int REQUIRED_FOOT_TRAFFIC;

  private int nDevelopment;
  private int nFootTraffic;
  private double dTotalCollected;
  private boolean bDoubleRent;
  private CardGroup5 doubleRentHolder;

  /**
   * Constructor for a <b><i>Property</i></b> object. The <b>OWNABLE_ID</b> of this class's instances are based on their
   * <b>COLOR_INDEX</b> and <b>PROPERTY_INDEX</b> attributes wherein the ID is equal to the color index * 10 + the
   * property index.
   *
   * @param nLocationIndex Integer holding the location of an instance on a <b><i>Board</i></b>.
   * @param nColorIndex Integer holding the color type of an instance based on <b><i>Reference</i></b>.
   * @param nPropertyIndex Integer holding the property identification as is coordinated on <b><i>Reference</i></b>.
   * @param nPlayers Integer holding the number of players in the <b><i>Game</i></b>. Used in the calculation of
   *                 the <b>REQUIRED_FOOT_TRAFFIC</b> attribute.
   */

  public Property (int nLocationIndex, int nColorIndex, int nPropertyIndex, int nPlayers) {

    super(nLocationIndex, nColorIndex * 10 + nPropertyIndex, Reference.PROPERTIES[nColorIndex][nPropertyIndex], Reference.PRICE[nColorIndex][nPropertyIndex], Reference.RENT[nColorIndex][nPropertyIndex][0]);

    this.COLOR_INDEX = nColorIndex;
    this.PROPERTY_INDEX = nPropertyIndex;

    this.HOUSE_PRICE = Reference.HOUSEPRICE[nColorIndex][nPropertyIndex];
    this.HOTEL_PRICE = this.HOUSE_PRICE * 4;
    this.nDevelopment = 0;

    this.nFootTraffic = 0;
    this.REQUIRED_FOOT_TRAFFIC = (int) Math.ceil(Reference.MULTIPLIER[nColorIndex][nPropertyIndex] * nPlayers);

    this.dTotalCollected = 0;

    this.bDoubleRent = false;
    this.doubleRentHolder = null;

  }

  /**
   * Method that allows for development possibility checking. The checking is based on the <b>nDevelopment</b>,
   * <b>nFootTraffic</b>, <b>REQUIRED_FOOT_TRAFFIC</b>, <b>dTotalCollected</b>, and pricing attributes of an instance.
   *
   * @return Boolean variable that is true when the instance can be developed. False, otherwise.
   */

  public boolean canBeDeveloped () {

    if (this.nDevelopment == 5) {

      return false;

    } else if (this.nDevelopment == 4) {

      if (this.dTotalCollected >= this.HOTEL_PRICE)
        return true;

      else if (this.nFootTraffic >= this.REQUIRED_FOOT_TRAFFIC)
        return true;

      else
        return false;

    } else if (this.nDevelopment < 4) {

      if (this.dTotalCollected >= this.HOUSE_PRICE)
        return true;

      else if (this.nFootTraffic >= this.REQUIRED_FOOT_TRAFFIC)
        return true;

      else
        return false;

    } else {

      return false;

    }

  }

  /**
   * Modified getter method for the <b>dRent</b> attribute of this class. The method takes into account the number of
   * <b><i>Property</i></b> instances that the <b>owner</b> has and increases them accordingly. This method
   * also removes the double rent chance cards, if any.
   *
   * @param deck <b><i>Deck</i></b> object to which the double rent chance card, if any, will be discarded.
   * @return Double-precision floating point value that holds the amount to be paid.
   */

  public double getPropertyRent (Deck deck) {

    double dToPay = dRent;

    if (owner.getOwnedPerType(this.COLOR_INDEX) == 2)
      dToPay += 10;

    else if (owner.getOwnedPerType(this.COLOR_INDEX) == 3)
      dToPay += 20;

    if (this.isDoubleRent()) {

      this.bDoubleRent = false;
      this.dRent /= 2;

      deck.discardCard(this.doubleRentHolder);
      this.doubleRentHolder = null;

      this.dMultiplier /= 2;

    }

    return dToPay;

  }

  /**
   * Getter method for the <b>COLOR_INDEX</b> attribute of this class.
   *
   * @return Integer holding the color type of an instance.
   */

  public int getColorIndex () {
    return COLOR_INDEX;
  }

  /**
   * Getter method for the <b>PROPERTY_INDEX</b> attribute of this class.
   *
   * @return Integer holding the property index of an instance.
   */

  public int getPropertyIndex () {
    return PROPERTY_INDEX;
  }

  /**
   * Getter method for the <b>HOUSE_PRICE</b> attribute of this class.
   *
   * @return Integer holding the price for house development of an instance.
   */

  public double getHousePrice () {
    return HOUSE_PRICE;
  }

  /**
   * Getter method for the <b>HOTEL_PRICE</b> attribute of this class.
   *
   * @return Integer holding the price for hotel development of an instance.
   */

  public double getHotelPrice () {
    return HOTEL_PRICE;
  }

  /**
   * Getter method for the <b>nDevelopment</b> attribute of this class.
   *
   * @return Integer holding the current level of development of an instance.
   */

  public int getDevelopment () {
    return nDevelopment;
  }

  /**
   * Getter method for the <b>bDoubleRent</b> attribute of this class.
   *
   * @return Boolean variable holding the double rent status of an instance.
   */

  public boolean isDoubleRent () {
    return bDoubleRent;
  }

  /**
   * Equivalent setter method for the <b>nDevelopment</b> attribute of this class. Increments the attribute by one
   * for every call.
   */

  public void addToDevelopment () {
    this.nDevelopment++;
  }

  /**
   * Equivalent setter method for the <b>nFootTraffic</b> attribute of this class. Increments the attribute by one
   * for every call.
   */

  public void addFootTraffic () {
    this.nFootTraffic++;
  }

  /**
   * Equivalent setter method for the <b>dTotalCollected</b> attribute of this class. Instead of changing the entire
   * value, a certain amount is added to that attribute.
   *
   * @param toAdd Double-precision floating point number holding the amount to be added.
   */

  public void addToCollected (double toAdd) {
    this.dTotalCollected += toAdd;
  }

  /**
   * Setter method for the <b>bDoubleRent</b> attirbute of this class.
   *
   * @param bDoubleRent Boolean variable holding the new <b>bDoubleRent</b> attribute.
   */

  public void setDoubleRent (boolean bDoubleRent) {
    this.bDoubleRent = bDoubleRent;
  }

  /**
   * Setter method for the <b>doubleRentHolder</b> attribute of this class.
   *
   * @param doubleRentHolder <b><i>CardGroup5</i></b> object holding the new <b>doubleRentHolder</b> attribute. Calls
   *                         of this function may use null as the parameter.
   */

  public void setDoubleRentHolder (CardGroup5 doubleRentHolder) {
    this.doubleRentHolder = doubleRentHolder;
  }

  /**
   * Overriden <b>toString()</b> method from the Object class. Used only in program testing and confirmation.
   *
   * @return String representation of the attributes of this class.
   * @see Object#toString()
   */

  @Override
  public String toString () {

    return super.toString() +
            "\nHOUSE PRICE: " + this.HOUSE_PRICE +
            "\nHOTEL PRICE: " + this.HOTEL_PRICE +
            "\nDEVELOPMENT: " + this.nDevelopment +
            "\nFOOT TRAFFIC: " + this.nFootTraffic + " / " + this.REQUIRED_FOOT_TRAFFIC +
            "\nCOLLECTED: " + this.dTotalCollected +
            "\nDOUBLE RENT: " + this.bDoubleRent;

  }

  /**
   * Modified <b>toString</b> method that returns only the string representation of an instance's basic attributes.
   * Used in presenting basic data to the program user.
   *
   * @return String representation of the basic attributes of this class.
   */

  @Override
  public String toStringShort () {

    return  super.toStringShort() +
            "\nDevelopment: " + this.nDevelopment + " / 5" +
            "\nFoot Traffic: " + this.nFootTraffic + " / " + this.REQUIRED_FOOT_TRAFFIC +
            "\nCash Collected: " + this.dTotalCollected;
  }

}
