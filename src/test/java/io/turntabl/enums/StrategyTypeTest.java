package io.turntabl.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Strategy Test")
class StrategyTypeTest {

    @Test
    @DisplayName("Testing Strategy Type Generation from input string")
    void testFromString() {
        StrategyType strategy = StrategyType.fromString("always-stick");
        assertEquals(StrategyType.ALWAYS_STICK, strategy);
    }
    @Test
    @DisplayName("Testing Strategy Type Generation from input string that does not exist ")
    void test2FromString() {
        StrategyType strategy = StrategyType.fromString("perumtation-biaa");
        assertEquals(StrategyType.DEFAULT, strategy);
    }
}