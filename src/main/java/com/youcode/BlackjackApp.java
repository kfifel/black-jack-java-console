package com.youcode;

import com.youcode.controller.BlackjackController;
import com.youcode.service.primitive.ArraysManipulation;
import com.youcode.service.primitive.BlackjackService;

public class BlackjackApp {
    public static void main(String[] args) {
        BlackjackController app = new BlackjackController();

        app.startGame();

    }
}