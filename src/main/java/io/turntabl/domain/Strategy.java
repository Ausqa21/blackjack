package io.turntabl.domain;

import io.turntabl.Game;

public class Strategy {

    private static final String PLAYER_STATUS = "Total value of cards of Player %s is %d\n";

    public static void defaultStrategy(Player player, Deck deck) {

        if (player.getTotalCardValue() < 17) {
            System.out.printf(PLAYER_STATUS, player.getUsername(), player.getTotalCardValue());
            System.out.println("Calling HIT");
            Game.hit(deck, player);
        } else if (player.getTotalCardValue() >= 17) {
            System.out.printf(PLAYER_STATUS, player.getUsername(), player.getTotalCardValue());
            System.out.println("Calling STICK");
            Game.stick();
        } else if (player.getTotalCardValue() > 21) {
            System.out.printf(PLAYER_STATUS, player.getUsername(), player.getTotalCardValue());
            System.out.println("Calling BUST");
            Game.checkGameStatistics();
        }
    }
}
