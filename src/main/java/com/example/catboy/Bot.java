package com.example.catboy;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "CatboySpring_bot";
    }

    @Override
    public String getBotToken() {
        return "5122391230:AAHNEX9WYLOJKqjvHYfEg-u_UhLs_N25-p8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String message = update.getMessage().getText().trim();

            String answer = makeAnswer(message);

            SendMessage send = new SendMessage();
            send.setChatId(chatId);
            send.setText(answer);
            try {
                execute(send);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String makeAnswer(String message) {
//        String serverAnswer;
//        String answer = "";

        switch (message) {
            case "/start":
                return Controller.commandStart();
            case "/ping":
                return Controller.commandPing();
            case "/catboy":
                return Controller.commandCatboy();
            default:
                return "Unknown command. Try /ping or /catboy";
        }
    }

}
