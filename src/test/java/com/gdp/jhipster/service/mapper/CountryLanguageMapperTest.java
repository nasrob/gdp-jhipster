package com.gdp.jhipster.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CountryLanguageMapperTest {

    private CountryLanguageMapper countryLanguageMapper;

    @BeforeEach
    public void setUp() {
        countryLanguageMapper = new CountryLanguageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(countryLanguageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(countryLanguageMapper.fromId(null)).isNull();
    }
}
