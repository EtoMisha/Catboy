package catboy.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandServiceImpl implements CommandService {

    private final SendService sendService;

    @Autowired
    public CommandServiceImpl(SendService sendService) {
        this.sendService = sendService;
    }

    @Override
    public String start() {
        return "Hi! It's test bot. I know commands /ping and /catboy";
    }

    @Override
    public String ping() {
        String serverAnswer = sendService.sendRequest("https://api.catboys.com/ping");

        if (serverAnswer == null) {
            return "Unknown server error";
        }

        try {
            JSONObject jsonObject = new JSONObject(serverAnswer);
            return "Catboy says: " + jsonObject.get("catboy_says");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String catBoy() {
        String serverAnswer = sendService.sendRequest("https://api.catboys.com/catboy");

        if (serverAnswer == null) {
            return "Unknown server error";
        }

        try {
            JSONObject jsonObject = new JSONObject(serverAnswer);
            return jsonObject.get("response").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
