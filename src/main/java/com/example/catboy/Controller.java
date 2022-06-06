package com.example.catboy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "")
    public static String commandStart() {
        return Handler.getAnswer("");
    }

    @RequestMapping(value = "ping")
    public static String commandPing() {
        return Handler.getAnswer("ping");
    }

    @RequestMapping(value = "catboy")
    public static String commandCatboy() {
        return Handler.getAnswer("catboy");
    }
}
