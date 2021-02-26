package com.gdp.jhipster.web.rest;

import com.gdp.jhipster.GdpJhipsterApp;
import com.gdp.jhipster.domain.CountryLanguage;
import com.gdp.jhipster.domain.Country;
import com.gdp.jhipster.repository.CountryLanguageRepository;
import com.gdp.jhipster.service.CountryLanguageService;
import com.gdp.jhipster.service.dto.CountryLanguageDTO;
import com.gdp.jhipster.service.mapper.CountryLanguageMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gdp.jhipster.domain.enumeration.TrueFalse;
/**
 * Integration tests for the {@link CountryLanguageResource} REST controller.
 */
@SpringBootTest(classes = GdpJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CountryLanguageResourceIT {

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final TrueFalse DEFAULT_IS_OFFICIAL = TrueFalse.T;
    private static final TrueFalse UPDATED_IS_OFFICIAL = TrueFalse.F;

    private static final Float DEFAULT_PERCENTAGE = 1F;
    private static final Float UPDATED_PERCENTAGE = 2F;

    @Autowired
    private CountryLanguageRepository countryLanguageRepository;

    @Autowired
    private CountryLanguageMapper countryLanguageMapper;

    @Autowired
    private CountryLanguageService countryLanguageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCountryLanguageMockMvc;

    private CountryLanguage countryLanguage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CountryLanguage createEntity(EntityManager em) {
        CountryLanguage countryLanguage = new CountryLanguage()
            .language(DEFAULT_LANGUAGE)
            .isOfficial(DEFAULT_IS_OFFICIAL)
            .percentage(DEFAULT_PERCENTAGE);
        // Add required entity
        Country country;
        if (TestUtil.findAll(em, Country.class).isEmpty()) {
            country = CountryResourceIT.createEntity(em);
            em.persist(country);
            em.flush();
        } else {
            country = TestUtil.findAll(em, Country.class).get(0);
        }
        countryLanguage.setCountry(country);
        return countryLanguage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CountryLanguage createUpdatedEntity(EntityManager em) {
        CountryLanguage countryLanguage = new CountryLanguage()
            .language(UPDATED_LANGUAGE)
            .isOfficial(UPDATED_IS_OFFICIAL)
            .percentage(UPDATED_PERCENTAGE);
        // Add required entity
        Country country;
        if (TestUtil.findAll(em, Country.class).isEmpty()) {
            country = CountryResourceIT.createUpdatedEntity(em);
            em.persist(country);
            em.flush();
        } else {
            country = TestUtil.findAll(em, Country.class).get(0);
        }
        countryLanguage.setCountry(country);
        return countryLanguage;
    }

    @BeforeEach
    public void initTest() {
        countryLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createCountryLanguage() throws Exception {
        int databaseSizeBeforeCreate = countryLanguageRepository.findAll().size();
        // Create the CountryLanguage
        CountryLanguageDTO countryLanguageDTO = countryLanguageMapper.toDto(countryLanguage);
        restCountryLanguageMockMvc.perform(post("/api/country-languages").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryLanguageDTO)))
            .andExpect(status().isCreated());

        // Validate the CountryLanguage in the database
        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        CountryLanguage testCountryLanguage = countryLanguageList.get(countryLanguageList.size() - 1);
        assertThat(testCountryLanguage.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testCountryLanguage.getIsOfficial()).isEqualTo(DEFAULT_IS_OFFICIAL);
        assertThat(testCountryLanguage.getPercentage()).isEqualTo(DEFAULT_PERCENTAGE);
    }

    @Test
    @Transactional
    public void createCountryLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = countryLanguageRepository.findAll().size();

        // Create the CountryLanguage with an existing ID
        countryLanguage.setId(1L);
        CountryLanguageDTO countryLanguageDTO = countryLanguageMapper.toDto(countryLanguage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCountryLanguageMockMvc.perform(post("/api/country-languages").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CountryLanguage in the database
        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLanguageIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryLanguageRepository.findAll().size();
        // set the field null
        countryLanguage.setLanguage(null);

        // Create the CountryLanguage, which fails.
        CountryLanguageDTO countryLanguageDTO = countryLanguageMapper.toDto(countryLanguage);


        restCountryLanguageMockMvc.perform(post("/api/country-languages").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryLanguageDTO)))
            .andExpect(status().isBadRequest());

        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsOfficialIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryLanguageRepository.findAll().size();
        // set the field null
        countryLanguage.setIsOfficial(null);

        // Create the CountryLanguage, which fails.
        CountryLanguageDTO countryLanguageDTO = countryLanguageMapper.toDto(countryLanguage);


        restCountryLanguageMockMvc.perform(post("/api/country-languages").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryLanguageDTO)))
            .andExpect(status().isBadRequest());

        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPercentageIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryLanguageRepository.findAll().size();
        // set the field null
        countryLanguage.setPercentage(null);

        // Create the CountryLanguage, which fails.
        CountryLanguageDTO countryLanguageDTO = countryLanguageMapper.toDto(countryLanguage);


        restCountryLanguageMockMvc.perform(post("/api/country-languages").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryLanguageDTO)))
            .andExpect(status().isBadRequest());

        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCountryLanguages() throws Exception {
        // Initialize the database
        countryLanguageRepository.saveAndFlush(countryLanguage);

        // Get all the countryLanguageList
        restCountryLanguageMockMvc.perform(get("/api/country-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(countryLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].isOfficial").value(hasItem(DEFAULT_IS_OFFICIAL.toString())))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(DEFAULT_PERCENTAGE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getCountryLanguage() throws Exception {
        // Initialize the database
        countryLanguageRepository.saveAndFlush(countryLanguage);

        // Get the countryLanguage
        restCountryLanguageMockMvc.perform(get("/api/country-languages/{id}", countryLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(countryLanguage.getId().intValue()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.isOfficial").value(DEFAULT_IS_OFFICIAL.toString()))
            .andExpect(jsonPath("$.percentage").value(DEFAULT_PERCENTAGE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCountryLanguage() throws Exception {
        // Get the countryLanguage
        restCountryLanguageMockMvc.perform(get("/api/country-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCountryLanguage() throws Exception {
        // Initialize the database
        countryLanguageRepository.saveAndFlush(countryLanguage);

        int databaseSizeBeforeUpdate = countryLanguageRepository.findAll().size();

        // Update the countryLanguage
        CountryLanguage updatedCountryLanguage = countryLanguageRepository.findById(countryLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedCountryLanguage are not directly saved in db
        em.detach(updatedCountryLanguage);
        updatedCountryLanguage
            .language(UPDATED_LANGUAGE)
            .isOfficial(UPDATED_IS_OFFICIAL)
            .percentage(UPDATED_PERCENTAGE);
        CountryLanguageDTO countryLanguageDTO = countryLanguageMapper.toDto(updatedCountryLanguage);

        restCountryLanguageMockMvc.perform(put("/api/country-languages").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryLanguageDTO)))
            .andExpect(status().isOk());

        // Validate the CountryLanguage in the database
        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeUpdate);
        CountryLanguage testCountryLanguage = countryLanguageList.get(countryLanguageList.size() - 1);
        assertThat(testCountryLanguage.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testCountryLanguage.getIsOfficial()).isEqualTo(UPDATED_IS_OFFICIAL);
        assertThat(testCountryLanguage.getPercentage()).isEqualTo(UPDATED_PERCENTAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingCountryLanguage() throws Exception {
        int databaseSizeBeforeUpdate = countryLanguageRepository.findAll().size();

        // Create the CountryLanguage
        CountryLanguageDTO countryLanguageDTO = countryLanguageMapper.toDto(countryLanguage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountryLanguageMockMvc.perform(put("/api/country-languages").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CountryLanguage in the database
        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCountryLanguage() throws Exception {
        // Initialize the database
        countryLanguageRepository.saveAndFlush(countryLanguage);

        int databaseSizeBeforeDelete = countryLanguageRepository.findAll().size();

        // Delete the countryLanguage
        restCountryLanguageMockMvc.perform(delete("/api/country-languages/{id}", countryLanguage.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CountryLanguage> countryLanguageList = countryLanguageRepository.findAll();
        assertThat(countryLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
