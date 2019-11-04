package Cards;

public enum Values {
    Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Valet(11), Dama(12), King(13), Tuz(100), EmptyCard(1000);
    int number;

    Values(int i) {
        this.number = i;
    }
}
