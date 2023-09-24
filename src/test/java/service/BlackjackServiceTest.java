package service;

import com.youcode.service.BlackjackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackServiceTest {

    private BlackjackService blackjackService;

    @BeforeEach
    public void setUp() {
        blackjackService = new BlackjackService();
    }

    @Test
    public void testInitialPlayerBalance() {
        int playerBalance = blackjackService.getPlayerBalance();
        assertEquals(1000, playerBalance, "Initial player balance should be 1000 dollars.");
    }

    @Test
    public void testPlacingValidBet() {
        boolean result = blackjackService.placeBet(100);
        assertTrue(result, "Should be able to place a valid bet.");
        int playerBalance = blackjackService.getPlayerBalance();
        assertEquals(900, playerBalance, "Player balance should decrease by the bet amount.");
    }

    @Test
    public void testPlacingInvalidBet() {
        boolean result = blackjackService.placeBet(1100); // Attempt to bet more than the balance.
        assertFalse(result, "Should not be able to place an invalid bet.");
    }

    @Test
    public void testCalculatingPoints() {
        blackjackService.getPlayerHand().clear();
        blackjackService.getPlayerHand().add(List.of(10, 1)); // 10 points
        blackjackService.getPlayerHand().add(List.of(5, 2));  // 5 points
        int playerPoints = blackjackService.calculPoint(blackjackService.getPlayerHand());
        assertEquals(15, playerPoints, "Calculated player points should be 15.");
    }

}
