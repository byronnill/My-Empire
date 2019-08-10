package Model;

import java.util.*;

/**
 * Class handling the <b>gameDeck</b> attribute of a master <b><i>Game</i></b> object. The instantiation of this class
 * must always be preceded, even indirectly, by the instantiation of a <b><i>Board</i></b> object.
 */

public class Deck {

  private ArrayList <Card> activeDeck;
  private ArrayList <Card> discardDeck;

  /**
   * Constructor for a <b><i>Deck</i></b> object that, in itself, contructs multiple instances of the multiple
   * subclasses of the <b><i>Card</i></b> class. Upon the last instantiation, the constructor will call the
   * <b>shuffle()</b> method from the <b><i>java.util</i></b> class Collections.
   *
   * @param board <b><i>Board</i></b> object that will be the basis for the instantiation of <b><i>CardGroup2</i></b>
   *              and <b><i>CardGroup4</i></b> instances with <b>SPEC_TYPE</b> attributes of 1 and 2, respectively.
   * @see Collections#shuffle(List)
   * @see CardGroup2#findRandomProperty(Board)
   * @see CardGroup4#findRandomProperty(Board)
   */

  public Deck (Board board) {

    activeDeck = new ArrayList <> ();
    discardDeck = new ArrayList <> ();

    for (int i = 0; i < 2; i++)
      activeDeck.add(new CardGroup1());

    for (int i = 0; i < 6; i++)
      activeDeck.add(new CardGroup2(board));

    for (int i = 0; i < 6; i++)
      activeDeck.add(new CardGroup3());

    for (int i = 0; i < 4; i++)
      activeDeck.add(new CardGroup4(board));

    for (int i = 0; i < 7; i++)
      activeDeck.add(new CardGroup5());

    for (int i = 0; i < 3; i++)
      activeDeck.add(new CardGroup6());

    Collections.shuffle(activeDeck);

  }

  /**
   * Equivalent getter method that returns the <b><i>Card</i></b> instance that is at index 0 of <b>activeDeck</b>.
   *
   * <p>
   * Upon the removal of the top object in the ArrayList, if the <b>activeDeck</b> attribute is left empty, the
   * <b>replaceDeckAndShuffle()</b> method is called.
   *
   * @return <b><i>Card</i></b> object that is taken from index 0 of the <b>activeDeck</b> attribute.
   * @see #replaceDeckAndShuffle()
   */

  public Card drawCard () {

    Card drawn = activeDeck.remove(0);

    if (activeDeck.isEmpty())
      replaceDeckAndShuffle();

    return drawn;

  }

  /**
   * Equivalent setter method that adds a new <b><i>Card</i></b> object to the <b>discardDeck</b>.
   *
   * @param toDiscard <b><i>Card</i></b> instance that will be added to the <b>discardDeck</b> attribute.
   */

  public void discardCard (Card toDiscard) {
    discardDeck.add(toDiscard);
  }

  /**
   * Method that sets the <b>activeDeck</b> attribute equal to the <b>discardDeck</b> attribute. After that setting,
   * the former is shuffled through Collections.shuffle() while the latter's contents are cleared.
   */

  private void replaceDeckAndShuffle () {

    activeDeck = discardDeck;
    Collections.shuffle(activeDeck);

    discardDeck.clear();

  }

  /**
   * Console printing method that displays the contents of <b>activeDeck</b> on the environment console through
   * <b>consolePrintDeck()</b>. Used only in program testing and confirmation.
   *
   * @see #consolePrintDeck(ArrayList)
   */

  public void consolePrintActiveDeck () {

    System.out.println("Active Deck: ");
    consolePrintDeck(activeDeck);

  }

  /**
   * Console printing method that displays the contents of <b>discardDeck</b> on the environment console through
   * <b>consolePrintDeck()</b>. Used only in program testing and confirmation.
   *
   * @see #consolePrintDeck(ArrayList)
   */

  public void consolePrintDiscardDeck () {

    System.out.println("Discard Pile: ");
    consolePrintDeck(discardDeck);

  }

  /**
   * Console printing method called by <b>consolePrintActiveDeck</b> and <b>consolePrintDiscardDeck</b>. Lists and
   * prints all cards in the appropriate deck in the order that they were inserted. The method also displays on the
   * environment console the number of <b><i>Card</i></b> instances that are in each group (subclass). Used only
   * in program testing and confirmation.
   *
   * @param deckToPrint
   */

  private void consolePrintDeck (ArrayList<Card> deckToPrint) {
    int[] i = new int[6];

    for (int index : i)
      index = 0;

    for (Card c : deckToPrint) {

      if (c instanceof CardGroup1) {
        System.out.println("1: Get out of jail free");
        i[0]++;
      }

      else if (c instanceof CardGroup2) {
        System.out.println("2: Proceed to property/utility/railroad without collecting start");
        i[1]++;
      }

      else if (c instanceof CardGroup3) {
        System.out.println("3: Collect cash");
        i[2]++;
      }

      else if (c instanceof CardGroup4) {
        System.out.println("4: Go to jail or proceed to property");
        i[3]++;
      }

      else if (c instanceof CardGroup5) {
        System.out.println("5: Augment property rent");
        i[4]++;
      }

      else {
        System.out.println("6: Pay cash");
        i[5]++;
      }

    }

    System.out.println();

    for (int index = 0; index < i.length; index++)
      System.out.println("Number of cards in group " + (index + 1) + ": " + i[index]);
  }



}
