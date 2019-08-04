package Model;

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

  public boolean canBeDeveloped (Player currPlayer) { //edit pa to accommodate gui

    if (this.nDevelopment == 5) {

      return false;

    } else if (this.nDevelopment == 4 && currPlayer.isPaymentPossible(this.HOTEL_PRICE)) {

      if (this.dTotalCollected >= this.HOTEL_PRICE)
        return true;

      else if (this.nFootTraffic >= this.REQUIRED_FOOT_TRAFFIC)
        return true;

      else
        return false;

    } else if (this.nDevelopment < 4 && currPlayer.isPaymentPossible(this.HOUSE_PRICE)) {

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

  public int getColorIndex () {
    return COLOR_INDEX;
  }

  public int getPropertyIndex () {
    return PROPERTY_INDEX;
  }

  public double getHousePrice () {
    return HOUSE_PRICE;
  }

  public double getHotelPrice () {
    return HOTEL_PRICE;
  }

  public int getDevelopment () {
    return nDevelopment;
  }

  public int getFootTraffic () {
    return nFootTraffic;
  }

  public int getRequiredFootTraffic () {
    return REQUIRED_FOOT_TRAFFIC;
  }

  public double getTotalCollected () {
    return dTotalCollected;
  }

  public boolean isDoubleRent () {
    return bDoubleRent;
  }

  public CardGroup5 getDoubleRentHolder () {
    return doubleRentHolder;
  }

  public void addToDevelopment () {
    this.nDevelopment++;
  }

  public void addFootTraffic () {
    this.nFootTraffic++;
  }

  public void addToCollected () {
    this.dTotalCollected += this.dRent;
  }

  public void setDoubleRent (boolean bDoubleRent) {
    this.bDoubleRent = bDoubleRent;
  }

  public void setDoubleRentHolder (CardGroup5 doubleRentHolder) {
    this.doubleRentHolder = doubleRentHolder;
  }

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

}
