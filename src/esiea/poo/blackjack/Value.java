package esiea.poo.blackjack;

public enum Value {
    AS("AS", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6),
    SEVEN("7", 7), EIGHT("8", 8),NINE("9", 9),TEN("10", 10), JACK("J", 10),DAME("Q", 10),ROI("K", 10);

    private String symbole;
    private int points;

    private Value(String v, int p) {
        this.symbole = v;
        this.points = p;
    }
    public String getSymbole() {
        return this.symbole;
    }
    public int getPoints() {
        return this.points;
    }
}
