package com.example.catboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
@SpringBootApplication
public class CatboyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatboyApplication.class, args);
    }

}
*/
public class CatboyApplication {
    public static void main(String[] args) {
    ApiContextInitializer.init();
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    try {
        telegramBotsApi.registerBot(Bot.getBot());
    } catch (TelegramApiRequestException e) {
        e.printStackTrace();
        }
    }
}
