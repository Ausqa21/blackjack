package io.turntabl.domain;

import io.turntabl.Game;
import io.turntabl.enums.StrategyType;

public class Strategy {

    public static String defaultStrategy(Player player, Deck deck) {
        String resultingStrategy = "";
        if (player.getTotalCardValue() < 17) {
            Game.hit(deck, player);
            resultingStrategy = "Implementing Hit";
        } else if (player.getTotalCardValue() >= 17) {
            Game.stick();
            resultingStrategy = "Implementing Stick";
        }
        return resultingStrategy;
    }
    public static void alwaysStickStrategy() {
        Game.stick();
    }
    public static void alwaysHitStrategy(Player player, Deck deck) {
        Game.hit(deck, player);
    }
}
