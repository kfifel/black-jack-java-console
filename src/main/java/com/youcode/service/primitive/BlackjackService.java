package com.youcode.service.primitive;

import com.youcode.consts.ResultRound;

import java.util.*;

import static com.youcode.consts.ResultRound.*;

public class BlackjackService {
    private int[][] deck            = new int[52][2];
    private int[][] playerHand      = new int[52][2];
    private int[][] dealerHand      = new int[52][2];
    private int[][] piochDeck       = new int[52][2];
    private int[][] restPiochDeck   = new int[52][2];
    private int[][] usedCards       = new int[52][2];

    private int playerScore ;
    private int dealerScore;
    private int playerBalance = 1_000;
    private int betAmount;

    public boolean placeBet(int amount) {
        if (amount > playerBalance) {
            return false;
        } else {
            betAmount = amount;
            playerBalance -= betAmount;
            return true;
        }
    }

    public void dealInitialHands() {

    }

    /* public int countPiochDeck() { return piochDeck.size();}

    public void playerHit() {
        checkPiochSize();
        playerHand.add(tirer_une_carte(piochDeck, 0));
    }

    public void playerStand() {
        int playerPoint = calculPoint(playerHand);
        int dealerPoint;
        do{
            addToDealerHand();
            dealerPoint = calculPoint(dealerHand);
        } while (dealerPoint < 17 &&  dealerPoint < playerPoint);
    }

    public int evaluateRoundResult() {
        int playerResult = calculPoint(playerHand);
        int dealerResult = calculPoint(dealerHand);

        if ((playerResult > dealerResult || dealerResult > 21 ) && playerResult <= 21 ) {
            updateSold(WIN);
            return 1;
        } else if(playerResult == dealerResult) {
            updateSold(DRAW);
            return 0;
        } else {
            if(dealerResult>21)
                updateSold(WIN);
            else
                updateSold(LOST);
            return -1;
        }
    } */

    void updateSold(ResultRound result) {
        if(result.equals(DRAW)) {
            playerBalance  += betAmount;
        } else if (result.equals(WIN)) {
            playerBalance  += betAmount * 2;
        }
    }






    public void initializeDeck() {
        deck = getDeckInitial(1, 1);
    }

    public int[][] getDeckInitial(int cardNumber, int symbolCardNumber) {
        int[][] cards = new int[52][2];
        int index = 0;

        for (int symboleNumber = symbolCardNumber; symboleNumber <= 4; symboleNumber++) {
            for (int cardValue = cardNumber; cardValue <= 13; cardValue++) {
                cards[index][0] = symboleNumber;
                cards[index][1] = cardValue;
                index++;
            }
            cardNumber = 1;
        }
        return cards;
    }

    public Map<List<Integer>, List<List<Integer>>> extraire_ieme_carte(List<List<Integer>> cards, int index) {
        return new HashMap<>(Map.of(cards.remove(index), cards));
    }
    public int[][][] piocher_n_cartes (int[][] cards, int piochIndex) {
        int[][]  cardsTemp = cards;
        int[][]  piochList = new int[52][2];
        int[][]  piochRest = new int[52][2];
        int [][][] result = new int[2][][];

        for (int i = 0; i < piochIndex; i++) {
            int[][][] resultDelete =  ArraysManipulation.deleteElement(cardsTemp, i);
            piochList[i] = resultDelete[0][0];
            piochRest[i] = resultDelete[0][0];

        }

        result[0] = piochList;
        result[1] = cardsTemp;

        return result;
    }


    public int calculPoint(List<List<Integer>> hand) {
        int totalPoints = 0;
        List<List<Integer>> localList = new ArrayList<>(hand);
        localList.sort(Comparator.comparing(list -> list.get(0), Comparator.reverseOrder()));

        for (List<Integer> card : hand) {
            if (card.get(0) >= 10) {
                totalPoints += 10;
            } else if(card.get(0) > 1) {
                totalPoints += card.get(0);
            } else {
                if(totalPoints + 11 <= 21 )
                    totalPoints += 11;
                else
                    totalPoints += 1;
            }
        }
        return totalPoints;
    }

    public void shuffleCards() {
        int i = 100;
        //while (i-- > 0)deck.add(tirer_une_carte(deck, getRandomInteger(52)));
    }
    List<Integer> tirer_une_carte(List<List<Integer>> cards, int index) {
        return cards.remove(index);
    }

    private int getRandomInteger(int rang) {
        Random random = new Random();
        return random.nextInt(rang);
    }


    public int getBetAmount() {
        return betAmount;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public int getPlayerBalance() {
        return playerBalance;
    }
}
