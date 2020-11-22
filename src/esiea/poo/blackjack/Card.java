package esiea.poo.blackjack;

public class Card {
    private Value value;
    private Color color;

    public Card(Value v, Color c) {
        this.value = v;
        this.color = c;
    }

    public String getColorSymbole() {
        if (this.color != null) {
            return this.color.getSymbole();
        }
        return null;
    }

    public String getColorName() {
        if (this.color != null) {
            return this.color.toString();
        }
        return null;
    }

    public String getValueSymbole() {
        if (this.value != null) {
            return this.value.getSymbole();
        }
        return null;
    }

    public int getPoints() {
        if (this.value != null) {
            return this.value.getPoints();
        }
        return 0;
    }


    public String toString() {
        return getValueSymbole() + getColorSymbole();
    }
}
