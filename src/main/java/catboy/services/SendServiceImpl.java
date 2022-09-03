package catboy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class SendServiceImpl implements SendService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendServiceImpl.class);

    @Override
    public String sendRequest(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            LOGGER.info("Sending request to {}", url);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
            LOGGER.info("Response ok");
            return response.getBody();
        } catch (Exception ex) {
            LOGGER.error("Error when sending request: " + ex);
        }
        return null;
    }

}
