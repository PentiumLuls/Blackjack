import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck getDeck() {
        List<Card> newCards = new ArrayList<>();
        for (Value value : Value.values()) {
            newCards.add(new Card(value, Suit.CLUB));
            newCards.add(new Card(value, Suit.DIAMOND));
            newCards.add(new Card(value, Suit.SPADE));
            newCards.add(new Card(value, Suit.HEART));
        }
        return new Deck(newCards);
    }

    public Card getCard() {
        return cards.remove(cards.size() - 1);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
