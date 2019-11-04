package Cards;

import java.util.*;

public class Game {

    static Masti cosir;
    ArrayList<Card> deck = new ArrayList<>();
    static Map<Card, Card> battlefield = new TreeMap<>();
    Player playerOne;
    Player playerTwo;
    Random random = new Random();

    public static void main(String[] args) {
        Game game = new Game(new Player("John"), new Player("James"));
        System.out.println(game);
    }

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        createDeck();
        setCosirAndShuffleDeck();
        giveCards();
        setFirstWalkPlayer();
        if (playerOne.isActive) {
            playerOne.makeTheMove();
        } else {
            playerTwo.makeTheMove();
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
}
