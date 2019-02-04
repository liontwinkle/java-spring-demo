package com.codesample.demo.controller;

import com.codesample.demo.domain.IncidentEntity;
import com.codesample.demo.mapper.IncidentMapper;
import com.codesample.demo.request.IncidentDto;
import com.codesample.demo.request.IncidentFilterDto;
import com.codesample.demo.request.IncidentUpdateDto;
import com.codesample.demo.request.UserPrincipal;
import com.codesample.demo.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/incident")
public class IncidentController {
    @Autowired
    private IncidentService incidentService;
    @Autowired
    private IncidentMapper incidentMapper;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IncidentDto> createIncident (@AuthenticationPrincipal UserPrincipal principal,
                                                       @Valid @RequestBody IncidentDto incidentDto) {
        IncidentEntity incidentEntity = incidentMapper.incidentDtoToEntity(incidentDto);
        IncidentEntity incident = incidentService.createIncident(principal, incidentEntity);
        return new ResponseEntity<>(incidentMapper.incidentEntityToDto(incident), HttpStatus.OK);
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IncidentDto> updateIncident (@AuthenticationPrincipal UserPrincipal principal,
                                                       @Valid @RequestBody IncidentUpdateDto incidentUpdateDto) {
        IncidentEntity incidentEntity = incidentMapper.incidentUpdateDtoToEntity(incidentUpdateDto);
        IncidentEntity updatedIncident = incidentService.updateIncident(principal, incidentEntity);
        return new ResponseEntity<>(incidentMapper.incidentEntityToDto(updatedIncident), HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Page<IncidentDto> getIncidentsByFilter (@AuthenticationPrincipal UserPrincipal principal,
                                                   @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                   @Valid IncidentFilterDto filter
    ) {
        Page<IncidentEntity> incidentPage = incidentService.getIncidents(filter, pageable, principal);

        return incidentPage.map(incidentMapper::incidentEntityToDto);
    }

    @GetMapping("/{incidentId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public IncidentDto getIncidentById (@AuthenticationPrincipal UserPrincipal principal,
                                        @PathVariable Long incidentId) {

        return incidentMapper.incidentEntityToDto(incidentService.getById(incidentId, principal));
    }

    @DeleteMapping("/{incidentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteIncident (@AuthenticationPrincipal UserPrincipal principal,
                                                @PathVariable Long incidentId) {
        incidentService.deleteIncident(principal, incidentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
