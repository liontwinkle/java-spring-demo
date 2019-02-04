package com.codesample.demo.listener;

import com.codesample.demo.domain.AuditableEntity;
import com.codesample.demo.request.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Objects;

public class AuditEntityListener {
    @PrePersist
    public void prePersist(AuditableEntity e) {
        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                e.setCreatedBy(principal.getId());
                e.setModifiedBy(principal.getId());
            }
        }
    }

    @PreUpdate
    public void preUpdate(AuditableEntity e) {
        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                e.setModifiedBy(principal.getId());
            }
        }
    }
}
