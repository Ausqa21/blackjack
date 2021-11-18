package io.turntabl.domain;

import io.turntabl.domain.Card;
import io.turntabl.domain.Deck;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Dealing first two cards on deck")
    void testDealCard(){
        deck.generate();
//        deck.shuffle();
        Card dealtCard = deck.dealCard();
        System.out.println(dealtCard);
        assertEquals(11, dealtCard.getValue());
        assertEquals(51, deck.getSize());
        assertEquals(10, deck.dealCard().getValue());
        assertEquals(50, deck.getSize());
    }

    @Test
    @DisplayName("Shuffling cards on deck")
    void testDeckShuffle(){
        deck.generate();
        deck.shuffle();
        assertEquals(52, deck.getSize());
    }

    @Test
    @DisplayName("Generating cards on deck")
    void testDeckGeneration(){
        deck.generate();
        assertEquals(52, deck.getSize());
    }
}
