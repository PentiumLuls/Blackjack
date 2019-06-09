import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private boolean isEnded;
    private Deck deck;
    private List<Player> players;
    private int bet;

    private Scanner scan = new Scanner(System.in);

    public Game() {
        isEnded = false;
        bet = 0;
        players = new ArrayList<>();
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public boolean ended() {
        return isEnded;
    }

    public void showCards() {
        for (Player player : players) {
            if (!(player instanceof Bot)) {
                System.out.println("~===~ " + player.name + " CARDS ~===~");
                player.showCards();
            }
        }
    }

    public void askForTurn() {
        System.out.println("~===~ YOUR TURN ~===~");

        for (Player player : players) {
            if (!(player instanceof Bot)) {
                System.out.print("Do you want to take another card? ('y' or 'n'): ");
                String answer = scan.nextLine();
                if (answer.equals("y") || answer.equals("yes")) {
                    Card card = deck.getCard();
                    player.addCard(card);
                    System.out.println("~~You took new card: " + card);
                    System.out.println("Now you cards is:");
                    player.showCards();
                } else {
                    System.out.println(" ~~You wait the turn");
                }
            }
        }
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void askForEnd() {
        System.out.print("~~Do you want to end this game? ('y' or 'n'): ");
        String answer = scan.nextLine();
        if (answer.equals("y") || answer.equals("yes")) {
            isEnded = true;
        }
    }

    public void showResult() {
        System.out.println("~===~ RESULTS ~===~");

        for (Player player : players) {
            int cardsSum = player.getCardsSum();
            System.out.println(player.name + " score is " + cardsSum);
            System.out.println(player.name + " cards: ");
            player.showCards();
        }

        int maxScore = getMaxScore();
        String winnerNames = getWinnerNames(maxScore);
        if (winnerNames != null) {
            System.out.println("~~Wow, " + winnerNames + " win with score " + maxScore + "~~");
        } else {
            System.out.println("~~There is no winner~~");
        }
    }

    private int getMaxScore() {
        return players.stream()
                .map(player -> player.getCardsSum())
                .filter(sum -> sum <= 21)
                .max(Comparator.comparing(Integer::valueOf))
                .orElse(0);
    }

    private String getWinnerNames(int maxScore) {
        return players.stream()
                .filter(player -> player.getCardsSum() == maxScore)
                .map(player -> player.name)
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse(null);
    }

    public void getCardsBack() {
        players.forEach(player -> player.removeCards());
    }

    public void giveInitialCards() {
        players.forEach(player -> player.addCard(deck.getCard()));
        players.forEach(player -> player.addCard(deck.getCard()));
    }
}
