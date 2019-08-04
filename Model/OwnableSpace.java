package Model;

public abstract class OwnableSpace extends Space {

  protected final int OWNABLE_ID;
  protected final String NAME;
  protected final double PRICE;

  protected Player owner;
  protected double dRent;

  protected double dWorth;

  public OwnableSpace (int nLocationIndex, int nID, String strName, double dPrice, double dRent) {

    super(nLocationIndex);

    this.OWNABLE_ID = nID;
    this.owner = null;
    this.NAME = strName;
    this.PRICE = dPrice;
    this.dRent = dRent;

    this.dWorth = this.PRICE;

  }

  public boolean canBeBought (Player currPlayer) {

    return this.owner == null && currPlayer.isPaymentPossible(this.PRICE);

  }

  public int getID () {
    return OWNABLE_ID;
  }

  public Player getOwner () {
    return owner;
  }

  public String getName () {
    return NAME;
  }

  public double getPrice () {
    return PRICE;
  }

  public double getRent () {
    return dRent;
  }

  public double getWorth () {
    return dWorth;
  }

  public void setOwner (Player owner) {
    this.owner = owner;
  }

  public void setRent (double dRent) {
    this.dRent = dRent;
  }

  public void addToWorth (double toAdd) {
    this.dWorth += toAdd;
  }

  @Override
  public String toString () {

    String toReturn = "ID: " + this.OWNABLE_ID +
                      "\nNAME: " + this.NAME;

    try {

      toReturn += "\nOWNER: " + this.owner.getName();

    } catch (NullPointerException e) {

      toReturn += "\nOWNER: NULL";

    }

    toReturn += "\nPRICE: " + this.PRICE +
                "\nRENT: " + this.dRent;

    return toReturn;

  }

}
