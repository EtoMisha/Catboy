package catboy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "catboy")
public class CatboyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatboyApplication.class, args);
    }

}
