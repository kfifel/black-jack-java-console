package com.youcode.service;

import com.youcode.consts.Colors;

import java.io.Console;

public class PrintingService {

    /**
     * this method print the card given
     * @param value
     * @param symbole
     * @return String
     */
    public static String printCard(int value, int symbole) {
        String suitSymbol;
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
                    suitSymbol = " ♥"; break;

            case 2: suitSymbol = " ♣"; break;

            case 3: cardColor = Colors.RED;
                    suitSymbol = " ♦"; break;

            case 4: suitSymbol = " ♠"; break;

            default: throw new IllegalStateException("the symbole should be 1, 2, 3 or 4");
        }

        return  cardColor +
                "+-------+\n" +
                "| " + cardValue + suitSymbol + "   |\n" +
                "|       |\n" +
                "|       |\n" +
                "+-------+\n" + Colors.RESET;
    }

}
