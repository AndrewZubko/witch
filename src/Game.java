import java.io.IOException;
import java.util.*;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.w3c.dom.ls.LSOutput;

public class Game {
    private ArrayList<User> userList = new ArrayList<User>();
    private static Scanner in = new Scanner(System.in);
    private Deck deck;
//    public static final Logger logger = LoggerFactory.getLogger(Game.class);

    public Game(int countCards, int countUsers) {
//        logger.trace("это конструктор");
        deck = new Deck(countCards);
        userList = addUsersInList(countUsers);
    }

    public static int checkCountUser() {
        int countUser = 0;
        System.out.print("Please input count of user (from 2 to 6): ");
        while (!Arrays.asList(new Integer[]{2, 3, 4, 5, 6}) //автоупаковка
                .contains(in.hasNextInt() ? countUser = in.nextInt() : in.next())) {
            System.out.println("Error, input valid value!");
            System.out.print("Please input count of user (from 2 to 6): ");
        }
        return countUser;
    }

    public static int checkCountCard() {
        int countCard = 0;
        System.out.print("Please input count of cards (36 or 52): ");
        while (!Arrays.asList(new Integer[]{36, 52}) //автоупаковка
                .contains(in.hasNextInt() ? countCard = in.nextInt() : in.next())) {
            System.out.println("Error, input valid value!");
            System.out.print("Please input count of cards (36 or 52): ");
        }
        return countCard;
    }

    public ArrayList<User> addUsersInList(int countUsers) {//создание списка играков
        ArrayList<User> userList = new ArrayList<>();
        Scanner st = new Scanner(System.in);
        for (int i = 1; i <= countUsers; i++) {
            String tmp = "";
            System.out.println("Please, input name of user " + i + ": ");
            tmp = st.nextLine();
            userList.add(new User(tmp));
            System.out.println();
        }
        st.close();
        return userList;
    }

    public void dealСards(int cardCount) { //делим карты между играками(раздача)
        List<Card> cards;
        int div = 0;
        for (int i = 0; i < userList.size(); i++) {
            div = cardCount / (userList.size() - i);
            cards = deck.getDeck().subList(0, div);
            userList.get(i).getUserCards().addAll(cards);
            cards.clear();
            cardCount -= div;
        }
    }

    public int randomNumber(int a) {
        return 0 + (int) (Math.random() * a);
    } //выдаёт число от 0 до а (не включительно)

    public boolean gameOver() { //проверка закончилась играл или нет, удаление играков которые закончили
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserCards().isEmpty()) {
                list.add(i);
            }
        }
        for (int i : list) {
            System.out.println("Игрок " + userList.get(i).getName() + " закончил игру" + "\u270C" + "\n");
            userList.remove(i);
        }
        list.clear();
        if (userList.size() != 1) {
            return true;
        } else {
            System.out.printf("Игрок %s проиграл :(", userList.get(0).getName());
            return false;
        }
    }

    public void pullCard() { //игроки тянут карты по кругу, начиная с рандомного игрока
        int a1 = randomNumber(userList.size()); //кто тянет первым
        int a2;
        while (gameOver()) {
            if (a1 >= userList.size()) { //1
                a1 = 0;
                a2 = a1 + 1;
            } else if (a1 == (userList.size() - 1)) { //2
                a2 = 0;
            } else { //3
                a2 = a1 + 1;
            }
            int randomCard = randomNumber(userList.get(a2).getUserCards().size());
            threadSleep(500);
            System.out.println("Игрок " + userList.get(a1).getName() + " вытащил карту " +
                    userList.get(a2).getUserCards().get(randomCard) + " у игрока " + userList.get(a2).getName());
            userList.get(a1).getUserCards().add(userList.get(a2).getUserCards().get(randomCard));
            threadSleep(300);
            System.out.println(userList.get(a1).getUserCards());

            userList.get(a2).getUserCards().remove(randomCard);
            userList.get(a1).popCards();
            System.out.println();
            a1++;
        }
    }

    public void playGame() throws IOException {
        System.out.println("Игра началась" + "\u231B" + "\n");
        threadSleep(500);
        System.out.println("Список игроков:");
        for (int i = 1; i <= userList.size(); i++) {
            threadSleep(500);
            System.out.println("Игрок №" + i + ": " + userList.get(i - 1).getName());
        }
        System.out.println();
        deck.mixDeck(); //перемешали созданую колоду
        dealСards(deck.getCardCount()); //поделили карты между играками
        for (User u : userList) {
            threadSleep(500);
            u.popCards();
            System.out.println();
        }
        threadSleep(500);
        pullCard();

    }

    public void threadSleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game(checkCountCard(), checkCountUser()); //создали игру, выбрали колоду и кол-во играков
        game.playGame();
    }
}