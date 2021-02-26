package com.gdp.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gdp.jhipster.web.rest.TestUtil;

public class CountryLanguageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountryLanguage.class);
        CountryLanguage countryLanguage1 = new CountryLanguage();
        countryLanguage1.setId(1L);
        CountryLanguage countryLanguage2 = new CountryLanguage();
        countryLanguage2.setId(countryLanguage1.getId());
        assertThat(countryLanguage1).isEqualTo(countryLanguage2);
        countryLanguage2.setId(2L);
        assertThat(countryLanguage1).isNotEqualTo(countryLanguage2);
        countryLanguage1.setId(null);
        assertThat(countryLanguage1).isNotEqualTo(countryLanguage2);
    }
}
