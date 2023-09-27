package com.youcode;

import com.youcode.controller.BlackjackController;
import com.youcode.service.primitive.ArraysManipulation;
import com.youcode.service.primitive.BlackjackService;

public class BlackjackApp {
    public static void main(String[] args) {
        BlackjackController app = new BlackjackController();

        //app.startGame();
        int [][] table = new int[10][2];
        for (int i = 0; i <10; i++) {
                table[i][0] = i*i+i+1;
                table[i][1] = i*i+i+2;
        }
        System.out.println(table.length);
        ArraysManipulation.shuffle2D(table);
        ArraysManipulation.sort2D(table);


    }
}