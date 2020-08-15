import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    private int cardCount;

    public Deck(int count_cards) {
        if (count_cards == 36) {
            test(6);
            cardCount = 36;
        } else {
                test(2);
            cardCount = 52;
        }
    }

    private void test(int k){
        for (int i = k; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) deck.add(new Card(i + "", "\u2663"));
                else if (j == 1) deck.add(new Card(i + "", "\u2660"));
                else if (j == 2) deck.add(new Card(i + "", "\u2666"));
                else deck.add(new Card(i + "", "\u2665"));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public int getCardCount() {
        return cardCount;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }

    public void mixDeck(){
        Collections.shuffle(deck);
    }
}