package Model;

public class CardGroup4 extends Card {

  private final int SPEC_TYPE;

  private int nIndexToGo;

  public CardGroup4 (Board board) {

    this.SPEC_TYPE = randomGen(1, 2);

    if (this.SPEC_TYPE == 1)
      this.nIndexToGo = findRandomProperty(board);

    else
      this.nIndexToGo = 16;

  }

  public boolean doChance (Board board, Player player, Bank bank) {

    int numSteps;

    if (player.getLocationIndex() > this.nIndexToGo)
      numSteps = this.nIndexToGo + 32 - player.getLocationIndex();

    else
      numSteps = this.nIndexToGo - player.getLocationIndex();

    if (!player.movePlayer(board, numSteps, true, bank))
      return false;

    //do something

    return true;

  }

  public int findRandomProperty (Board board) {

    int nHolder = randomGen(0, 32);

    while (!(board.getBoardSpaces().get(nHolder) instanceof Property))
      nHolder = randomGen(0, 32);

    return nHolder;

  }

  public int getType () {
    return this.SPEC_TYPE;
  }

  public int getIndexToGo () {
    return this.nIndexToGo;
  }

}
