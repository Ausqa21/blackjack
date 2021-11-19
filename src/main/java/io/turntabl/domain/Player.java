package io.turntabl.domain;

import io.turntabl.enums.StrategyType;

import java.util.ArrayList;
import java.util.List;

public class Player {

    // Attributes
    private String username;
    private List<Card> dealtCards;
    private final StrategyType strategy;

    public Player(String username) {
        this.username = username;
        this.dealtCards = new ArrayList<>();
        this.strategy = StrategyType.DEFAULT;
    }

    public Player(String username, StrategyType strategy) {
        this.username = username;
        this.dealtCards = new ArrayList<>();
        this.strategy = strategy;
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

    public StrategyType getStrategy() {
        return this.strategy;
    }

    public void addCard(Card card) {
        this.dealtCards.add(card);
    }

    public int getTotalCardValue() {
        return this.dealtCards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", dealtCards=" + dealtCards +
                ", strategy=" + strategy +
                '}';
    }
}
