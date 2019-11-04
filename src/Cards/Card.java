package Cards;

public class Card implements Comparable<Card> {
    Masti mast;
    Values value;

    public boolean possibleToBeatCard(Card enemyCard) {
        boolean result = false;
        if (enemyCard.mast == this.mast && enemyCard.value.number < this.value.number) {
            result = true;
        } else if (enemyCard.mast != Game.cosir && this.mast == Game.cosir) {
            result = true;
        }
        return result;
    }

    public Card(Masti mast, Values value) {
        this.mast = mast;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "mast=" + mast +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Card o) {
        return this.mast.compareTo(o.mast)*11113 + this.value.compareTo(o.value);
    }
}
