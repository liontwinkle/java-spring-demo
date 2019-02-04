package com.codesample.demo.service;

import com.codesample.demo.domain.UserEntity;
import com.codesample.demo.domain.UserTokenEntity;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String generateUserToken(UserEntity userEntity);
    void invalidateToken(String token);
    void invalidateAllTokenByUserId(UserEntity user);
    UserTokenEntity getToken(String token);
}
