import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards;
    String name;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public void showCards() {
        for (Card card : cards) {
            System.out.println(" - " + card);
        }
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardsSum() {
        return cards.stream()
                .map(card -> card.getValue().getValue())
                .reduce((v1, v2) -> v1 + v2)
                .orElse(0);
    }
}
