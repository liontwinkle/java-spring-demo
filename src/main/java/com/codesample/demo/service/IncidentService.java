package com.codesample.demo.service;

import com.codesample.demo.domain.IncidentEntity;
import com.codesample.demo.request.IncidentFilterDto;
import com.codesample.demo.request.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IncidentService {
    IncidentEntity createIncident(UserPrincipal principal, IncidentEntity incidentEntity);
    IncidentEntity updateIncident(UserPrincipal principal, IncidentEntity incidentEntity);
    Page<IncidentEntity> getIncidents(IncidentFilterDto filter, Pageable pageable, UserPrincipal principal);
    IncidentEntity getById(Long incidentId, UserPrincipal principal);
    void deleteIncident(UserPrincipal principal, Long incidentId);
}
