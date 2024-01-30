package io.seoleir.micro.todoutils.resttemplate;

import io.seoleir.micro.todoentity.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class UserRestBuilder {

    private static final String baseUrl = "http://localhost:8765/todo-main/users";

    public boolean userExists(Long userId) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User> response;
        try {
//            response = restTemplate.getForEntity(baseUrl + "/" + userId, User.class);
            response = restTemplate.exchange(baseUrl + "/{id}", HttpMethod.GET, null, User.class, userId);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
