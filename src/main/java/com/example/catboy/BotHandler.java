package com.example.catboy;

public class BotHandler {

    public static String makeAnswer(String message) {

        switch (message) {
            case "/start":
                return Handler.getAnswer("");
            case "/ping":
                return Handler.getAnswer("ping");
            case "/catboy":
                return Handler.getAnswer("catboy");
            default:
                return "Unknown command. Try /ping or /catboy";
        }
    }
}
