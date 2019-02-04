package com.codesample.demo.scheduler;

import com.codesample.demo.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduleTask {
    @Autowired
    private UserTokenRepository userTokenRepository;

    @Scheduled(fixedDelay = 5 * 60 * 1000L)
    public void deleteExpiredTokens() {
        userTokenRepository.deleteAllByExpireAtBefore(LocalDateTime.now());
    }
}
