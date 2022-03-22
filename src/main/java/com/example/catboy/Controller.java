package com.example.catboy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    @RequestMapping("")
    public String ping() {
        return ("Hello");
    }
    @RequestMapping("/ping")
    public String ping() {
        return sendRequest("https://api.catboys.com/ping");
    }
    @RequestMapping("/catboy")
    public String catboy() {
        return sendRequest("https://api.catboys.com/catboy");
    }
    private String sendRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
