package io.seoleir.micro.todoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer

public class TodoConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoConfigApplication.class, args);
    }

}
