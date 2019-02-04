package com.codesample.demo.service;

import com.codesample.demo.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity getUserByLoginAndPass(String login, String pass);
}
