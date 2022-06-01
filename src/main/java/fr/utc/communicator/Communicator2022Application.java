package fr.utc.communicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Communicator2022Application {

    public static void main(String[] args) {
        SpringApplication.run(Communicator2022Application.class, args);
    }
}
