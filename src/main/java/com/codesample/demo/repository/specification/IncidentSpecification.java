package com.codesample.demo.repository.specification;

import com.codesample.demo.domain.IncidentEntity;
import com.codesample.demo.domain.IncidentEntity_;
import com.codesample.demo.request.IncidentFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IncidentSpecification {
    public static Specification<IncidentEntity> createFilter(IncidentFilterDto incidentFilterDto) {
        return (Root<IncidentEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            Set<Predicate> andPredicates = new HashSet<>();
            if(Objects.nonNull(incidentFilterDto.getTitleLike())) {
                andPredicates.add(cb.like(cb.lower(root.get(IncidentEntity_.title)), "%" + incidentFilterDto.getTitleLike().toLowerCase() + "%"));
            }
            if(Objects.nonNull(incidentFilterDto.getSourceUrlLike())) {
                andPredicates.add(cb.like(root.get(IncidentEntity_.sourceUrl), "%" + incidentFilterDto.getSourceUrlLike().toLowerCase() + "%"));
            }
            if(Objects.nonNull(incidentFilterDto.getIncidentDateEnd())) {
                andPredicates.add(cb.lessThan(root.get(IncidentEntity_.incidentDate), incidentFilterDto.getIncidentDateEnd()));
            }
            if(Objects.nonNull(incidentFilterDto.getIncidentDateStart())) {
                andPredicates.add(cb.greaterThanOrEqualTo(root.get(IncidentEntity_.incidentDate), incidentFilterDto.getIncidentDateStart()));
            }
            return andPredicates.stream().reduce(cb::and).orElse(null);
        };
    }
}
