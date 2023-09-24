package controller;

import com.youcode.controller.BlackjackController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackControllerTest {

    private BlackjackController blackjackController;

    @BeforeEach
    public void setUp() {
        blackjackController = new BlackjackController();
    }

    @Test
    public void testBetInput() {
        // Simulate user input for betting
        String input = "200\n"; // Input 200 dollars
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        blackjackController.setBetUser();
        int playerBalance = blackjackController.blackjackService.getPlayerBalance();
        int betAmount = blackjackController.blackjackService.getBetAmount();

        assertEquals(200, betAmount, "Bet amount should be set to 200.");
        assertEquals(800, playerBalance, "Player balance should be reduced by the bet amount.");
    }


    // Add more test cases for other methods in BlackjackController...
}
