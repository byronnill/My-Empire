package Model;

public class CardGroup2 extends Card {

  private final int SPEC_TYPE;

  private int nIndexToGo;

  public CardGroup2 (Board board) {

    this.SPEC_TYPE = randomGen(1, 3);

    if (this.SPEC_TYPE == 1)
      nIndexToGo = findRandomProperty(board);

    else
      nIndexToGo = -1;

  }

  public boolean doChance (Board board, Player player, Bank bank) {

    switch (this.SPEC_TYPE) {

      case 2:  this.nIndexToGo = findNearestRail(board, player); break;
      case 3:  this.nIndexToGo = findNearestUtil(board, player);

    }

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

  public int findNearestRail (Board board, Player player) {

    int nHolder = player.getLocationIndex();

    while (!(board.getBoardSpaces().get(nHolder) instanceof Railroad))
      nHolder = (nHolder + 1) % 32;

    return nHolder;

  }

  public int findNearestUtil (Board board, Player player) {

    int nHolder = player.getLocationIndex();

    while (!(board.getBoardSpaces().get(nHolder) instanceof Utility))
      nHolder = (nHolder + 1) % 32;

    return nHolder;

  }

  public int getType () {
    return this.SPEC_TYPE;
  }

  public int getIndexToGo () {
    return this.nIndexToGo;
  }

}
