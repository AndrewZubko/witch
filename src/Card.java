import java.util.ArrayList;

public class Card {
    /*
    2663 - крест
    2660 - пика
    2666 - бубна
    2665 - чирва
    */
    private String suit;
    private String denomination;

    public Card(String denomination, String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getDenomination() {
        return denomination;
    }

    @Override
    public String toString() {
        if (Integer.parseInt(denomination) < 11) return denomination + suit;
        else if (denomination.equals("11")) return "J" + suit;
        else if (denomination.equals("12")) return "Q" + suit;
        else if (denomination.equals("13")) return "K" + suit;
        else return "A" + suit;

    }

}