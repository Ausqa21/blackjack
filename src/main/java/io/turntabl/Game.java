package io.turntabl;

import io.turntabl.domain.Deck;
import io.turntabl.domain.Player;
import io.turntabl.domain.Strategy;
import io.turntabl.enums.StrategyType;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    // Attributes
    static Scanner scanner = new Scanner(System.in);
    static List<Player> players = new ArrayList<>();
    static boolean winnerNotFound = true;
    static int sticksPerCurrentRound;


    public static void main(String[] args) {
        System.out.println("Welcome to the game BlackJack, let's get you started!");

        int playerTracker = 1;
        for (int index = 0 ; index < args.length ; index++){
            if (index % 2 == 1){
                    players.add(new Player(String.valueOf(playerTracker),StrategyType.fromString(args[index])));
                    playerTracker ++;
            }
        }

        // Print of alert for shuffle algo selection
        System.out.println("What shuffling algorithm would you like to be implemented?\n");
        System.out.println("Enter:\n1. Riffle suffle\n2. Pharoah/Faro shuffle\n3. Default shuffle");

        // Accept user input for shuffle algo selection
        scanner.nextInt();

        players.forEach(System.out::println);
        System.out.println("\nNOW, LET THE GAME BEGIN!!\n");

        // Create a deck
        System.out.println("Generating Deck...");
        Deck deck = new Deck();

        // Shuffle the deck
        System.out.println("Shuffling Deck...");
        deck.shuffle();

        // Deal cards to players
        players.forEach(player -> {
            System.out.printf("Dealing First Card for Player %s...%n", player.getUsername());
            player.addCard(deck.dealCard());
            System.out.printf("Dealing Second Card for Player %s...%n", player.getUsername());
            player.addCard(deck.dealCard());
        });

        // Print out every player's first 2 cards
        players.forEach(System.out::println);

        // Check for a winner or eliminated players
        checkGameStatistics();


        while (winnerNotFound) {
            // Set number of sticks for this round
            sticksPerCurrentRound = 0;

            players.forEach(player -> {
                System.out.printf("Player %s is implementing the %s strategy%n", player.getUsername(), player.getStrategy());
                if (player.getStrategy() == StrategyType.DEFAULT) {
                    String strategy = Strategy.defaultStrategy(player, deck);
                    System.out.printf("Default Strategy choose : %s%n",strategy);
                } else if (player.getStrategy() == StrategyType.ALWAYS_STICK) {
                    Strategy.alwaysStickStrategy();
                } else if (player.getStrategy() == StrategyType.ALWAYS_HIT) {
                    Strategy.alwaysHitStrategy(player, deck);
                }
            });

            if (players.size() == sticksPerCurrentRound) {
                System.out.println("Winning by all stick");
                Player winnerByStick = getWinnerByStick();
                System.out.printf("Congratulations Player %s! You've won the game!%n", winnerByStick.getUsername());
                break;
            }

            // Check player statistics
            checkGameStatistics();
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
            System.out.println("Winning at exactly 21");
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
                System.out.println("Winning by default");
                System.out.printf("Congratulations, Player %s! You've won the game!%n", activePlayers.get(0).getUsername());
            }

            players = activePlayers;
        }
    }
}
