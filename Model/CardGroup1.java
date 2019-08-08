package Model;

/**
 * Class handling the application of instances of this class to the <i>jailChanceCards</i> attribute of
 * <b><i>Player</i></b> objects. In running an application with instances of this class, instances of
 * <b><i>CardGroup1</i></b> are referred to as <i>GET OUT OF JAIL FREE cards</i>.
 *
 * @see Player
 */

public class CardGroup1 extends Card { //jail

  /**
   * Empty constructor for a <b><i>CardGroup1</i></b> object.
   */

  public CardGroup1 () {}

  /**
   * Method used to add the active instance of this class to the <i>jailChanceCards</i> attribute of
   * <b><i>Player</i></b> objects.
   *
   * @param player Player instance to which this object will be applied to.
   */

  public void applyCardToPlayer (Player player) {

    player.addJailChance(this);

  }

}
