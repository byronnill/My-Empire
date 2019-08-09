package Model;

import java.util.*;

public class Deck {

  private ArrayList <Card> activeDeck;
  private ArrayList <Card> discardDeck;

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

  public Card drawCard () {

    Card drawn = activeDeck.remove(0);

    if (activeDeck.isEmpty())
      replaceDeckAndShuffle();

    return drawn;

  }

  public void discardCard (Card toDiscard) {
    discardDeck.add(toDiscard);
  }

  private void replaceDeckAndShuffle () {

    activeDeck = discardDeck;
    Collections.shuffle(activeDeck);

    discardDeck.clear();

  }

  public void consolePrintActiveDeck () {

    System.out.println("Active Deck: ");
    consolePrintDeck(activeDeck);

  }

  public void consolePrintDiscardDeck () {

    System.out.println("Discard Pile: ");
    consolePrintDeck(discardDeck);

  }

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
