package io.turntabl.domain;

import static org.junit.jupiter.api.Assertions.*;

import io.turntabl.enums.CardDetail;
import io.turntabl.enums.StrategyType;
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
    @DisplayName("Testing Player username when set by constructor without strategy")
    void testGetPlayerUsername() {
        assertEquals("Alex", player.getUsername());
    }



    @Test
    @DisplayName("Testing Player Has no dealt cards at the start of project")
    void testGetDealtCards() {
        assertEquals(0, player.getDealtCards().size());
    }


    @Test
    @DisplayName("Testing Get Strategy for a default user")
    void testGetStrategy() {
        assertEquals(StrategyType.DEFAULT,player.getStrategy());
    }

    @Test
    @DisplayName("Testing add card")
    void testAddCard() {
        player.addCard(new Card(CardDetail.THREE, Suit.DIAMONDS));
        assertEquals(1, player.getDealtCards().size());
    }

    @Test
    @DisplayName("Testing player's total value for cards")
    void testGetTotalCardValue() {
        assertEquals(0, player.getTotalCardValue());
    }


}
