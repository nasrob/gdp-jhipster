package com.gdp.jhipster.service.mapper;


import com.gdp.jhipster.domain.*;
import com.gdp.jhipster.service.dto.SchoolDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link School} and its DTO {@link SchoolDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SchoolMapper extends EntityMapper<SchoolDTO, School> {


    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "removeTeacher", ignore = true)
    School toEntity(SchoolDTO schoolDTO);

    default School fromId(Long id) {
        if (id == null) {
            return null;
        }
        School school = new School();
        school.setId(id);
        return school;
    }
}
