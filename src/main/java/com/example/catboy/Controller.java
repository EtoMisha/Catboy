package com.example.catboy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    static Handler handler = new Handler();

    @RequestMapping(value = "")
    public static String commandStart() {
        return handler.getAnswer("");
    }

    @RequestMapping(value = "ping")
    public static String commandPing() {
        return handler.getAnswer("ping");
    }

    @RequestMapping(value = "catboy")
    public static String commandCatboy() {
        return handler.getAnswer("catboy");
    }
}
