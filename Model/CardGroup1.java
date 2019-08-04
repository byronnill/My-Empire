package Model;

public class CardGroup1 extends Card { //jail

  public CardGroup1 () {}

  public void applyCardToPlayer (Player player) {

    player.addJailChance(this);

  }

}
