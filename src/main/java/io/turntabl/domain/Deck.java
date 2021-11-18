package io.turntabl.domain;

import io.turntabl.enums.CardDetail;
import io.turntabl.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        this.generate();
        System.out.println("=== Creating a deck ===");
    }

    public void generate() {
        for (Suit suit: Suit.values()) {
            for (CardDetail cardDetail: CardDetail.values()) {
                cards.add(new Card(cardDetail, suit));
            }
        }
    }

    public int getSize() {
        return this.cards.size();
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
        System.out.println("==== Shuffling Cards on Deck ====");
        System.out.println(this.cards);
        System.out.println("===== Shuffling Done ====");
    }

    public Card dealCard() {
        System.out.println("=== Dealing Card ===");
        Card dealtCard = this.cards.get(this.cards.size() - 1);
        this.cards.remove(dealtCard);
        return dealtCard;
    }
}
