package Model;

public class CardGroup3 extends Card {

  private final int SPEC_TYPE;

  private double dToCollect;

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

  public double getCollect () {
    return dToCollect;
  }

  public int getType () {
    return this.SPEC_TYPE;
  }

}
