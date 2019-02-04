package com.codesample.demo.mapper;

import com.codesample.demo.domain.IncidentEntity;
import com.codesample.demo.request.IncidentDto;
import com.codesample.demo.request.IncidentUpdateDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface IncidentMapper {
    IncidentDto incidentEntityToDto (IncidentEntity incidentEntity);
    List<IncidentDto> incidentEntityToDto (List<IncidentEntity> incidentEntityList);
    IncidentEntity incidentDtoToEntity(IncidentDto incidentShareDto);
    IncidentEntity incidentUpdateDtoToEntity(IncidentUpdateDto incidentUpdateDto);
}
