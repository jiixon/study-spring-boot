package com.gachon.board.service;

import com.gachon.board.entity.UserEntity;
import com.gachon.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity findByEmail (String email) throws Exception {
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            return byEmail.get();
        }
        else {
            throw new Exception("Not found member");
        }
    }
}
