package io.turntabl;

import io.turntabl.domain.Deck;
import io.turntabl.domain.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    // Attributes
    static int numberOfPlayers = 3;
    static List<Player> players = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static boolean winnerNotFound = true;
    static int sticksPerCurrentRound ;


    public static void main(String[] args) {
        int playerCount = 1;
        while (playerCount <= numberOfPlayers) {
            System.out.printf("Player %s, enter your username: ", playerCount);
            String username = scanner.nextLine();
            players.add(new Player(username));
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
                System.out.printf("Player %s, wanna HIT or STICK? Enter \n1: HIT\n2: STICK\n3: SHOW CARDS\n", player.getUsername());
                int input = scanner.nextInt();

                if (input == 3) {
                    System.out.println(player.getDealtCards());
                    System.out.printf("Player %s, wanna HIT or STICK? Enter \n1: HIT\n2: STICK\n", player.getUsername());
                    input = scanner.nextInt();
                }

                if (input == 1) {
                    player.addCard(deck.dealCard());
                } else if (input == 2) {
                    sticksPerCurrentRound++;
                }
                checkGameStatistics();
            });

        }
    }

    static Map<Boolean, List<Player>> getPlayerStatistics(){
        return  players.stream().collect(Collectors.partitioningBy(player -> player.getTotalCardValue() > 21));
    }
    static Optional<Player> getWinningPlayer(){
        return  players.stream().filter(player -> player.getTotalCardValue() == 21).findFirst();
    }

    static void checkGameStatistics(){
        if (getWinningPlayer().isPresent()) {
            winnerNotFound = false;
            System.out.println(getWinningPlayer().get().getDealtCards());
            System.out.printf("Congratulations, Player %s! You've won the game!%n", getWinningPlayer().get().getUsername());
        } else {
            Map<Boolean, List<Player>> partitionedItems = getPlayerStatistics();
            List<Player> eliminatedPlayers = partitionedItems.get(true);
            eliminatedPlayers.forEach(player -> {
                System.out.println(player.getDealtCards());
                System.out.printf("Player %s has been eliminated%n", player.getUsername());
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
