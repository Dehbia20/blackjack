package esiea.poo.blackjack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private LinkedList<Card> cardList = new LinkedList<>();


    public Deck(int nbox) {
        for (int i = 0; i < nbox; i++) {
            for (Color c: Color.values()) {
                for(Value v : Value.values()) {
                    Card card = new Card(v, c);
                    cardList.add(card);
                }
            }
        }

        Collections.shuffle(this.cardList);
    }

    public int size() {
        return this.cardList != null ? this.cardList.size() : 0;
    }

    public Card draw() throws EmptyDeckException {
        if (cardList.isEmpty()) {
            throw new EmptyDeckException("Deck is empty, cannot poll from it !");
        }

        return cardList.poll();
    }

    public String toString() {
        if (this.cardList != null) {
            return this.cardList.toString();
        }
        return "deck is null!";
    }
}
