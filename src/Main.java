import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isGame = true;
        Player player = new Player("Jack");
        Player player2 = new Bot();

        while(isGame) {
            Game game = new GameBuilder()
                    .createDeck()
                    .shuffleDeck()
                    .addPlayer(player)
                    .addPlayer(player2)
                    .giveToPlayersInitialCards()
                    .build();

            while (!game.ended()) {
                game.showCards();
                game.askForTurn();
                game.askForEnd();
            }

            game.showResult();
            game.getCardsBack();
            isGame = askForNewGame();
        }
    }

    private static boolean askForNewGame() {
        System.out.print("Do you want to start new game? ('y' or 'n')");
        String answer = scan.nextLine();
        if (answer.equals("y") || answer.equals("yes")) {
            return true;
        }
        return false;
    }
}
