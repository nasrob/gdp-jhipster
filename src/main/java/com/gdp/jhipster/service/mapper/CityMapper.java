package com.gdp.jhipster.service.mapper;


import com.gdp.jhipster.domain.*;
import com.gdp.jhipster.service.dto.CityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface CityMapper extends EntityMapper<CityDTO, City> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.name", target = "countryName")
    CityDTO toDto(City city);

    @Mapping(source = "countryId", target = "country")
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}
