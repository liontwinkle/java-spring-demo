package com.codesample.demo.repository;

import com.codesample.demo.domain.UserEntity;
import com.codesample.demo.domain.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
    Optional<UserTokenEntity> findByToken(String token);

    @Modifying
    @Transactional
    void deleteAllByUser(UserEntity user);

    @Modifying
    @Transactional
    void deleteAllByExpireAtBefore(LocalDateTime now);
}
