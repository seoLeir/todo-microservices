package io.seoleir.micro.todousers.service;

import io.seoleir.micro.todoentity.entity.User;
import io.seoleir.micro.todousers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User add(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User update(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void deleteByUserId(Long id){
        userRepository.deleteById(id);
    }

    public void deleteByUserEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public Page<User> finByParams(String email, String username, Pageable pageable){
        return userRepository.findByParams(email, username, pageable);
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }



}
