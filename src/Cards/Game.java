package Cards;

import java.util.*;

public class Game {

    static Masti cosir;
    ArrayList<Card> deck = new ArrayList<>();
    static Map<Card, Card> battlefield = new TreeMap<>();
    Player playerOne;
    Player playerTwo;
    Random random = new Random();

    public Game() {

    }

    public static void main(String[] args) {
//        ArrayList<String> coleList = new ArrayList<>();
//        Collections.addAll(coleList, "wee", "srsr", "wweree","asasasaas","asd","zzzz");
//        Game game = new Game();
//        game.removeAfterIndex(coleList, 2);
        Game game = new Game(new Player("John"), new Player("Tom"));
        System.out.println(game);
    }

    //deck.size() ==0
    // playerOne.listCard.size()==0
    //playerTwo.listCard.size()==0
    //результат
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        createDeck();
        setCosirAndShuffleDeck();
        giveCards();
        setFirstWalkPlayer();
        while ((deck.size() == 0 && playerOne.listCard.size() == 0) || (deck.size() == 0 && playerOne.listCard.size() == 0)) {
            if (playerOne.isActive) {
                playerOne.makeTheMove(playerTwo, deck);
                playerOne.isActive = false;
                playerTwo.isActive = true;
            } else {
                playerTwo.makeTheMove(playerOne, deck);
                playerTwo.isActive = false;
                playerOne.isActive = true;
            }
        }
    }

    public void startGame() {
        while (deck.size() != 0 || availabilityOfCards()) {

        }
    }

    private boolean availabilityOfCards() {
        return playerOne.listCard.size() != 0 && playerOne.listCard.size() != 0;
    }

    private void createDeck() {
        for (Values cardValue : Values.values()) {
            for (Masti cardMast : Masti.values()) {
                if (cardValue != Values.EmptyCard) {
                    deck.add(new Card(cardMast, cardValue));
                }
            }
        }

        removeAfterIndex(deck,15);

    }

    private void setCosirAndShuffleDeck() {
        cosir = Masti.values()[random.nextInt(3)];
        Collections.shuffle(deck);
    }

    private void giveCards() {
        for (int i = 0; i < 14; i++) {
            if (i % 2 == 0) {
                playerOne.listCard.add(deck.get(i));
                deck.remove(i);
            } else {
                playerTwo.listCard.add(deck.get(i));
                deck.remove(i);
            }
        }
    }

    private void setFirstWalkPlayer() {
        Card minCosirOfPlayerOne = new Card(cosir, Values.EmptyCard);
        Card minCosirOfPlayerTwo = new Card(cosir, Values.EmptyCard);

        for (Card playerCard : playerOne.listCard) {        //сравниваем козыря
            if (playerCard.mast == cosir && playerCard.value.number < minCosirOfPlayerOne.value.number) {
                minCosirOfPlayerOne = playerCard;
            }
        }
        for (Card playerCard : playerTwo.listCard) {
            if (playerCard.mast == cosir && playerCard.value.number < minCosirOfPlayerTwo.value.number) {
                minCosirOfPlayerTwo = playerCard;
            }
        }

        if (minCosirOfPlayerOne == minCosirOfPlayerTwo) {//выбираем рандомного игрока так как у них нет козырей
            int result = random.nextInt(1);
            if (result == 0) {
                playerOne.isActive = true;
                playerTwo.isActive = false;
            } else {
                playerTwo.isActive = true;
                playerOne.isActive = false;
            }
        }

        if (minCosirOfPlayerOne.value.number < minCosirOfPlayerTwo.value.number) {
            playerOne.isActive = true;
            playerTwo.isActive = false;
        } else {
            playerTwo.isActive = true;
            playerOne.isActive = false;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "cosir=" + cosir +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                '}';
    }

    public Map<Card, Card> getBattlefield() {
        return battlefield;
    }

    public <T> void removeBeforeIndex(Collection<T> collection, int index) {
        if (index > 0 && index < collection.size()) {
            Iterator<T> iterator = collection.iterator();
            for (int i = 0; i <= index; i++) {
                iterator.next();
                iterator.remove();
            }
        }else{
            System.out.println("Ошибка!");
            return;
        }

        System.out.println(collection);
    }

    public <T> void removeAfterIndex(Collection<T> collection, int index) {
        if (index > 0 && index < collection.size()) {
            Iterator<T> iterator = collection.iterator();
            int k=0;
            while(k<index){
                iterator.next();
                k++;
            }
           final  int  size=collection.size();
            for (int i =0; i <size-index; i++) {
                iterator.next();
                iterator.remove();


            }
        }else{
            System.out.println("Ошибка!");
            return;
        }

        System.out.println(collection);
    }
}
