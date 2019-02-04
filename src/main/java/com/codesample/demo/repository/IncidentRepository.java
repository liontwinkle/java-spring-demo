package com.codesample.demo.repository;

import com.codesample.demo.domain.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<IncidentEntity, Long>, JpaSpecificationExecutor<IncidentEntity> {
}
