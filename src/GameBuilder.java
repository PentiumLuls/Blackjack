public class GameBuilder {
    private Game game;

    public GameBuilder() {
        game = new Game();
    }


    public GameBuilder createDeck() {
        Deck deck = Deck.getDeck();
        game.setDeck(deck);
        return this;
    }

    public GameBuilder addPlayer(Player player) {
        game.addPlayer(player);
        return this;
    }

    public Game build() {
        return game;
    }

    public GameBuilder shuffleDeck() {
        game.shuffleDeck();
        return this;
    }

    public GameBuilder giveToPlayersInitialCards() {
        game.giveInitialCards();
        return this;
    }
}
