package io.seoleir.micro.todousers.controller;

import io.seoleir.micro.todoentity.entity.User;
import io.seoleir.micro.todousers.search.UserSearchValue;
import io.seoleir.micro.todousers.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user){

        if (user.getId() != null && user.getId() != 0){
            return new ResponseEntity("Redunant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getEmail() != null && user.getEmail().trim().length() == 0){
            return new ResponseEntity("Missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getPassword() == null || user.getPassword().trim().length() == 0){
            return new ResponseEntity("Missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getUsername() == null || user.getUsername().trim().length() == 0){
            return new ResponseEntity("Missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.add(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){

        if (user.getId() == null && user.getId() == 0){
            return new ResponseEntity("Redunant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getEmail() != null && user.getEmail().trim().length() == 0){
            return new ResponseEntity("Missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getPassword() == null || user.getPassword().trim().length() == 0){
            return new ResponseEntity("Missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getUsername() == null || user.getUsername().trim().length() == 0){
            return new ResponseEntity("Missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.add(user));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteByUserId(@PathVariable("id") Long userId){
        try {
            userService.deleteByUserId(userId);
        }catch (EmptyResultDataAccessException e){
            log.error(e.getMessage(), e);
            return new ResponseEntity("userId=" + userId + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity deleteByEmail(@PathVariable("email") String email){
        try {
            userService.deleteByUserEmail(email);
        }catch (EmptyResultDataAccessException e){
            log.error(e.getMessage(), e);
            return new ResponseEntity("email=" + email + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long userId) {

        User user;

        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
        try {
            user = userService.findById(userId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity<>("User with id:" + userId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {

        User user;

        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
        try {
            user = userService.findByEmail(email);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity<>("User with email:" + email + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody UserSearchValue userSearchValue, Pageable pageable){
        String email = userSearchValue.getEmail() != null ? userSearchValue.getEmail() : null;
        String username = userSearchValue.getUsername() != null ? userSearchValue.getUsername() : null;

        return ResponseEntity.ok(userService.finByParams(email, username, pageable));
    }
}
