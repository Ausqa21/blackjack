package io.turntabl.domain;

import static org.junit.jupiter.api.Assertions.*;

import io.turntabl.enums.CardDetail;
import io.turntabl.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Card Test")
class CardTest {

    // Create test card
    Card card;

    @BeforeEach
    void setUp() {
        this.card = new Card(CardDetail.TWO, Suit.CLUBS);
    }

    @Test
    void testCardAttributes() {
        assertEquals("TWO", card.getName());
        assertEquals(2, card.getValue());
        assertEquals(Suit.CLUBS, card.getSuit());
    }
    @Test
    @DisplayName("Test Card Details Creation")
    void testCardCreationFromCardDetails() {
        this.card = new Card(CardDetail.TEN, Suit.CLUBS);
    }
}
