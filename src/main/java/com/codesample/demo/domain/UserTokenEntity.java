package com.codesample.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_token")
public class UserTokenEntity extends AuditableEntity {
    @Column(name = "token_string")
    private String token;
    private LocalDateTime expireAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
