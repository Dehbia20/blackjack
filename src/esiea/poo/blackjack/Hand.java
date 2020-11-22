package esiea.poo.blackjack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hand {
    private LinkedList<Card> cardList = new LinkedList<>();
    private List<Integer> scores = new ArrayList<>();
    public Hand() {
    }
    public void clear() {
        this.cardList.clear();
    }

    public void add(Card c) {
        cardList.add(c);
        this.count();
    }

    public String toString() {
        return cardList == null ? "Hand is null !" : this.cardList.toString() + " : " + scores.toString();
    }

    List<Integer> count() {
        int numberOfAs = 0;
        int minTotal = 0;
        for (Card c: this.cardList) {
            if ("AS".equals(c.getValueSymbole())) {
                numberOfAs++;
            }
            minTotal += c.getPoints();

        }

        if (scores.isEmpty()) {
            scores.add(minTotal);
        } else {
            scores.set(0, minTotal);
        }
        int size = scores.size();
        for (int i = 0; i < numberOfAs; i++) {
            int lastVal = scores.get(i);
            if (size - 1 > i) { // valeur exite déjà
                scores.set(i + 1, lastVal - 1 + 11);
            } else { // inserer valeur
                scores.add(lastVal - 1 + 11);
            }
        }
        return scores;
    }

    public int best() {
        if (this.scores.size() == 1) {
            return this.scores.get(0);
        }
        int b = 0;
        for (int v : this.scores ) {
            if (b == 0) {
                b = v;
            } else if (v <= 21 && (b > 21 || v > b)) {
                b = v;
            } else if (v > b && b > 21) {
                b = v;
            }
        }
        return b;
    }
}
