import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String name;
    private List<Card> userCards = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userCards=" + userCards +
                '}';
    }

    public User(String name) {
        this.name = name;
    }

    public void popCards() {
        ArrayList<Card> newUserCards = new ArrayList<>(userCards);
        for (Card userCard : userCards) {
            if (newUserCards.size() == 2) {
                break;
            }
            List<Card> collect = newUserCards.stream()
                    .filter(thisUserCart -> !(thisUserCart.getDenomination().equals("12") && thisUserCart.getSuit().equals("\u2660")))
                    .filter(thisUserCart -> thisUserCart.getDenomination().equals(userCard.getDenomination()))
                    .collect(Collectors.toList());
            if (collect.size() > 1) {
                System.out.println("Игрок " + this.getName() + " скинул карты: " +
                        collect.get(0) + " " + collect.get(1));
                newUserCards.removeAll(collect.subList(0, 2));
            }
        }
        userCards = newUserCards;
    }


    public List<Card> getUserCards() {
        return userCards;
    }

    public void setUserCards(List<Card> userCards) {
        this.userCards = userCards;
    }

    public String getName() {
        return name;
    }
}