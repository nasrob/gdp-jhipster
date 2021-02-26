package com.gdp.jhipster.service.mapper;


import com.gdp.jhipster.domain.*;
import com.gdp.jhipster.service.dto.CountryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Country} and its DTO {@link CountryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {


    @Mapping(target = "cities", ignore = true)
    @Mapping(target = "removeCity", ignore = true)
    @Mapping(target = "countryLanguages", ignore = true)
    @Mapping(target = "removeCountryLanguage", ignore = true)
    Country toEntity(CountryDTO countryDTO);

    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
