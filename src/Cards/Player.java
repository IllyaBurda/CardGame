package Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Player {
    public final int LOSE = 33;
    public final int HANGUP = 44;
    String name;
    ArrayList<Card> listCard = new ArrayList<>();
    boolean isActive = false;

    public void makeTheMove(Player enemy, List<Card> koloda) {
        boolean flag = false;

        do {
            viewMyCards();
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            if (input == LOSE) {
                takeAllCards();
                enemy.replenishCard(koloda);
                return;
            } else if (input == HANGUP) {
                Game.battlefield.clear();
                replenishCard(koloda);
                enemy.replenishCard(koloda);
                break;
            }
            flag = checkPosibilityToBeatCard(input);
            System.out.println(Game.battlefield);
        } while (flag);
    }

//    private boolean searchCardsWithSameNumbers(Card cardForMove) {
//        boolean result = false;
//        if (Game.battlefield.size() == 0) {
//            return true;
//        }
//        for (Map.Entry<Card, Card> card : Game.battlefield.entrySet()) {
//            if (card.getKey().value.number == cardForMove.value.number || (card.getValue() == null ? false : (card.getValue().value.number == cardForMove.value.number))) {
//                result = true;
//            }
//        }
//        return result;
//    }

    public void viewMyCards() {
        System.out.println("Player - " + this.name);
        System.out.println(this.isActive);
        int iterator = 0;
        System.out.println(Game.cosir);
        for (Card card : listCard) {
            System.out.println(iterator + " - " + card);
            iterator++;
        }
    }

//    public void defence() {
//        viewMyCards();
//        System.out.println("33 - взять все карты");
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//            int choiceIndex = scanner.nextInt();
//            if (choiceIndex == LOSE) {
//                takeAllCards();
//                return;
//            } else {
//                checkPosibilityToBeatCard(choiceIndex);
//            }
//        }
//    }

    public boolean checkPosibilityToBeatCard(int choiceIndex) {
        boolean result = false;
        Card choiceCard = listCard.get(choiceIndex);
        Card enemyCard = null;
        for (Map.Entry<Card, Card> card : Game.battlefield.entrySet()) {
            if (card.getValue() == null) {
                enemyCard = card.getKey();
                if (choiceCard.possibleToBeatCard(enemyCard)) {
                    card.setValue(choiceCard);
                    listCard.remove(choiceIndex);
                    result = false;
                } else {
                    System.out.println("Вы выбрали неправильную карту! попробуйте ещё раз!");
                    result = true;
                    break;
                }
            }
        }

        if (enemyCard == null) {
            if (Game.battlefield.size() == 0) {
                Game.battlefield.put(choiceCard, null);
                listCard.remove(choiceIndex);
                result = false;
            } else {
                boolean flagBinarCard = false;
                for (Map.Entry<Card, Card> temp : Game.battlefield.entrySet()) {
                    if (temp.getKey().value.number == choiceCard.value.number || temp.getValue().value.number == choiceCard.value.number) {
                        flagBinarCard = true;
                        result = false;
                        break;
                    }
                }
                if (flagBinarCard == false) {
                    System.out.println("Вы не можете подкидывать эту карту");
                    result = true;
                }
            }

        }
        return result;
    }

    public void takeAllCards() {
        for (Map.Entry<Card, Card> card : Game.battlefield.entrySet()) {
            listCard.add(card.getKey());
            if (card.getValue() != null) {
                listCard.add(card.getValue());
            }
        }
        Game.battlefield.clear();
        return;
    }


    public void replenishCard(List<Card> cards) {
        while (listCard.size() < 7 && cards.size() > 0) {
            listCard.add(cards.get(0));
            cards.remove(0);
        }
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
