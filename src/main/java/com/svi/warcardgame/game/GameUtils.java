package com.svi.warcardgame.game;

import com.svi.warcardgame.models.Card;
import com.svi.warcardgame.models.Player;
import com.svi.warcardgame.models.Deck;

import java.util.ArrayList;
import java.util.List;

public class GameUtils {

    public Player playGame(List<Player> players, int totalCards){
        List<Player> activePlayers = new ArrayList<>();

        for (Player player : players){
            activePlayers.add(player);
        }
        int roundNumber = 1;

        while(activePlayers.size() > 1){
            System.out.println("\n========== ROUND "
                    + roundNumber + " ==========");

            List <Card> tableCards = new ArrayList<>();
            List <Player> playersInRound = new ArrayList<>();

            playRound(activePlayers, playersInRound, tableCards);
            Player roundWinner = findRoundWinner(
                    playersInRound, tableCards, winningCard
            );

            displayRoundResults(
                    playersInRound, tableCards, roundWinner, winningCard
            );

            displayCollectedCards(tableCards, winningCard);

            roundWinner.collecCards(tableCards, winningCard);


            removeEliminatedPlayers(activePlayers);

            System.out.println("\nPlayer "
                    + roundWinner.getPlayerNo()
                    + "'s deck after Round " + roundNumber + ":");

            roundWinner.displayDeck();

            System.out.println("/nActive Players Remaining: " + activePlayers.size());

            if(roundWinner.hasAllCards(totalCards)){
                System.out.println("\n========== GAME OVER ==========");
                System.out.println("Player "
                        + roundWinner.getPlayerNo()
                        + " wins the game!");

                return roundWinner;

            }
            roundNumber ++;
        }
        if(activePlayers.size() == 1){
            return activePlayers.get(0);
        }

        return null;
    }

    private void playRound(
            List<Player> activePlayers,
            List <Player> playersInRound,
            List<Card> tableCards
    ){
        for (Player player : activePlayers){
            Card playedCard = player.playTopCard();

            playersInRound.add(player);
            tableCards.add(playedCard);
        }
    }

    private Card findWinningCard(List<Card> tableCards){
        Card winningCard = tableCards.get(0);

        for(int i = 1; i < tableCards.size(); i++){
            Card currentCard = tableCards.get(i);

            if (Card.compareCard(currentCard, winningCard) > 0){
                winningCard = currentCard;
            }
        }

        return winningCard;
    }

    private Player findRoundWinner(
            List<Player> playersInRound,
            List<Card> tableCards,
            Card winningCard
    ){
        for(int i = 0; i < tableCards.size(); i++){

        }
    }

}
