package Cards;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Player {
    public final int LOSE = 33;
    String name;
    ArrayList<Card> listCard = new ArrayList<>();
    boolean isActive = false;

    public void makeTheMove() {
        viewMyCards();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        Card cardForMove = listCard.get(input);

        if (searchCardsWithSameNumbers(cardForMove) == true) {
            Game.battlefield.put(cardForMove, null);
            listCard.remove(cardForMove);
        }
        System.out.println(Game.battlefield);
    }

    private boolean searchCardsWithSameNumbers(Card cardForMove) {
        boolean result = false;
        if (Game.battlefield.size() == 0) {
            return true;
        }
        for (Map.Entry<Card, Card> card : Game.battlefield.entrySet()) {
            if (card.getKey().value.number == cardForMove.value.number || card.getValue().value.number == cardForMove.value.number) {
                result = true;
            }
        }
        return result;
    }

    public void viewMyCards() {
        int iterator = 0;
        System.out.println(Game.cosir);
        for (Card card : listCard) {
            System.out.println(iterator + " - " + card);
            iterator++;
        }
    }

    public void defence() {
        viewMyCards();
        System.out.println("33 - взять все карты");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int choiceIndex = scanner.nextInt();
            if (choiceIndex == LOSE) {
                takeAllCards();
                return;
            } else {
                Card choiceCard = listCard.get(choiceIndex);
                Card enemyCard = null;
                for (Map.Entry<Card, Card> card : Game.battlefield.entrySet()) {
                    if (card.getValue() == null) {
                        enemyCard = card.getKey();
                        if (choiceCard.possibleToBeatCard(enemyCard)) {
                            card.setValue(choiceCard);
                            listCard.remove(choiceIndex);
                         //   return;
                        } else {
                            System.out.println("Вы выбрали неправильную карту! попробуйте ещё раз!");
                      //      break;
                        }
                    }
                }
            }
        }
    }

    public void takeAllCards() {
        for (Map.Entry<Card, Card> card : Game.battlefield.entrySet()) {
            listCard.add(card.getKey());
            listCard.add(card.getValue());
        }
        Game.battlefield.clear();
        return;
    }


    public void replenishCard(int countCard) {

    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", listCard=" + listCard +
                ", isActive=" + isActive +
                '}';
    }

    public Player(String name) {
        this.name = name;
    }

}
