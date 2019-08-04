package Model;

public class Driver {

  public static void print (String toPrint) {

    System.out.println(toPrint);

  }


  public static void main (String[] args) {

    Game game = new Game(4);

    Player player1 = game.getPlayerList().get(0);
    Player player2 = game.getPlayerList().get(1);
    Player player3 = game.getPlayerList().get(2);
    Player player4 = game.getPlayerList().get(3);

    Property property1 = new Property(1, 0, 0, 4);

    print(game.getGameBank().toString());
    System.out.println();
//
//    game.getGameDeck().consolePrintActiveDeck();
//    System.out.println();
//
//    game.consolePrintPlayers();
//    System.out.println();
//
//    game.setGameBoard(new Board(4));
//    game.getGameBoard().consolePrintBoard();
//    System.out.println();

    print(player1.toString());
    System.out.println();

    player1.buyProperty(property1, game.getGameBank());

    print(game.getGameBank().toString());
    System.out.println();
    print(player1.toString());

  }

}
