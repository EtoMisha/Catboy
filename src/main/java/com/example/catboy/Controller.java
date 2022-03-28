package com.example.catboy;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @RequestMapping("")
    public static String commandStart() {
        return "Hi! It's test bot. I know commands /ping and /catboy";
    }

    @RequestMapping("/ping")
    public static String commandPing() {
        String serverAnswer = sendRequest("https://api.catboys.com/ping");
        try {
            return "Catboy says: " + parseAnswer(serverAnswer, "catboy_says");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Error :(";
    }

    @RequestMapping("/catboy")
    public static String commandCatboy() {
        String serverAnswer = sendRequest("https://api.catboys.com/catboy");
        try {
            return parseAnswer(serverAnswer, "response");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Error :(";
    }

    private static String sendRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    private static String parseAnswer(String serverAnswer, String field) throws ParseException {
        JSONObject jsonObject = new JSONObject(serverAnswer);
        return (String) jsonObject.get(field);
    }
}
