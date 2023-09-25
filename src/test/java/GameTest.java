import com.youcode.service.BlackjackService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class GameTest {

    private BlackjackService blackjackService;
    @BeforeEach
    public void setUp() {
        blackjackService = new BlackjackService();
    }

    @Test
    public void testPlayerWins() {
        // Set up player and dealer hands for this scenario
        blackjackService.getPlayerHand().clear();
        blackjackService.getPlayerHand().add(Arrays.asList(10, 2)); // Player has a score of 12
        blackjackService.getDealerHand().clear();
        blackjackService.getDealerHand().add(Arrays.asList(9, 3)); // Dealer has a score of 12

        int result = blackjackService.evaluateRoundResult();
        int playerBalance = blackjackService.getPlayerBalance();

        assertEquals(1, result, "Player should win.");
        // Make assertions about the player's balance after the win (e.g., balance increased).
    }

    @Test
    public void testPlayerWinsDealerBusts() {
        // Set up player and dealer hands for this scenario
        blackjackService.getPlayerHand().clear();
        blackjackService.getPlayerHand().add(Arrays.asList(10, 2)); // Player has a score of 12
        blackjackService.getDealerHand().clear();
        blackjackService.getDealerHand().add(Arrays.asList(10, 5)); // Dealer has a score of 15

        int result = blackjackService.evaluateRoundResult();
        int playerBalance = blackjackService.getPlayerBalance();

        assertEquals(1, result, "Player should win when dealer busts.");
        // Make assertions about the player's balance after the win (e.g., balance increased).
    }

    @Test
    public void testPlayerLoses() {
        // Set up player and dealer hands for this scenario
        blackjackService.getPlayerHand().clear();
        blackjackService.getPlayerHand().add(Arrays.asList(10, 4)); // Player has a score of 14
        blackjackService.getDealerHand().clear();
        blackjackService.getDealerHand().add(Arrays.asList(10, 3)); // Dealer has a score of 13

        int result = blackjackService.evaluateRoundResult();
        int playerBalance = blackjackService.getPlayerBalance();

        assertEquals(-1, result, "Player should lose.");
        // Make assertions about the player's balance after the loss (e.g., balance decreased).
    }

}
