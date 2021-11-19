package io.turntabl.domain;

import io.turntabl.domain.Card;
import io.turntabl.domain.Deck;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Bag Class Test")
public class DeckTest {

    // Create test deck
    Deck deck;

    @BeforeEach
    void setUp() {
        this.deck = new Deck();
    }

    @Test
    @DisplayName("Testing Generate Cards")
    void testGenerate() {
        assertEquals(52, deck.getSize());
    }

    @Test
    @DisplayName("Shuffling cards on deck")
    void testDeckShuffle(){
        List<Card> cards = deck.getCards();
        deck.shuffle();
        List<Card> shuffledCards = deck.getCards();
        assertNotEquals(cards.get(0),shuffledCards.get(0));
    }


    @Test
    @DisplayName("Dealing first two cards on deck")
    void testDealCard() {
        deck.generate();
        Card dealtCard = deck.dealCard();
        assertEquals(11, dealtCard.getValue());
        assertEquals(10, deck.dealCard().getValue());
    }
}
