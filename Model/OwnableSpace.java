package Model;

/**
 * Ownable type extension of the <b>Space</b> class. Methods are defined for <b><i>Player</i></b> observation and
 * manipulation while also providing for easier <b><i>Space</i></b> instance differentiation.
 *
 * <p>
 * The class is further divided into the classes <b><i>Property</i></b>, <b><i>Railroad</i></b>, and
 * <b><i>Utility</i></b>.
 *
 * <p>
 * The associated identification number for each instance of <b><i>OwnableSpace</i></b> are detailed in the following:
 *
 * <ul>
 *   <li>{@link Property#Property(int, int, int, int)}</li>
 *   <li>{@link Railroad#Railroad(int, int)}</li>
 *   <li>{@link Utility#Utility(int, int)}</li>
 * </ul>
 *
 * @see #OwnableSpace(int, int, String, double, double)
 * @see Property
 * @see Railroad
 * @see Utility
 */

public abstract class OwnableSpace extends Space {

  /**
   * Integer that is used for identification. The value set for this attribute is highly dependent on the subclass
   * that calls the constructor of this superclass.
   */

  protected final int OWNABLE_ID;

  /**
   * String that represents the name of an instance of this class.
   */

  protected final String NAME;

  /**
   * Double-precision floating point number that holds the price of an instance of this class. It is used whenever
   * a <b><i>Player</i></b> buys an object of type <b><i>OwnableSpace</i></b>
   */

  protected final double PRICE;

  /**
   * <b><i>Player</i></b> object that holds the owner of an instance of this class.
   */

  protected Player owner;

  /**
   * Double-precision floating point number that holds the amount that should be paid whenever a <b><i>Player</i></b>
   * that is not the <b>owner</b> lands on an instance of this class.
   */

  protected double dRent;

  /**
   * Double-precision floating point number that holds the total worth of an instance of this class. For
   * <b><i>Property</i></b> instances, this attribute is subject to change throught development.
   */

  protected double dWorth;

  /**
   * Double-precision floating point number that holds the multiplier for an instance's <b>dRent</b> value.
   */

  protected double dMultiplier;

  /**
   * Constructor for an <b><i>OwnableSpace</i></b> object that is called by its subclasses only.
   *
   * @param nLocationIndex Integer that holds the index where the <b><i>OwnableSpace</i></b> object will be placed on a
   *                       <b><i>Board</i></b>'s <b>boardSpaces</b> attribute.
   * @param nID Integer that holds the identification number of an <b>OwnableSpace</b>.
   * @param strName String that holds the name of an <b>OwnableSpace</b>.
   * @param dPrice Double-precision floating point number that holds the price of an <b><i>OwnableSpace</i></b>.
   * @param dRent Double-precision floating point number that holds the rent of an <b><i>OwnableSpace</i></b>.
   */

  public OwnableSpace (int nLocationIndex, int nID, String strName, double dPrice, double dRent) {

    super(nLocationIndex);

    this.OWNABLE_ID = nID;
    this.owner = null;
    this.NAME = strName;
    this.PRICE = dPrice;
    this.dRent = dRent;
    this.dMultiplier = 1;

    this.dWorth = this.PRICE;

  }

  /**
   * Method that checks if an <b><i>OwnableSpace</i></b> instance can be bought by a <b><i>Player</i></b> if they
   * choose to do so.
   *
   * @param currPlayer <b><i>Player</i></b> instance that is active in the <b><i>Game</i></b> turn.
   * @return Boolean variable that determines whether the <i>currPlayer</i> parameter will have enough cash
   *         after the <b>buyProperty()</b> call.
   */

  public boolean canBeBought (Player currPlayer) {

    return currPlayer.isPaymentPossible(this.PRICE);

  }

  /**
   * Getter method for the <b>OWNABLE_ID</b> attribute of this class.
   *
   * @return Integer variable holding the identification number of an <b><i>OwnableSpace</i></b>.
   */

  public int getID () {
    return OWNABLE_ID;
  }

  /**
   * Getter method for the <b>owner</b> attribute of this class.
   *
   * @return <b><i>Player</i></b> object that holds the owner of an <b><i>OwnableSpace</i></b>.
   */

  public Player getOwner () {
    return owner;
  }

  /**
   * Getter method for the <b>NAME</b> attribute of this class.
   *
   * @return String variable holding the name of an <b><i>OwnableSpace</i></b>
   */

  public String getName () {
    return NAME;
  }

  /**
   * Getter method for the <b>PRICE</b> attribute of this class.
   *
   * @return Double-precisiong floating point number holding the price of an <b><i>OwnableSpace</i></b>
   */

  public double getPrice () {
    return PRICE;
  }

  /**
   * Getter method for the <b>dRent</b> attribute of this class.
   *
   * @return Double-precision floating point number holding the rent of an <b><i>OwnableSpace</i></b>
   */

  public double getRent () {
    return dRent;
  }

  public double getMultplier () {
    return dMultiplier;
  }

  /**
   * Getter method for the <b>dWorth</b> attribute of this class.
   *
   * @return Double-precision floating point number holding the worth of an <b><i>OwnableSpace</i></b>
   */

  public double getWorth () {
    return dWorth;
  }

  /**
   * Setter method for the <b>owner</b> attribute of this class.
   *
   * @param owner <b><i>Player</i></b> object holding the new owner of an <b><i>OwnableSpace</i></b>
   */

  public void setOwner (Player owner) {
    this.owner = owner;
  }

  /**
   * Setter method for the <b>dRent</b> attribute of this class.
   *
   * @param dRent Double-precision floating point number holding the new rent value of an <b><i>OwnableSpace</i></b>
   */

  public void setRent (double dRent) {
    this.dRent = dRent;
  }

  public void setMultiplier (double dMultiplier) {
    this.dMultiplier = dMultiplier;
  }

  /**
   * Equivalent setter method for the <b>dWorth</b> attribute of this class. Instead of changing the entire value
   * of the attribute, a certain amount is added to the current value.
   *
   * @param toAdd Double-precision floating point number holding the amount to be added to the <b>dWorth</b> attribute
   *              of an <b><i>OwnableSpace</i></b>
   */

  public void addToWorth (double toAdd) {
    this.dWorth += toAdd;
  }

  /**
   * Overriden <b>toString()</b> method from the Object class. Used only in program testing and confirmation.
   *
   * @return String representation of the attributes of this class.
   * @see Object#toString()
   */

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

  /**
   * Modified <b>toString</b> method that returns only the string representation of an instance's <b>NAME</b>,
   * <b>dPrice</b>, and <b>dRent</b>. Used in presenting basic data to the program user.
   *
   * @return String representation of the basic attributes of this class.
   */

  public String toStringShort () {

    return  "Name: " + this.NAME +
            "\nPrice: " + this.PRICE +
            "\nRent: " + this.dRent;

  }

}
