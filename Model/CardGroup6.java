package Model;

/**
 * Class handling the construction of its instances and the assignment of values for the attribute <b>dToPay</b>.
 */

public class CardGroup6 extends Card {

  private final int SPEC_TYPE;

  private double dToPay;

  /**
   * Constructor for a <b><i>CardGroup6</i></b> object. The attribute <b>SPEC_TYPE</b> is randomly generated between the
   * integers 1 and 2.
   *
   * <p>
   * The <b>SPEC_TYPE</b> attribute dictates what the range of the value of <b>dToCollect</b> will be.
   */

  public CardGroup6 () {

    this.SPEC_TYPE = randomGen(1, 2);
    int multiplier = (int) Math.pow(10, randomGen(1, 2));

    if (this.SPEC_TYPE == 1)
     this.dToPay = (double) randomGen(1, 4) * multiplier;

    else
      this.dToPay = (double) randomGen(3, 6) * multiplier;

  }

  /**
   * Getter method for the <b>SPEC_TYPE</b> attribute of this class.
   *
   * @return Integer holding the specific type of an instance.
   */

  public int getType () {
    return this.SPEC_TYPE;
  }

  /**
   * Getter method for the <b>dToCollect</b> attribute of this class.
   *
   * @return Double-precision floating value holding the value to be collected when an instance is drawn at application
   * run.
   */

  public double getPay () {
    return this.dToPay;
  }

}

