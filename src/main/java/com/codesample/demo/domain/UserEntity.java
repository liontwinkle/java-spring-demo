package com.codesample.demo.domain;

import com.codesample.demo.domain.enumeration.RoleEnum;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserEntity extends AuditableEntity {
    @NotEmpty
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_value")
    private RoleEnum role;
    @NotNull
    private Boolean enabled;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
