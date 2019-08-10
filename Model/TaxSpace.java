package Model;

/**
 * Tax type extension of the <b><i>Space</i></b> class. One extra method is defined defined. Also provides for easier
 * <b><i>Space</i></b> instance differentiation. The associated identification number for instances of this class is
 * -2 when <b>INCOME_TAX</b> is true and -3 otherwise.
 */

public class TaxSpace extends Space {

  private final boolean INCOME_TAX;

  private double dToPay;

  /**
   * Constructor for a <b><i>ChanceSpace</i></b> object.
   *
   * @param nLocationIndex Integer holding the index of a <b><i>Board</i></b> instance's <b>boardSpaces</b> attribute
   *                       in which an instance of this class will be placed.
   * @param bIncome Boolean variable that differentiates the two instances of this class that will be created upon
   *                application run.
   */

  public TaxSpace(int nLocationIndex, boolean bIncome) {

    super(nLocationIndex);
    this.INCOME_TAX = bIncome;

  }

  /**
   * Method that determines the amount of t
   *
   * @param currPlayer
   * @return
   */

  public double payTax (Player currPlayer) {

    if (this.INCOME_TAX)
      dToPay = 200 > currPlayer.getCash() * 0.10 ? 200 : currPlayer.getCash() * 0.10;

    else
      dToPay = 75;

    if (currPlayer.isPaymentPossible(dToPay))
      return dToPay;

    else
      return -1;

  }

  public boolean isIncome() {
    return INCOME_TAX;
  }

}
