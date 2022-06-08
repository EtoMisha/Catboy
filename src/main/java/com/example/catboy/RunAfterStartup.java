package com.example.catboy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class RunAfterStartup {

    @Autowired
    private BotConfig botconfig;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("USERNAME" + botconfig.getUsername());
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(botconfig.getUsername(), botconfig.getToken()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
