package Model;

/**
 * Class handling the determination of how much cash will be given to a <b><i>Player</i></b> when an instance of
 * this class is drawn on an application run.
 */

public class CardGroup3 extends Card {

  private final int SPEC_TYPE;

  private double dToCollect;

  /**
   * Constructor for a <b><i>CardGroup2</i></b> object that randomly generates the <b>SPEC_TYPE</b> attribute.
   * For <b>SPEC_TYPE</b> == 3, <b>dToCollect</b> is set to 0 because the <b><i>Player</i></b> will be moved to index
   * 0 of the active <b><i>Board</i></b> as well, letting them collect 200 immediately.
   */

  public CardGroup3 () {

    this.SPEC_TYPE = randomGen(1, 5);

    switch (this.SPEC_TYPE) {

      case 1:   this.dToCollect = 50; break;
      case 2:   this.dToCollect = 100; break;
      case 3:   this.dToCollect = 0; break;
      case 4:   this.dToCollect = 300; break;
      default:  this.dToCollect = 150;

    }

  }

  /**
   * Getter method that returns the <b>dToCollect</b> attribute of this class.
   *
   * @return dToCollect Double-precision floating point value holding the amount that will be given to the
   * <b><i>Player</i></b> who draws an instance of this class.
   */

  public double getCollect () {
    return dToCollect;
  }

  /**
   * Getter method that returns the <b>SPEC_TYPE</b> attribute of this class.
   *
   * @return SPEC_TYPE Integer holding the specific type of this class that was determined at construction.
   */

  public int getType () {
    return this.SPEC_TYPE;
  }

}
