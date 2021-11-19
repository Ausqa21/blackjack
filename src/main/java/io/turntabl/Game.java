package io.turntabl;

import io.turntabl.domain.Deck;
import io.turntabl.domain.Player;
import io.turntabl.domain.Strategy;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    // Attributes
    static int numberOfPlayers = 3;
    static List<Player> players = new ArrayList<>();
    static boolean winnerNotFound = true;
    static int sticksPerCurrentRound ;


    public static void main(String[] args) {
        int playerCount = 1;
        while (playerCount <= numberOfPlayers) {
            players.add(new Player(String.valueOf(playerCount)));
            playerCount++;
        }

        System.out.println("Now, let the game begin!\n");

        // Create a deck
        Deck deck = new Deck();

        // Shuffle the deck
        deck.shuffle();

        // Deal cards to players
        players.forEach(player -> {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        });

        checkGameStatistics();

        while (winnerNotFound) {
            // Check player statistics
            checkGameStatistics();

            // Set number of sticks for this round
            sticksPerCurrentRound = 0;

            players.forEach(player -> {
                if (Objects.equals(player.getStrategy(), "DEFAULT")) {
                    System.out.printf("Player %s is implementing the DEFAULT strategy%n", player.getUsername());
                    Strategy.defaultStrategy(player, deck);
                }

                checkGameStatistics();
            });

            if (players.size() == sticksPerCurrentRound) {
                Player winnerByStick = getWinnerByStick();
                System.out.printf("Congratulations Player %s! You've won the game!%n", winnerByStick.getUsername());
                break;
            }
        }
    }

    public static void hit(Deck deck, Player player) {
        player.addCard(deck.dealCard());
    }

    public static void stick() {
        sticksPerCurrentRound++;
    }

    public static Player getWinnerByStick() {
        return players.stream()
                .max(Comparator.comparingInt(Player::getTotalCardValue))
                .orElseThrow(RuntimeException::new);
    }

    static Map<Boolean, List<Player>> getPlayerStatistics(){
        return  players.stream().collect(Collectors.partitioningBy(player -> player.getTotalCardValue() > 21));
    }
    static Optional<Player> getWinningPlayer(){
        return  players.stream().filter(player -> player.getTotalCardValue() == 21).findFirst();
    }

    public static void checkGameStatistics(){
        if (getWinningPlayer().isPresent()) {
            winnerNotFound = false;
            System.out.println(getWinningPlayer().get().getDealtCards());
            System.out.printf("Congratulations, Player %s! You've won the game!%n", getWinningPlayer().get().getUsername());
        } else {
            Map<Boolean, List<Player>> partitionedItems = getPlayerStatistics();
            List<Player> eliminatedPlayers = partitionedItems.get(true);
            eliminatedPlayers.forEach(player -> {
                System.out.printf("Player %s total card value: %s%n", player.getUsername(), player.getTotalCardValue());
                System.out.printf("Player %s has been eliminated%n", player.getUsername());
                System.out.printf("Player %s's dealt cards: %s%n", player.getUsername(), player.getDealtCards());
            });

            List<Player> activePlayers = partitionedItems.get(false);

            if (activePlayers.size() == 1) {
                winnerNotFound = false;
                System.out.printf("Congratulations, Player %s! You've won the game!%n", activePlayers.get(0).getUsername());
            }

            players = activePlayers;
        }
    }
}
