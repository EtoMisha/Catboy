package catboy.handler;

import catboy.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestHandler implements Handler {

    private final CommandService commandService;

    @Autowired
    public RestHandler(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public String getAnswer(String input) {
        String answer;
        switch (input) {
            case (""):
                answer = commandService.start();
                break;
            case ("ping"):
                answer = commandService.ping();
                break;
            case ("catboy"):
                answer = commandService.catBoy();
                break;
            default:
                answer = "";
                break;
        }
        return answer;
    }
}
