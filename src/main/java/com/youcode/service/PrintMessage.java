package com.youcode.service;

import static com.youcode.consts.Colors.*;


public class PrintMessage {

    public static void error(String s) {
        s = RED + s +  RESET;
        printMessage(s, TypeMessage.ERROR);
    }
    public static void success(String s) {
        s = GREEN + s +  RESET;
        printMessage(s,TypeMessage.SUCCESS);

    }
    public static void warning(String s) {
        s = YELLOW + s +  RESET;
        printMessage(s, TypeMessage.WARNING);

    }

    private static void printMessage(String s, TypeMessage typeMessage ) {
        System.out.println("<<<<< " +typeMessage.name() +": " + s + " >>>>>");
    }

    private enum TypeMessage{
        ERROR,
        WARNING,
        SUCCESS
    }
}
