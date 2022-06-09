package com.example.catboy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(botconfig.getUsername(), botconfig.getToken()));
            LOGGER.info("Bot " + botconfig.getUsername() + " started");
        } catch (TelegramApiException e) {
            LOGGER.error("Error while starting bot: " + e);
        }
    }
}
