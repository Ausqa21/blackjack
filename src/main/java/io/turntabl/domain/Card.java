package io.turntabl.domain;

import io.turntabl.enums.CardDetail;
import io.turntabl.enums.Suit;

public class Card {

    // Attributes
    private String name;
    private int value;
    private Suit suit;


    public Card(CardDetail cardDetail, Suit suit) {
        this.name = cardDetail.name();
        this.value = cardDetail.value;
        this.suit = suit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", suit=" + suit +
                '}';
    }
}
