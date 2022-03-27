package com.example.catboy;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@Component
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
            String serverAnswer;
            String answer = "";
            if (message.equals("/ping")) {
                serverAnswer = sendRequest("https://api.catboys.com/ping");
                try {
                    answer = "Catboy says: " + parseAnswer(serverAnswer, "catboy_says");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (message.equals("/catboy")) {
                serverAnswer = sendRequest("https://api.catboys.com/catboy");
                try {
                    answer = parseAnswer(serverAnswer, "response");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else {
                answer = "Unknown command. Type /ping or /catboy";
            }

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

    private String sendRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    private String parseAnswer(String answer, String field) throws ParseException {
        JSONObject jsonObject = new JSONObject(answer);
        return (String) jsonObject.get(field);
    }
}
