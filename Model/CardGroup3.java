package Model;

public class CardGroup3 extends Card {

  private final int SPEC_TYPE;

  private double dToCollect;
  private boolean bGoToStart;

  public CardGroup3 () {

    this.SPEC_TYPE = randomGen(1, 5);

    this.bGoToStart = false;

    switch (this.SPEC_TYPE) {

      case 1:   this.dToCollect = 50; break;
      case 2:   this.dToCollect = 100; break;
      case 3:   this.bGoToStart = true; break;
      case 4:   this.dToCollect = 300; break;
      default:  this.dToCollect = 150;

    }

  }

  public boolean collectCash (Board board, Player currPlayer, Bank bank) {

    if (this.bGoToStart) {

      return currPlayer.movePlayer(board, 32 - currPlayer.getLocationIndex(), true, bank);

    } else {

      if (bank.getValue() <= this.dToCollect)
        return false;

      currPlayer.addOrDeductCash(this.dToCollect);
      bank.addOrDeduct(this.dToCollect * -1);

      return true;

    }

  }

}
