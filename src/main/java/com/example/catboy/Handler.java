package com.example.catboy;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Handler {

    private Handler() {}

    public static String getAnswer(String input) {
        String answer;
        switch (input) {
            case (""):
                answer = "Hi! It's test bot. I know commands /ping and /catboy";
                break;
            case ("ping"):
                answer = pingHandler();
                break;
            case ("catboy"):
                answer = catboyHandler();
                break;
            default:
                answer = "";
                break;
        }
        return answer;
    }

    private static String pingHandler() {
        String serverAnswer = sendRequest("https://api.catboys.com/ping");
        try {
            return "Catboy says: " + parseAnswer(serverAnswer, "catboy_says");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String catboyHandler() {
        String serverAnswer = sendRequest("https://api.catboys.com/catboy");
        try {
            return parseAnswer(serverAnswer, "response");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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
