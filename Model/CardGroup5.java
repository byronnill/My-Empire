package Model;

/**
 * Class handling the application of its own instances to applicable <b><i>OwnableSpace</i></b> objects.
 */

public class CardGroup5 extends Card {

  private final int SPEC_TYPE;

  private double dChangeRate;

  /**
   * Constructor for a <b><i>CardGroup5</i></b> object. The attribute <b>SPEC_TYPE</b> is randomly generated between the
   * integers 1 and 5.
   *
   * <p>
   * The <b>SPEC_TYPE</b> attribute dictates what the value of <b>dChangeRate</b> will be and the
   * <b><i>OwnableSpace</i></b> subclasses that instances of this class can be applied to.
   */

  public CardGroup5 () {

    switch (randomGen(1, 5)) {

      case 1:   dChangeRate = 2.00;
                SPEC_TYPE = 1;
                break;

      case 2:   dChangeRate = 1.50;
                SPEC_TYPE = 2;
                break;

      case 3:   dChangeRate = 0.90;
                SPEC_TYPE = 3;
                break;

      case 4:   dChangeRate = 1.10;
                SPEC_TYPE = 4;
                break;

      default:  dChangeRate = 0.90;
                SPEC_TYPE = 4;

    }

  }

  /**
   * Checking method for an instance of this class and an instance of <b><i>Player</i></b>.
   *
   * <p>
   * The checking returns true if any of the following are satisfied.
   *
   * <ul>
   *   <li>
   *     <b>SPEC_TYPE</b> = 1: <i>plyer</i> owns at least one instance of <b><i>Property</i></b>.
   *   </li>
   *   <li>
   *     <b>SPEC_TYPE</b> = 2: <i>plyer</i> owns at least one instance of <b><i>Property</i></b>.
   *    </li>
   *    <li>
   *     <b>SPEC_TYPE</b> = 3: <i>plyer</i> owns at least one instance of <b><i>Property</i></b>.
   *    </li>
   *    <li>
   *     <b>SPEC_TYPE</b> = 4: <i>plyer</i> owns at least one instance of <b><i>Railroad</i></b> or <b><i>Utility</i></b>.
   *    </li>
   * </ul>
   *
   * @param player <b><i>Player</i></b> object whose <b>ownedSpaces</b> will be checked for possible card application.
   * @return Boolean variable that is true if the card is indeed applicable to an <b><i>OwnableSpace</i></b> that the
   *         <i>player</i> owns.
   */

  public boolean isCardApplicable (Player player) {

    if (player.getOwnedPerType(0) == 0 && player.getOwnedPerType(1) == 0 && player.getOwnedPerType(2) == 0 &&
            player.getOwnedPerType(3) == 0 && player.getOwnedPerType(4) == 0 && player.getOwnedPerType(5) == 0 &&
            player.getOwnedPerType(6) == 0 && (this.SPEC_TYPE == 1 || this.SPEC_TYPE == 2 || this.SPEC_TYPE == 3)) {

      return false;

    } else return player.getOwnedPerType(8) != 0 || player.getOwnedPerType(7) != 0 || this.SPEC_TYPE != 4;

  }

  /**
   * Method that applies the effects of an instance to an <b><i>OwnableSpace</i></b>.
   *
   * @param space <b><i>OwnableSpace</i></b> object whose <b>dMultiplier</b> attribute will be modified.
   * @return Integer that is greater than 1 if payment needs to be made after the application of an instance.
   */

  public double applyCardToSpace (OwnableSpace space) {

    space.setMultiplier(dChangeRate * space.getMultplier());

    if (SPEC_TYPE == 1) {

      space.setRent(space.getRent() * this.dChangeRate);
      ((Property) space).setDoubleRent(true);
      ((Property) space).setDoubleRentHolder(this);

      return 1;

    } else if (SPEC_TYPE == 2) {

      double dToPay;

      if (((Property) space).getDevelopment() == 5)
        dToPay = 50;

      else
        dToPay = 25 * ((Property) space).getDevelopment();


      space.setRent(space.getRent() * this.dChangeRate);
      return dToPay;

    } else {

      space.setRent(space.getRent() * this.dChangeRate);
      return 1;

    }

  }

  public int getType () {
    return this.SPEC_TYPE;
  }
}
