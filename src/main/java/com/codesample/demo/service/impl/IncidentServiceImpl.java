package com.codesample.demo.service.impl;

import com.codesample.demo.domain.IncidentEntity;
import com.codesample.demo.exceprion.NotFoundException;
import com.codesample.demo.repository.IncidentRepository;
import com.codesample.demo.repository.specification.IncidentSpecification;
import com.codesample.demo.request.IncidentFilterDto;
import com.codesample.demo.request.UserPrincipal;
import com.codesample.demo.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IncidentServiceImpl implements IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public IncidentEntity createIncident(UserPrincipal principal, IncidentEntity incidentEntity) {
        incidentEntity.setId(null);
        return incidentRepository.save(incidentEntity);
    }

    @Override
    public IncidentEntity updateIncident(UserPrincipal principal, IncidentEntity incidentEntity) {
        IncidentEntity original = incidentRepository.findById(incidentEntity.getId()).orElseThrow(()->new NotFoundException("Incident not found"));

        if(Objects.nonNull(incidentEntity.getTitle())) {
            original.setTitle(incidentEntity.getTitle());
        }
        if(Objects.nonNull(incidentEntity.getIncidentDate())) {
            original.setDescription(incidentEntity.getDescription());
        }
        if(Objects.nonNull(incidentEntity.getIncidentDate())) {
            original.setIncidentDate(incidentEntity.getIncidentDate());
        }
        if(Objects.nonNull(incidentEntity.getSourceUrl())) {
            original.setSourceUrl(incidentEntity.getSourceUrl());
        }

        return incidentRepository.save(original);
    }

    @Override
    public Page<IncidentEntity> getIncidents(IncidentFilterDto filter, Pageable pageable, UserPrincipal principal) {
        Specification<IncidentEntity> specification = IncidentSpecification.createFilter(filter);
        return (Objects.isNull(specification)) ? incidentRepository.findAll(pageable) : incidentRepository.findAll(specification, pageable);
    }

    @Override
    public IncidentEntity getById(Long incidentId, UserPrincipal principal) {
        return incidentRepository.findById(incidentId).orElseThrow(()->new NotFoundException("Incident not found"));
    }

    @Override
    public void deleteIncident(UserPrincipal principal, Long incidentId) {
        incidentRepository.deleteById(incidentId);
    }
}
