package io.seoleir.micro.todousers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDiscoveryClient
@EntityScan(basePackages = {"io.seoleir.micro.todoentity.entity"})
@SpringBootApplication(scanBasePackages = {"io.seoleir.micro.todoentity.entity", "io.seoleir.micro.todousers", "io.seoleir.micro.todoutils.aop"})
@EnableAspectJAutoProxy
@RefreshScope
public class TodoUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoUsersApplication.class, args);
    }

}
