package com.youcode;

import com.youcode.controller.BlackjackController;
import com.youcode.service.primitive.ArraysManipulation;

public class BlackjackApp {
    public static void main(String[] args) {
        BlackjackController app = new BlackjackController();

        //app.startGame();
        int [][] table = new int[4][2];
        for (int i = 0; i <4; i++) {
            for (int j = 0; j < 2; j++) {
                table[i][j] = i*j;
            }
        }

        int[][][] res = ArraysManipulation.deleteElement(table, 1);
        System.out.println("dd");
    }
}