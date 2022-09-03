package catboy.controller;

import catboy.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Handler handler;

    @Autowired
    @Qualifier("restHandler")
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @GetMapping(value = "")
    public String commandStart() {
        return handler.getAnswer("");
    }

    @GetMapping(value = "/ping")
    public String commandPing() {
        return handler.getAnswer("ping");
    }

    @GetMapping(value = "catboy")
    public String commandCatboy() {
        return handler.getAnswer("catboy");
    }
}
