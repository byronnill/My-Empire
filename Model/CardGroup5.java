package Model;

public class CardGroup5 extends Card {

  private final int SPEC_TYPE;

  private double dChangeRate;

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

  public boolean isCardApplicable (Player player) {

    if (player.getOwnedPerType(0) == 0 && player.getOwnedPerType(1) == 0 && player.getOwnedPerType(2) == 0 &&
            player.getOwnedPerType(3) == 0 && player.getOwnedPerType(4) == 0 && player.getOwnedPerType(5) == 0 &&
            player.getOwnedPerType(6) == 0 && (this.SPEC_TYPE == 1 || this.SPEC_TYPE == 2 || this.SPEC_TYPE == 3)) {

      return false;

    } else if (player.getOwnedPerType(8) == 0 && player.getOwnedPerType(7) == 0 && this.SPEC_TYPE == 4) {

      return false;

    } else {

      return true;

    }

  }

  public int applyCardToSpace (OwnableSpace space) {

    if (SPEC_TYPE == 1) {

      if (((Property) space).isDoubleRent())
        return 0;

      space.setRent(space.getRent() * this.dChangeRate);
      ((Property) space).setDoubleRent(true);
      ((Property) space).setDoubleRentHolder(this);

    } else if (SPEC_TYPE == 2) {

      double dToPay;

      if (((Property) space).getDevelopment() == 5)
        dToPay = 50;

      else
        dToPay = 25 * ((Property) space).getDevelopment();

      if (space.getOwner().isPaymentPossible(dToPay))
        space.setRent(space.getRent() * this.dChangeRate);

      else
        return -1;

    } else {

      space.setRent(space.getRent() * this.dChangeRate);

    }

    return 1;

  }

  public int getType () {
    return this.SPEC_TYPE;
  }
}
