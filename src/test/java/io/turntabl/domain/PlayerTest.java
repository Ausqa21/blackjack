package io.turntabl.domain;

import static org.junit.jupiter.api.Assertions.*;

import io.turntabl.enums.CardDetail;
import io.turntabl.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Player Test")
class PlayerTest {

    // Create test player
    Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player("Alex");
    }

    @Test
    void testPlayerAttributes() {
        assertEquals("Alex", player.getUsername());
        assertEquals(0, player.getDealtCards().size());
    }

    @Test
    void testCardCanBeAddedToList() {
        player.addCard(new Card(CardDetail.THREE, Suit.DIAMONDS));
        assertEquals(1, player.getDealtCards().size());
    }

    @Test
    void testGetTotalValueOfPlayerCards() {
        assertEquals(0, player.getTotalCardValue());
    }
}
