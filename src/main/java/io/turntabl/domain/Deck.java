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
    }

    public Card dealCard() {
        Card dealtCard = this.cards.get(this.cards.size() - 1);
        this.cards.remove(dealtCard);
        return dealtCard;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
