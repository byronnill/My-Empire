package Model;

/**
 * Class handling the application of its own instances to <b><i>Player</i></b> objects.
 */

public class CardGroup1 extends Card { //jail

  /**
   * Empty constructor for a <b><i>CardGroup1</i></b> object.
   */

  public CardGroup1 () {}

  /**
   * Method that adds instances of this class to the <b>jailChanceCards</b> attribute of a <b><i>Player</i></b>
   * object.
   *
   * @param player <b><i>Player</i></b> object that will receive and instance of this class.
   */

  public void applyCardToPlayer (Player player) {

    player.addJailChance(this);

  }

}
