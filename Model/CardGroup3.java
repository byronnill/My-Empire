package Model;

/**
 * Class handling the construction of its instances and the assignment of values for the attribute <b>dToCollect</b>.
 */

public class CardGroup3 extends Card {

  private final int SPEC_TYPE;

  private double dToCollect;

  /**
   * Constructor for a <b><i>CardGroup3</i></b> object. The attribute <b>SPEC_TYPE</b> is randomly generated between the
   * integers 1 and 5.
   *
   * <p>
   * The <b>SPEC_TYPE</b> attribute dictates what the value of <b>dToCollect</b> will be.
   */

  public CardGroup3 () {

    this.SPEC_TYPE = randomGen(1, 5);

    switch (this.SPEC_TYPE) {

      case 1:   this.dToCollect = 50; break;
      case 2:   this.dToCollect = 100; break;
      case 3:   this.dToCollect = 200; break;
      case 4:   this.dToCollect = 300; break;
      default:  this.dToCollect = 150;

    }

  }

  /**
   * Getter method for the <b>dToCollect</b> attribute of this class.
   *
   * @return Double-precision floating value holding the value to be collected when an instance is drawn at application
   * run.
   */

  public double getCollect () {
    return dToCollect;
  }

  /**
   * Getter method for the <b>SPEC_TYPE</b> attribute of this class.
   *
   * @return Integer holding the specific type of an instance.
   */

  public int getType () {
    return this.SPEC_TYPE;
  }

}
