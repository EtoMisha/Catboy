package com.example.catboy;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

public class Handler {

    private Handler() {}

    public static String getAnswer(String input) {
        String answer;
        switch (input) {
            case (""):
                answer = startHandler();
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

    public static String getBotAnswer(String input) {
        String answer;
        switch (input) {
            case ("/start"):
                answer = "Hi! It's test bot. I know commands /ping and /catboy";
                break;
            case ("/ping"):
                answer = pingHandler();
                break;
            case ("/catboy"):
                answer = catboyHandler();
                break;
            default:
                answer = "Unknown command. I know commands /ping and /catboy";
                break;
        }
        return answer;
    }

    private static String startHandler() {
        return "Hi! It's test bot. I know commands /ping and /catboy";
    }

    private static String pingHandler() {
        String serverAnswer = sendRequest("https://api.catboys.com/ping");
        if (serverAnswer == null) {
            return "Unknown server error";
        }
        try {
            return "Catboy says: " + parseAnswer(serverAnswer, "catboy_says");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String catboyHandler() {
        String serverAnswer = sendRequest("https://api.catboys.com/catboy");
        if (serverAnswer == null) {
            return "Unknown server error";
        }
        try {
            return parseAnswer(serverAnswer, "response");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String sendRequest(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static String parseAnswer(String serverAnswer, String field) throws ParseException {
        JSONObject jsonObject = new JSONObject(serverAnswer);
        return (String) jsonObject.get(field);
    }
}
