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
   * Method that determines the amount of tax to be paid by a <b><i>Player</i></b> instance.
   *
   * @param currPlayer <b><i>Player</i></b> object that will pay the amount to be returned. The <b>dCash</b> attribute
   *                   of this parameter is also the basis for the returnable value if <b>bIncome</b> is true.
   * @return Double-precision floating point value that is negative if payment is not possible a
   */

  public double payTax (Player currPlayer) {

    if (this.INCOME_TAX)
      dToPay = 200 > currPlayer.getCash() * 0.10 ? 200 : currPlayer.getCash() * 0.10;

    else
      dToPay = 75;

    if (currPlayer.isPaymentPossible(dToPay))
      return dToPay;

    else
      return dToPay * -1;

  }

  /**
   * Getter method for the <b>INCOME_TAX</b> attribute of this class.
   *
   * @return Boolean variable that is used for the differentiation of the only two instances of this class.
   */

  public boolean isIncome() {
    return INCOME_TAX;
  }

}
