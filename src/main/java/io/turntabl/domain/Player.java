package io.turntabl.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    // Attributes
    private String username;
    private List<Card> dealtCards;

    public Player(String username) {
        this.username = username;
        this.dealtCards = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Card> getDealtCards() {
        return dealtCards;
    }

    public void setDealtCards(List<Card> dealtCards) {
        this.dealtCards = dealtCards;
    }

    public void addCard(Card card) {
        this.dealtCards.add(card);
    }

    public int getTotalCardValue() {
        return this.dealtCards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }
}
