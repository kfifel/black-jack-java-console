package com.youcode.controller;

import com.youcode.consts.Colors;
import com.youcode.service.BlackjackService;
import com.youcode.service.PrintMessage;
import com.youcode.service.PrintingService;

import java.util.List;
import java.util.Scanner;

public class BlackjackController {
    public final BlackjackService blackjackService = new BlackjackService();
    private final Scanner scanner;

    public BlackjackController() {
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        blackjackService.setEnvironment();
        blackjackService.setPlayerAndDealersDeckHand();

        startPlaying();
    }

    private void startPlaying() {
        int input;
        boolean roundIsFinished = false;
        setBetUser();
        do {
            List<List<Integer>> playerHand = blackjackService.getPlayerHand();
            int playerPoint = blackjackService.calculPoint(playerHand);

            showCards(playerPoint, playerHand);
            System.out.println("\nPioch Deck: "+blackjackService.countPiochDeck());
            if ( playerPoint < 21 ) {
                System.out.println("\n1: Hit \t\t 2~: Stand");
                input = scanner.nextInt();
                if(input == 1)
                    blackjackService.playerHit();
                else {
                    blackjackService.playerStand();
                    showCards(playerPoint, playerHand);
                    roundIsFinished = true;
                }
            } else
                break;
        }while(!roundIsFinished);

        evaluateRound();

        if ( blackjackService.getPlayerBalance() > 0 ) {
            System.out.println("would you play again Y/N");
            scanner.nextLine();
            String playerChose = scanner.nextLine();

            boolean playAgain = playerChose.equalsIgnoreCase("Y");

           if (playAgain){
               blackjackService.nextRound();
               startPlaying();
           }
        }

        System.out.println("your sold is not enought to play other games !!");
        System.out.println("Thanks for playing ...");
        System.out.println(Colors.RED + "Good bay!" + Colors.RESET);

    }

    void showCards(int playerPoint, List<List<Integer>> playerHand ) {
        List<List<Integer>> dealerHand = blackjackService.getDealerHand();
        int dealerPoint = blackjackService.calculPoint(dealerHand);

        System.out.println("Dealer point:" + dealerPoint);
        PrintingService.printCard(dealerHand);

        System.out.println("---------------------------------------------\n");

        PrintingService.printCard(playerHand);
        System.out.println("Player point:" + playerPoint);
    }

    private void evaluateRound() {

        int roundResult = blackjackService.evaluateRoundResult();
        if(roundResult == 1)
            PrintMessage.success("You win :)  !!");
        else if(roundResult == 0)
            PrintMessage.warning("Game is Draw :|  !!");
        else
            PrintMessage.warning("You lost :(  !!");
    }

    public void setBetUser() {
        int amount;
        boolean validInput = false;

        do {
            System.out.printf("Your balance: %d $\n", blackjackService.getPlayerBalance());
            System.out.print("Enter the amount you want to bet: ");

            try {
                amount = scanner.nextInt();
                if (amount <= 0 || amount > blackjackService.getPlayerBalance()) {
                    PrintMessage.error("Invalid bet amount. Please enter a valid amount.");
                } else {
                    validInput = true;
                    blackjackService.placeBet(amount);
                }
            } catch (Exception e) {
                PrintMessage.error("Invalid input. Please enter a valid positive integer.");
                scanner.nextLine();
            }
        } while (!validInput);
    }

}
