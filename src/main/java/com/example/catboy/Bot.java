package com.example.catboy;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

//    private final BotConfig botconfig = new BotConfig();

    @Override
    public String getBotUsername() {
        return "CatboySpring_bot";//botconfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return "5122391230:AAHNEX9WYLOJKqjvHYfEg-u_UhLs_N25-p8";//botconfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String message = update.getMessage().getText().trim();

            String answer = Handler.getBotAnswer(message);

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
}
