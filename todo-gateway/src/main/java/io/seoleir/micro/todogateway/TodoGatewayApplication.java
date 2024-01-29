package io.seoleir.micro.todogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class TodoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoGatewayApplication.class, args);
    }

}
