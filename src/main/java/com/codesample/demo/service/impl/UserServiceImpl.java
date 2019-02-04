package com.codesample.demo.service.impl;

import com.codesample.demo.domain.UserCredentialEntity;
import com.codesample.demo.domain.UserEntity;
import com.codesample.demo.exceprion.AuthorizationException;
import com.codesample.demo.repository.UserCredentialRepository;
import com.codesample.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserCredentialRepository userCredentialsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${app.server-salt}")
    private String salt;

    public UserEntity getUserByLoginAndPass(String login, String pass) {
        UserCredentialEntity credentialsEntity = userCredentialsRepository.findByEmail(login)
                .orElseThrow(()->new AuthorizationException("User not found or wrong password"));
        UserEntity user = credentialsEntity.getUser();

        if(!passwordEncoder.matches(pass + salt, credentialsEntity.getPassword())) {
            throw new AuthorizationException("User not found or wrong password");
        }

        checkUserAllowedToLogin(user);

        return user;
    }

    private void checkUserAllowedToLogin(UserEntity userEntity) {
        if(!userEntity.getEnabled()) {
            throw new AuthorizationException("User disabled");
        }
    }
}
