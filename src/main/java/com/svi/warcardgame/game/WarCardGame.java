
package com.svi.warcardgame.game;

import com.svi.warcardgame.enums.Ranks;
import com.svi.warcardgame.enums.Suits;
import com.svi.warcardgame.models.Card;
import com.svi.warcardgame.models.Player;
import com.svi.warcardgame.utils.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

import static com.svi.warcardgame.utils.ConsoleColors.RESET;

/*
* Handles the game rounds, findWinningIndex, round results, displayCollected Cards and remove eliminated players.
* */
public class WarCardGame {

    public Player playGame(List<Player> players, int totalCards) {
        List<Player> activePlayers = new ArrayList<>();


        for (Player player : players) {
            if (player.hasCards()) {
                activePlayers.add(player);
            }
        }

        int roundNumber = 1;

        while (activePlayers.size() > 1) {
            System.out.println("\n========== ROUND "
                    + roundNumber + " ==========");

            List<Card> tableCards = new ArrayList<>();
            List<Player> playersInRound = new ArrayList<>();

            playRound(activePlayers, playersInRound, tableCards);

            int winningIndex = findWinningIndex(tableCards);
            Card winningCard = tableCards.get(winningIndex);
            Player roundWinner = playersInRound.get(winningIndex);

            displayRoundResults(
                    playersInRound, tableCards, roundWinner, winningCard
            );

            displayCollectedCards(tableCards, winningCard);

            roundWinner.collectCards(tableCards, winningCard);

            removeEliminatedPlayers(activePlayers);

            System.out.println("\nPlayer "
                    + roundWinner.getPlayerNo()
                    + "'s deck after Round " + roundNumber + ":");

            roundWinner.displayDeck();

            System.out.println("\nActive players remaining: "
                    + activePlayers.size());

            if (roundWinner.hasAllCards(totalCards)) {
                System.out.println("\n========== GAME OVER ==========");
                System.out.println("Player "
                        + roundWinner.getPlayerNo()
                        + " wins the game!");

                return roundWinner;
            }


            roundNumber++;
        }

        // Return the last remaining player, if there is one.
        if (activePlayers.size() == 1) {
            return activePlayers.get(0);
        }

        return null;
    }

    private void playRound(
            List<Player> activePlayers,
            List<Player> playersInRound,
            List<Card> tableCards) {

        for (Player player : activePlayers) {
            Card playedCard = player.playTopCard();

            playersInRound.add(player);
            tableCards.add(playedCard);
        }
    }

    private int findWinningIndex(List<Card> tableCards){
        int winningIndex = 0;

        for(int index = 1; index < tableCards.size(); index++){
            if((tableCards.get(index).compareTo(tableCards.get(winningIndex))) > 0){
                winningIndex = index;
            }
        }
        return winningIndex;
    }

    private void displayRoundResults(
            List<Player> playersInRound,
            List<Card> tableCards,
            Player roundWinner,
            Card winningCard) {

        System.out.println("\nCards played:");

        for (int index = 0; index < tableCards.size(); index++) {
            Player player = playersInRound.get(index);
            Card playedCard = tableCards.get(index);

            if (playedCard == winningCard) {
                System.out.println(ConsoleColors.GOLD + "★ WINNER: Player "
                        + player.getPlayerNo()
                        + ": " + playedCard.toDisplayString()
                        + " ★" + ConsoleColors.RESET);
            } else {
                System.out.println("Player "
                        + player.getPlayerNo()
                        + ": " + playedCard.toDisplayString());
            }
        }

        System.out.println("\nRound winner: Player "
                + roundWinner.getPlayerNo());
    }

    private void displayCollectedCards(
            List<Card> tableCards,
            Card winningCard) {

        System.out.println("\nCards collected:");
        System.out.println("Winning card: " + winningCard);

        int cardNumber = 2;

        for (Card card : tableCards) {
            if (card != winningCard) {
                System.out.println(cardNumber + ". " + card);
                cardNumber++;
            }
        }
    }

    private void removeEliminatedPlayers(List<Player> activePlayers) {
        for (int index = activePlayers.size() - 1; index >= 0; index--) {
            Player player = activePlayers.get(index);

            if (!player.hasCards()) {
                System.out.println("Player "
                        + player.getPlayerNo()
                        + " has no cards left and is eliminated.");

                activePlayers.remove(index);
            }
        }
    }
}
