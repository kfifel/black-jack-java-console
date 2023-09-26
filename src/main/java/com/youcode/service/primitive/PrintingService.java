package com.youcode.service.primitive;

import com.youcode.consts.Colors;

import java.util.List;

public class PrintingService {

    public static void printCard(int[][] cards) {
        String line1 = "", line2 = "", line3 = "", line4 = "", line5 = "";
        for (int[] card : cards) {
            int value = card[0];
            int symbole = card[1];

            StringBuilder suitSymbol = new StringBuilder();
            String cardValue;
            String cardColor = Colors.RESET;

            switch (value) {
                case 1:  cardValue = "A";  break;
                case 11: cardValue = "J"; break;
                case 12: cardValue = "Q"; break;
                case 13: cardValue = "K"; break;
                default: cardValue = String.valueOf(value);
            }
            switch (symbole){
                case 1: cardColor = Colors.RED;
                        suitSymbol.append(" ♥ "); break;

                case 2: suitSymbol.append(" ♣ "); break;

                case 3: cardColor = Colors.RED;
                        suitSymbol.append(" ♦ "); break;

                case 4: suitSymbol.append(" ♠ "); break;

                default: throw new IllegalStateException("the symbole should be 1, 2, 3 or 4");
            }
            if (cardValue.equals("10"))
                suitSymbol.deleteCharAt(2);
            line1 +=  cardColor + "+-------+   " + Colors.RESET;
            line2 +=  cardColor + "| " + cardValue + suitSymbol + "  |   " + Colors.RESET;
            line3 +=  cardColor + "|       |   " + Colors.RESET;
            line4 +=  cardColor + "|       |   " + Colors.RESET;
            line5 +=  cardColor + "+-------+   " + Colors.RESET;

        }
        System.out.println(  line1 + "\n"+
                line2 + "\n"+
                line3 + "\n"+
                line4 + "\n"+
                line5 + "\n"
        );
    }

}
