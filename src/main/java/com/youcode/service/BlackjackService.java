package com.youcode.service;

import com.youcode.consts.ResultRound;

import java.util.*;

import static com.youcode.consts.ResultRound.*;

public class BlackjackService {
    private final List<List<Integer>> deck = new ArrayList<>();
    private final List<List<Integer>> playerHand  = new ArrayList<>();
    private final List<List<Integer>> dealerHand  = new ArrayList<>();
    private List<List<Integer>> piochDeck  = new ArrayList<>();
    private List<List<Integer>> restPiochDeck  = new ArrayList<>();
    private final List<List<Integer>> usedCards  = new ArrayList<>();

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

    public void playerHit() {
        checkPiochSize();
        playerHand.add(tirer_une_carte(piochDeck, 0));
    }

    public void playerStand() {
       do{
           addToDealerHand();
        } while (calculPoint(dealerHand) < 17 &&  calculPoint(dealerHand) < calculPoint(playerHand));
    }

    public int evaluateRoundResult() {
        int playerResult = calculPoint(playerHand);
        int dealerResult = calculPoint(dealerHand);

        if (playerResult > dealerResult && playerResult <= 21) {
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
    }

    void updateSold(ResultRound result) {
        if(result.equals(DRAW)) {
            this.betAmount += this.betAmount;
        } else if (result.equals(WIN)) {
            this.betAmount += this.betAmount * 2;
        }
    }

    public void resetGame() {
        deck.clear();
        playerHand.clear();
        dealerHand.clear();
        playerScore = 0;
        dealerScore = 0;
        betAmount   = 0;
    }

    public void setEnvironment() {
        resetGame();
        initializeDeck();
        shuffleCards();
        initializePioch();
    }

    public void setPlayerAndDealersDeckHand(){
        playerHand.add (tirer_une_carte(piochDeck, 0));
        playerHand.add (tirer_une_carte(piochDeck, 0));

        dealerHand.add(tirer_une_carte(piochDeck, 0));
    }

    public void nextRound() {
        usedCards.addAll(dealerHand);
        usedCards.addAll(playerHand);

        dealerHand.clear();
        playerHand.clear();
        betAmount = 0;

        setPlayerAndDealersDeckHand();

    }

    private void checkPiochSize() {
        if(piochDeck.isEmpty()) {
            deck.clear();
            deck.addAll(restPiochDeck);
            deck.addAll(piochDeck);
            deck.addAll(usedCards);

            restPiochDeck.clear();
            piochDeck.clear();
            usedCards.clear();

            initializePioch(); // create a new pioch of the deck cards;
        }
    }

    public void addToDealerHand() {
        checkPiochSize();
        dealerHand.add(tirer_une_carte(piochDeck, 0));
    }

    public void initializePioch() {
        Map<List<List<Integer>>, List<List<Integer>>> res = this.piocher_n_cartes(deck, getRandomInteger(10)+20);

        for (Map.Entry<List<List<Integer>>, List<List<Integer>>> entry : res.entrySet()) {
            this.piochDeck = entry.getKey();
            this.restPiochDeck = entry.getValue();
            break;
        }
    }

    public void initializeDeck() {
        deck.addAll(getDeckInitial(1, 1));
    }

    public List<List<Integer>> getDeckInitial(int cardNumber, int symbolCardNumber) {
        List<List<Integer>> cards = new ArrayList<>();
        for (int i = symbolCardNumber; i <= 4; i++ ){
            for (int j = cardNumber; j <= 13; j++)
                cards.add(List.of(j, i));
            cardNumber = 1;
        }
        return cards;
    }

    public Map<List<Integer>, List<List<Integer>>> extraire_ieme_carte(List<List<Integer>> cards, int index) {
        return new HashMap<>(Map.of(cards.remove(index), cards));
    }
    public Map<List<List<Integer>>, List<List<Integer>>> piocher_n_cartes (List<List<Integer>> cards, int piochIndex) {
        List<List<Integer>> cardsTemp = new ArrayList<>(cards);
        List<List<Integer>> piochList = new ArrayList<>();

        for (int i = 0; i < piochIndex; i++){
            piochList.add(cardsTemp.remove(0));
        }
        return new HashMap<>(Map.of(piochList, cardsTemp));
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
        while (i-- > 0)
            deck.add(
                    tirer_une_carte(deck, getRandomInteger(52)));
    }
    List<Integer> tirer_une_carte(List<List<Integer>> cards, int index) {
        return cards.remove(index);
    }

    private int getRandomInteger(int rang) {
        Random random = new Random();
        return random.nextInt(rang);
    }

    public List<List<Integer>> getPlayerHand() {
        return playerHand;
    }

    public List<List<Integer>> getDealerHand() {
        return dealerHand;
    }

    public int getPlayerScore() {
        return playerScore;
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
