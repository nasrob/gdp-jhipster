package com.gdp.jhipster.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gdp.jhipster.web.rest.TestUtil;

public class CountryLanguageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountryLanguageDTO.class);
        CountryLanguageDTO countryLanguageDTO1 = new CountryLanguageDTO();
        countryLanguageDTO1.setId(1L);
        CountryLanguageDTO countryLanguageDTO2 = new CountryLanguageDTO();
        assertThat(countryLanguageDTO1).isNotEqualTo(countryLanguageDTO2);
        countryLanguageDTO2.setId(countryLanguageDTO1.getId());
        assertThat(countryLanguageDTO1).isEqualTo(countryLanguageDTO2);
        countryLanguageDTO2.setId(2L);
        assertThat(countryLanguageDTO1).isNotEqualTo(countryLanguageDTO2);
        countryLanguageDTO1.setId(null);
        assertThat(countryLanguageDTO1).isNotEqualTo(countryLanguageDTO2);
    }
}
