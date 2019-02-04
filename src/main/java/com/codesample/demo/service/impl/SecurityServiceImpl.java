package com.codesample.demo.service.impl;

import com.codesample.demo.domain.UserEntity;
import com.codesample.demo.domain.UserTokenEntity;
import com.codesample.demo.repository.UserTokenRepository;
import com.codesample.demo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.codesample.demo.constant.ApplicationConstants.TOKEN_VALID_IN_MILLIS;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserTokenRepository userTokenRepository;

    @Override
    public UserTokenEntity getToken(String token) {
        return userTokenRepository.findByToken(token).orElse(null);
    }

    @Override
    public void invalidateToken(String token) {
        userTokenRepository.findByToken(token).ifPresent(t->userTokenRepository.delete(t));
    }

    @Override
    public void invalidateAllTokenByUserId(UserEntity user) {
        userTokenRepository.deleteAllByUser(user);
    }

    @Override
    public String generateUserToken(UserEntity userEntity) {
        LocalDateTime localExpire = LocalDateTime.now().plus(TOKEN_VALID_IN_MILLIS, ChronoUnit.MILLIS);

        String token = UUID.randomUUID().toString();

        UserTokenEntity userTokenEntity = new UserTokenEntity();
        userTokenEntity.setUser(userEntity);
        userTokenEntity.setToken(token);
        userTokenEntity.setExpireAt(localExpire);
        userTokenRepository.save(userTokenEntity);
        return token;
    }
}
