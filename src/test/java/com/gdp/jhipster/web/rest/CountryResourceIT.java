package com.gdp.jhipster.web.rest;

import com.gdp.jhipster.GdpJhipsterApp;
import com.gdp.jhipster.domain.Country;
import com.gdp.jhipster.domain.City;
import com.gdp.jhipster.domain.CountryLanguage;
import com.gdp.jhipster.repository.CountryRepository;
import com.gdp.jhipster.service.CountryService;
import com.gdp.jhipster.service.dto.CountryDTO;
import com.gdp.jhipster.service.mapper.CountryMapper;
import com.gdp.jhipster.service.dto.CountryCriteria;
import com.gdp.jhipster.service.CountryQueryService;

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

import com.gdp.jhipster.domain.enumeration.Continent;
/**
 * Integration tests for the {@link CountryResource} REST controller.
 */
@SpringBootTest(classes = GdpJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CountryResourceIT {

    private static final String DEFAULT_CODE = "AAA";
    private static final String UPDATED_CODE = "BBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Continent DEFAULT_CONTINENT = Continent.ASIA;
    private static final Continent UPDATED_CONTINENT = Continent.EUROPE;

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final Float DEFAULT_SURFACE_AREA = 1F;
    private static final Float UPDATED_SURFACE_AREA = 2F;
    private static final Float SMALLER_SURFACE_AREA = 1F - 1F;

    private static final Integer DEFAULT_POPULATION = 1;
    private static final Integer UPDATED_POPULATION = 2;
    private static final Integer SMALLER_POPULATION = 1 - 1;

    private static final Float DEFAULT_LIFE_EXPECTANCY = 1F;
    private static final Float UPDATED_LIFE_EXPECTANCY = 2F;
    private static final Float SMALLER_LIFE_EXPECTANCY = 1F - 1F;

    private static final String DEFAULT_LOCAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GOVERNMENT_FORM = "AAAAAAAAAA";
    private static final String UPDATED_GOVERNMENT_FORM = "BBBBBBBBBB";

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryQueryService countryQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCountryMockMvc;

    private Country country;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Country createEntity(EntityManager em) {
        Country country = new Country()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .continent(DEFAULT_CONTINENT)
            .region(DEFAULT_REGION)
            .surfaceArea(DEFAULT_SURFACE_AREA)
            .population(DEFAULT_POPULATION)
            .lifeExpectancy(DEFAULT_LIFE_EXPECTANCY)
            .localName(DEFAULT_LOCAL_NAME)
            .governmentForm(DEFAULT_GOVERNMENT_FORM);
        return country;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Country createUpdatedEntity(EntityManager em) {
        Country country = new Country()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .continent(UPDATED_CONTINENT)
            .region(UPDATED_REGION)
            .surfaceArea(UPDATED_SURFACE_AREA)
            .population(UPDATED_POPULATION)
            .lifeExpectancy(UPDATED_LIFE_EXPECTANCY)
            .localName(UPDATED_LOCAL_NAME)
            .governmentForm(UPDATED_GOVERNMENT_FORM);
        return country;
    }

    @BeforeEach
    public void initTest() {
        country = createEntity(em);
    }

    @Test
    @Transactional
    public void createCountry() throws Exception {
        int databaseSizeBeforeCreate = countryRepository.findAll().size();
        // Create the Country
        CountryDTO countryDTO = countryMapper.toDto(country);
        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isCreated());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeCreate + 1);
        Country testCountry = countryList.get(countryList.size() - 1);
        assertThat(testCountry.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCountry.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCountry.getContinent()).isEqualTo(DEFAULT_CONTINENT);
        assertThat(testCountry.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testCountry.getSurfaceArea()).isEqualTo(DEFAULT_SURFACE_AREA);
        assertThat(testCountry.getPopulation()).isEqualTo(DEFAULT_POPULATION);
        assertThat(testCountry.getLifeExpectancy()).isEqualTo(DEFAULT_LIFE_EXPECTANCY);
        assertThat(testCountry.getLocalName()).isEqualTo(DEFAULT_LOCAL_NAME);
        assertThat(testCountry.getGovernmentForm()).isEqualTo(DEFAULT_GOVERNMENT_FORM);
    }

    @Test
    @Transactional
    public void createCountryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = countryRepository.findAll().size();

        // Create the Country with an existing ID
        country.setId(1L);
        CountryDTO countryDTO = countryMapper.toDto(country);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setCode(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setName(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContinentIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setContinent(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegionIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setRegion(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSurfaceAreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setSurfaceArea(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPopulationIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setPopulation(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setLocalName(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGovernmentFormIsRequired() throws Exception {
        int databaseSizeBeforeTest = countryRepository.findAll().size();
        // set the field null
        country.setGovernmentForm(null);

        // Create the Country, which fails.
        CountryDTO countryDTO = countryMapper.toDto(country);


        restCountryMockMvc.perform(post("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCountries() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList
        restCountryMockMvc.perform(get("/api/countries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(country.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].continent").value(hasItem(DEFAULT_CONTINENT.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].surfaceArea").value(hasItem(DEFAULT_SURFACE_AREA.doubleValue())))
            .andExpect(jsonPath("$.[*].population").value(hasItem(DEFAULT_POPULATION)))
            .andExpect(jsonPath("$.[*].lifeExpectancy").value(hasItem(DEFAULT_LIFE_EXPECTANCY.doubleValue())))
            .andExpect(jsonPath("$.[*].localName").value(hasItem(DEFAULT_LOCAL_NAME)))
            .andExpect(jsonPath("$.[*].governmentForm").value(hasItem(DEFAULT_GOVERNMENT_FORM)));
    }
    
    @Test
    @Transactional
    public void getCountry() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get the country
        restCountryMockMvc.perform(get("/api/countries/{id}", country.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(country.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.continent").value(DEFAULT_CONTINENT.toString()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.surfaceArea").value(DEFAULT_SURFACE_AREA.doubleValue()))
            .andExpect(jsonPath("$.population").value(DEFAULT_POPULATION))
            .andExpect(jsonPath("$.lifeExpectancy").value(DEFAULT_LIFE_EXPECTANCY.doubleValue()))
            .andExpect(jsonPath("$.localName").value(DEFAULT_LOCAL_NAME))
            .andExpect(jsonPath("$.governmentForm").value(DEFAULT_GOVERNMENT_FORM));
    }


    @Test
    @Transactional
    public void getCountriesByIdFiltering() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        Long id = country.getId();

        defaultCountryShouldBeFound("id.equals=" + id);
        defaultCountryShouldNotBeFound("id.notEquals=" + id);

        defaultCountryShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCountryShouldNotBeFound("id.greaterThan=" + id);

        defaultCountryShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCountryShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCountriesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where code equals to DEFAULT_CODE
        defaultCountryShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the countryList where code equals to UPDATED_CODE
        defaultCountryShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCountriesByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where code not equals to DEFAULT_CODE
        defaultCountryShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the countryList where code not equals to UPDATED_CODE
        defaultCountryShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCountriesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where code in DEFAULT_CODE or UPDATED_CODE
        defaultCountryShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the countryList where code equals to UPDATED_CODE
        defaultCountryShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCountriesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where code is not null
        defaultCountryShouldBeFound("code.specified=true");

        // Get all the countryList where code is null
        defaultCountryShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllCountriesByCodeContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where code contains DEFAULT_CODE
        defaultCountryShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the countryList where code contains UPDATED_CODE
        defaultCountryShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCountriesByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where code does not contain DEFAULT_CODE
        defaultCountryShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the countryList where code does not contain UPDATED_CODE
        defaultCountryShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllCountriesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where name equals to DEFAULT_NAME
        defaultCountryShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the countryList where name equals to UPDATED_NAME
        defaultCountryShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where name not equals to DEFAULT_NAME
        defaultCountryShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the countryList where name not equals to UPDATED_NAME
        defaultCountryShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where name in DEFAULT_NAME or UPDATED_NAME
        defaultCountryShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the countryList where name equals to UPDATED_NAME
        defaultCountryShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where name is not null
        defaultCountryShouldBeFound("name.specified=true");

        // Get all the countryList where name is null
        defaultCountryShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllCountriesByNameContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where name contains DEFAULT_NAME
        defaultCountryShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the countryList where name contains UPDATED_NAME
        defaultCountryShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where name does not contain DEFAULT_NAME
        defaultCountryShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the countryList where name does not contain UPDATED_NAME
        defaultCountryShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllCountriesByContinentIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where continent equals to DEFAULT_CONTINENT
        defaultCountryShouldBeFound("continent.equals=" + DEFAULT_CONTINENT);

        // Get all the countryList where continent equals to UPDATED_CONTINENT
        defaultCountryShouldNotBeFound("continent.equals=" + UPDATED_CONTINENT);
    }

    @Test
    @Transactional
    public void getAllCountriesByContinentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where continent not equals to DEFAULT_CONTINENT
        defaultCountryShouldNotBeFound("continent.notEquals=" + DEFAULT_CONTINENT);

        // Get all the countryList where continent not equals to UPDATED_CONTINENT
        defaultCountryShouldBeFound("continent.notEquals=" + UPDATED_CONTINENT);
    }

    @Test
    @Transactional
    public void getAllCountriesByContinentIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where continent in DEFAULT_CONTINENT or UPDATED_CONTINENT
        defaultCountryShouldBeFound("continent.in=" + DEFAULT_CONTINENT + "," + UPDATED_CONTINENT);

        // Get all the countryList where continent equals to UPDATED_CONTINENT
        defaultCountryShouldNotBeFound("continent.in=" + UPDATED_CONTINENT);
    }

    @Test
    @Transactional
    public void getAllCountriesByContinentIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where continent is not null
        defaultCountryShouldBeFound("continent.specified=true");

        // Get all the countryList where continent is null
        defaultCountryShouldNotBeFound("continent.specified=false");
    }

    @Test
    @Transactional
    public void getAllCountriesByRegionIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where region equals to DEFAULT_REGION
        defaultCountryShouldBeFound("region.equals=" + DEFAULT_REGION);

        // Get all the countryList where region equals to UPDATED_REGION
        defaultCountryShouldNotBeFound("region.equals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllCountriesByRegionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where region not equals to DEFAULT_REGION
        defaultCountryShouldNotBeFound("region.notEquals=" + DEFAULT_REGION);

        // Get all the countryList where region not equals to UPDATED_REGION
        defaultCountryShouldBeFound("region.notEquals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllCountriesByRegionIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where region in DEFAULT_REGION or UPDATED_REGION
        defaultCountryShouldBeFound("region.in=" + DEFAULT_REGION + "," + UPDATED_REGION);

        // Get all the countryList where region equals to UPDATED_REGION
        defaultCountryShouldNotBeFound("region.in=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllCountriesByRegionIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where region is not null
        defaultCountryShouldBeFound("region.specified=true");

        // Get all the countryList where region is null
        defaultCountryShouldNotBeFound("region.specified=false");
    }
                @Test
    @Transactional
    public void getAllCountriesByRegionContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where region contains DEFAULT_REGION
        defaultCountryShouldBeFound("region.contains=" + DEFAULT_REGION);

        // Get all the countryList where region contains UPDATED_REGION
        defaultCountryShouldNotBeFound("region.contains=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllCountriesByRegionNotContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where region does not contain DEFAULT_REGION
        defaultCountryShouldNotBeFound("region.doesNotContain=" + DEFAULT_REGION);

        // Get all the countryList where region does not contain UPDATED_REGION
        defaultCountryShouldBeFound("region.doesNotContain=" + UPDATED_REGION);
    }


    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea equals to DEFAULT_SURFACE_AREA
        defaultCountryShouldBeFound("surfaceArea.equals=" + DEFAULT_SURFACE_AREA);

        // Get all the countryList where surfaceArea equals to UPDATED_SURFACE_AREA
        defaultCountryShouldNotBeFound("surfaceArea.equals=" + UPDATED_SURFACE_AREA);
    }

    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea not equals to DEFAULT_SURFACE_AREA
        defaultCountryShouldNotBeFound("surfaceArea.notEquals=" + DEFAULT_SURFACE_AREA);

        // Get all the countryList where surfaceArea not equals to UPDATED_SURFACE_AREA
        defaultCountryShouldBeFound("surfaceArea.notEquals=" + UPDATED_SURFACE_AREA);
    }

    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea in DEFAULT_SURFACE_AREA or UPDATED_SURFACE_AREA
        defaultCountryShouldBeFound("surfaceArea.in=" + DEFAULT_SURFACE_AREA + "," + UPDATED_SURFACE_AREA);

        // Get all the countryList where surfaceArea equals to UPDATED_SURFACE_AREA
        defaultCountryShouldNotBeFound("surfaceArea.in=" + UPDATED_SURFACE_AREA);
    }

    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea is not null
        defaultCountryShouldBeFound("surfaceArea.specified=true");

        // Get all the countryList where surfaceArea is null
        defaultCountryShouldNotBeFound("surfaceArea.specified=false");
    }

    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea is greater than or equal to DEFAULT_SURFACE_AREA
        defaultCountryShouldBeFound("surfaceArea.greaterThanOrEqual=" + DEFAULT_SURFACE_AREA);

        // Get all the countryList where surfaceArea is greater than or equal to UPDATED_SURFACE_AREA
        defaultCountryShouldNotBeFound("surfaceArea.greaterThanOrEqual=" + UPDATED_SURFACE_AREA);
    }

    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea is less than or equal to DEFAULT_SURFACE_AREA
        defaultCountryShouldBeFound("surfaceArea.lessThanOrEqual=" + DEFAULT_SURFACE_AREA);

        // Get all the countryList where surfaceArea is less than or equal to SMALLER_SURFACE_AREA
        defaultCountryShouldNotBeFound("surfaceArea.lessThanOrEqual=" + SMALLER_SURFACE_AREA);
    }

    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsLessThanSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea is less than DEFAULT_SURFACE_AREA
        defaultCountryShouldNotBeFound("surfaceArea.lessThan=" + DEFAULT_SURFACE_AREA);

        // Get all the countryList where surfaceArea is less than UPDATED_SURFACE_AREA
        defaultCountryShouldBeFound("surfaceArea.lessThan=" + UPDATED_SURFACE_AREA);
    }

    @Test
    @Transactional
    public void getAllCountriesBySurfaceAreaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where surfaceArea is greater than DEFAULT_SURFACE_AREA
        defaultCountryShouldNotBeFound("surfaceArea.greaterThan=" + DEFAULT_SURFACE_AREA);

        // Get all the countryList where surfaceArea is greater than SMALLER_SURFACE_AREA
        defaultCountryShouldBeFound("surfaceArea.greaterThan=" + SMALLER_SURFACE_AREA);
    }


    @Test
    @Transactional
    public void getAllCountriesByPopulationIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population equals to DEFAULT_POPULATION
        defaultCountryShouldBeFound("population.equals=" + DEFAULT_POPULATION);

        // Get all the countryList where population equals to UPDATED_POPULATION
        defaultCountryShouldNotBeFound("population.equals=" + UPDATED_POPULATION);
    }

    @Test
    @Transactional
    public void getAllCountriesByPopulationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population not equals to DEFAULT_POPULATION
        defaultCountryShouldNotBeFound("population.notEquals=" + DEFAULT_POPULATION);

        // Get all the countryList where population not equals to UPDATED_POPULATION
        defaultCountryShouldBeFound("population.notEquals=" + UPDATED_POPULATION);
    }

    @Test
    @Transactional
    public void getAllCountriesByPopulationIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population in DEFAULT_POPULATION or UPDATED_POPULATION
        defaultCountryShouldBeFound("population.in=" + DEFAULT_POPULATION + "," + UPDATED_POPULATION);

        // Get all the countryList where population equals to UPDATED_POPULATION
        defaultCountryShouldNotBeFound("population.in=" + UPDATED_POPULATION);
    }

    @Test
    @Transactional
    public void getAllCountriesByPopulationIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population is not null
        defaultCountryShouldBeFound("population.specified=true");

        // Get all the countryList where population is null
        defaultCountryShouldNotBeFound("population.specified=false");
    }

    @Test
    @Transactional
    public void getAllCountriesByPopulationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population is greater than or equal to DEFAULT_POPULATION
        defaultCountryShouldBeFound("population.greaterThanOrEqual=" + DEFAULT_POPULATION);

        // Get all the countryList where population is greater than or equal to UPDATED_POPULATION
        defaultCountryShouldNotBeFound("population.greaterThanOrEqual=" + UPDATED_POPULATION);
    }

    @Test
    @Transactional
    public void getAllCountriesByPopulationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population is less than or equal to DEFAULT_POPULATION
        defaultCountryShouldBeFound("population.lessThanOrEqual=" + DEFAULT_POPULATION);

        // Get all the countryList where population is less than or equal to SMALLER_POPULATION
        defaultCountryShouldNotBeFound("population.lessThanOrEqual=" + SMALLER_POPULATION);
    }

    @Test
    @Transactional
    public void getAllCountriesByPopulationIsLessThanSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population is less than DEFAULT_POPULATION
        defaultCountryShouldNotBeFound("population.lessThan=" + DEFAULT_POPULATION);

        // Get all the countryList where population is less than UPDATED_POPULATION
        defaultCountryShouldBeFound("population.lessThan=" + UPDATED_POPULATION);
    }

    @Test
    @Transactional
    public void getAllCountriesByPopulationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where population is greater than DEFAULT_POPULATION
        defaultCountryShouldNotBeFound("population.greaterThan=" + DEFAULT_POPULATION);

        // Get all the countryList where population is greater than SMALLER_POPULATION
        defaultCountryShouldBeFound("population.greaterThan=" + SMALLER_POPULATION);
    }


    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy equals to DEFAULT_LIFE_EXPECTANCY
        defaultCountryShouldBeFound("lifeExpectancy.equals=" + DEFAULT_LIFE_EXPECTANCY);

        // Get all the countryList where lifeExpectancy equals to UPDATED_LIFE_EXPECTANCY
        defaultCountryShouldNotBeFound("lifeExpectancy.equals=" + UPDATED_LIFE_EXPECTANCY);
    }

    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy not equals to DEFAULT_LIFE_EXPECTANCY
        defaultCountryShouldNotBeFound("lifeExpectancy.notEquals=" + DEFAULT_LIFE_EXPECTANCY);

        // Get all the countryList where lifeExpectancy not equals to UPDATED_LIFE_EXPECTANCY
        defaultCountryShouldBeFound("lifeExpectancy.notEquals=" + UPDATED_LIFE_EXPECTANCY);
    }

    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy in DEFAULT_LIFE_EXPECTANCY or UPDATED_LIFE_EXPECTANCY
        defaultCountryShouldBeFound("lifeExpectancy.in=" + DEFAULT_LIFE_EXPECTANCY + "," + UPDATED_LIFE_EXPECTANCY);

        // Get all the countryList where lifeExpectancy equals to UPDATED_LIFE_EXPECTANCY
        defaultCountryShouldNotBeFound("lifeExpectancy.in=" + UPDATED_LIFE_EXPECTANCY);
    }

    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy is not null
        defaultCountryShouldBeFound("lifeExpectancy.specified=true");

        // Get all the countryList where lifeExpectancy is null
        defaultCountryShouldNotBeFound("lifeExpectancy.specified=false");
    }

    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy is greater than or equal to DEFAULT_LIFE_EXPECTANCY
        defaultCountryShouldBeFound("lifeExpectancy.greaterThanOrEqual=" + DEFAULT_LIFE_EXPECTANCY);

        // Get all the countryList where lifeExpectancy is greater than or equal to UPDATED_LIFE_EXPECTANCY
        defaultCountryShouldNotBeFound("lifeExpectancy.greaterThanOrEqual=" + UPDATED_LIFE_EXPECTANCY);
    }

    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy is less than or equal to DEFAULT_LIFE_EXPECTANCY
        defaultCountryShouldBeFound("lifeExpectancy.lessThanOrEqual=" + DEFAULT_LIFE_EXPECTANCY);

        // Get all the countryList where lifeExpectancy is less than or equal to SMALLER_LIFE_EXPECTANCY
        defaultCountryShouldNotBeFound("lifeExpectancy.lessThanOrEqual=" + SMALLER_LIFE_EXPECTANCY);
    }

    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsLessThanSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy is less than DEFAULT_LIFE_EXPECTANCY
        defaultCountryShouldNotBeFound("lifeExpectancy.lessThan=" + DEFAULT_LIFE_EXPECTANCY);

        // Get all the countryList where lifeExpectancy is less than UPDATED_LIFE_EXPECTANCY
        defaultCountryShouldBeFound("lifeExpectancy.lessThan=" + UPDATED_LIFE_EXPECTANCY);
    }

    @Test
    @Transactional
    public void getAllCountriesByLifeExpectancyIsGreaterThanSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where lifeExpectancy is greater than DEFAULT_LIFE_EXPECTANCY
        defaultCountryShouldNotBeFound("lifeExpectancy.greaterThan=" + DEFAULT_LIFE_EXPECTANCY);

        // Get all the countryList where lifeExpectancy is greater than SMALLER_LIFE_EXPECTANCY
        defaultCountryShouldBeFound("lifeExpectancy.greaterThan=" + SMALLER_LIFE_EXPECTANCY);
    }


    @Test
    @Transactional
    public void getAllCountriesByLocalNameIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where localName equals to DEFAULT_LOCAL_NAME
        defaultCountryShouldBeFound("localName.equals=" + DEFAULT_LOCAL_NAME);

        // Get all the countryList where localName equals to UPDATED_LOCAL_NAME
        defaultCountryShouldNotBeFound("localName.equals=" + UPDATED_LOCAL_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByLocalNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where localName not equals to DEFAULT_LOCAL_NAME
        defaultCountryShouldNotBeFound("localName.notEquals=" + DEFAULT_LOCAL_NAME);

        // Get all the countryList where localName not equals to UPDATED_LOCAL_NAME
        defaultCountryShouldBeFound("localName.notEquals=" + UPDATED_LOCAL_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByLocalNameIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where localName in DEFAULT_LOCAL_NAME or UPDATED_LOCAL_NAME
        defaultCountryShouldBeFound("localName.in=" + DEFAULT_LOCAL_NAME + "," + UPDATED_LOCAL_NAME);

        // Get all the countryList where localName equals to UPDATED_LOCAL_NAME
        defaultCountryShouldNotBeFound("localName.in=" + UPDATED_LOCAL_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByLocalNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where localName is not null
        defaultCountryShouldBeFound("localName.specified=true");

        // Get all the countryList where localName is null
        defaultCountryShouldNotBeFound("localName.specified=false");
    }
                @Test
    @Transactional
    public void getAllCountriesByLocalNameContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where localName contains DEFAULT_LOCAL_NAME
        defaultCountryShouldBeFound("localName.contains=" + DEFAULT_LOCAL_NAME);

        // Get all the countryList where localName contains UPDATED_LOCAL_NAME
        defaultCountryShouldNotBeFound("localName.contains=" + UPDATED_LOCAL_NAME);
    }

    @Test
    @Transactional
    public void getAllCountriesByLocalNameNotContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where localName does not contain DEFAULT_LOCAL_NAME
        defaultCountryShouldNotBeFound("localName.doesNotContain=" + DEFAULT_LOCAL_NAME);

        // Get all the countryList where localName does not contain UPDATED_LOCAL_NAME
        defaultCountryShouldBeFound("localName.doesNotContain=" + UPDATED_LOCAL_NAME);
    }


    @Test
    @Transactional
    public void getAllCountriesByGovernmentFormIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where governmentForm equals to DEFAULT_GOVERNMENT_FORM
        defaultCountryShouldBeFound("governmentForm.equals=" + DEFAULT_GOVERNMENT_FORM);

        // Get all the countryList where governmentForm equals to UPDATED_GOVERNMENT_FORM
        defaultCountryShouldNotBeFound("governmentForm.equals=" + UPDATED_GOVERNMENT_FORM);
    }

    @Test
    @Transactional
    public void getAllCountriesByGovernmentFormIsNotEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where governmentForm not equals to DEFAULT_GOVERNMENT_FORM
        defaultCountryShouldNotBeFound("governmentForm.notEquals=" + DEFAULT_GOVERNMENT_FORM);

        // Get all the countryList where governmentForm not equals to UPDATED_GOVERNMENT_FORM
        defaultCountryShouldBeFound("governmentForm.notEquals=" + UPDATED_GOVERNMENT_FORM);
    }

    @Test
    @Transactional
    public void getAllCountriesByGovernmentFormIsInShouldWork() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where governmentForm in DEFAULT_GOVERNMENT_FORM or UPDATED_GOVERNMENT_FORM
        defaultCountryShouldBeFound("governmentForm.in=" + DEFAULT_GOVERNMENT_FORM + "," + UPDATED_GOVERNMENT_FORM);

        // Get all the countryList where governmentForm equals to UPDATED_GOVERNMENT_FORM
        defaultCountryShouldNotBeFound("governmentForm.in=" + UPDATED_GOVERNMENT_FORM);
    }

    @Test
    @Transactional
    public void getAllCountriesByGovernmentFormIsNullOrNotNull() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where governmentForm is not null
        defaultCountryShouldBeFound("governmentForm.specified=true");

        // Get all the countryList where governmentForm is null
        defaultCountryShouldNotBeFound("governmentForm.specified=false");
    }
                @Test
    @Transactional
    public void getAllCountriesByGovernmentFormContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where governmentForm contains DEFAULT_GOVERNMENT_FORM
        defaultCountryShouldBeFound("governmentForm.contains=" + DEFAULT_GOVERNMENT_FORM);

        // Get all the countryList where governmentForm contains UPDATED_GOVERNMENT_FORM
        defaultCountryShouldNotBeFound("governmentForm.contains=" + UPDATED_GOVERNMENT_FORM);
    }

    @Test
    @Transactional
    public void getAllCountriesByGovernmentFormNotContainsSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        // Get all the countryList where governmentForm does not contain DEFAULT_GOVERNMENT_FORM
        defaultCountryShouldNotBeFound("governmentForm.doesNotContain=" + DEFAULT_GOVERNMENT_FORM);

        // Get all the countryList where governmentForm does not contain UPDATED_GOVERNMENT_FORM
        defaultCountryShouldBeFound("governmentForm.doesNotContain=" + UPDATED_GOVERNMENT_FORM);
    }


    @Test
    @Transactional
    public void getAllCountriesByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);
        City city = CityResourceIT.createEntity(em);
        em.persist(city);
        em.flush();
        country.addCity(city);
        countryRepository.saveAndFlush(country);
        Long cityId = city.getId();

        // Get all the countryList where city equals to cityId
        defaultCountryShouldBeFound("cityId.equals=" + cityId);

        // Get all the countryList where city equals to cityId + 1
        defaultCountryShouldNotBeFound("cityId.equals=" + (cityId + 1));
    }


    @Test
    @Transactional
    public void getAllCountriesByCountryLanguageIsEqualToSomething() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);
        CountryLanguage countryLanguage = CountryLanguageResourceIT.createEntity(em);
        em.persist(countryLanguage);
        em.flush();
        country.addCountryLanguage(countryLanguage);
        countryRepository.saveAndFlush(country);
        Long countryLanguageId = countryLanguage.getId();

        // Get all the countryList where countryLanguage equals to countryLanguageId
        defaultCountryShouldBeFound("countryLanguageId.equals=" + countryLanguageId);

        // Get all the countryList where countryLanguage equals to countryLanguageId + 1
        defaultCountryShouldNotBeFound("countryLanguageId.equals=" + (countryLanguageId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCountryShouldBeFound(String filter) throws Exception {
        restCountryMockMvc.perform(get("/api/countries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(country.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].continent").value(hasItem(DEFAULT_CONTINENT.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].surfaceArea").value(hasItem(DEFAULT_SURFACE_AREA.doubleValue())))
            .andExpect(jsonPath("$.[*].population").value(hasItem(DEFAULT_POPULATION)))
            .andExpect(jsonPath("$.[*].lifeExpectancy").value(hasItem(DEFAULT_LIFE_EXPECTANCY.doubleValue())))
            .andExpect(jsonPath("$.[*].localName").value(hasItem(DEFAULT_LOCAL_NAME)))
            .andExpect(jsonPath("$.[*].governmentForm").value(hasItem(DEFAULT_GOVERNMENT_FORM)));

        // Check, that the count call also returns 1
        restCountryMockMvc.perform(get("/api/countries/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCountryShouldNotBeFound(String filter) throws Exception {
        restCountryMockMvc.perform(get("/api/countries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCountryMockMvc.perform(get("/api/countries/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCountry() throws Exception {
        // Get the country
        restCountryMockMvc.perform(get("/api/countries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCountry() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        int databaseSizeBeforeUpdate = countryRepository.findAll().size();

        // Update the country
        Country updatedCountry = countryRepository.findById(country.getId()).get();
        // Disconnect from session so that the updates on updatedCountry are not directly saved in db
        em.detach(updatedCountry);
        updatedCountry
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .continent(UPDATED_CONTINENT)
            .region(UPDATED_REGION)
            .surfaceArea(UPDATED_SURFACE_AREA)
            .population(UPDATED_POPULATION)
            .lifeExpectancy(UPDATED_LIFE_EXPECTANCY)
            .localName(UPDATED_LOCAL_NAME)
            .governmentForm(UPDATED_GOVERNMENT_FORM);
        CountryDTO countryDTO = countryMapper.toDto(updatedCountry);

        restCountryMockMvc.perform(put("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isOk());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeUpdate);
        Country testCountry = countryList.get(countryList.size() - 1);
        assertThat(testCountry.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCountry.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCountry.getContinent()).isEqualTo(UPDATED_CONTINENT);
        assertThat(testCountry.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testCountry.getSurfaceArea()).isEqualTo(UPDATED_SURFACE_AREA);
        assertThat(testCountry.getPopulation()).isEqualTo(UPDATED_POPULATION);
        assertThat(testCountry.getLifeExpectancy()).isEqualTo(UPDATED_LIFE_EXPECTANCY);
        assertThat(testCountry.getLocalName()).isEqualTo(UPDATED_LOCAL_NAME);
        assertThat(testCountry.getGovernmentForm()).isEqualTo(UPDATED_GOVERNMENT_FORM);
    }

    @Test
    @Transactional
    public void updateNonExistingCountry() throws Exception {
        int databaseSizeBeforeUpdate = countryRepository.findAll().size();

        // Create the Country
        CountryDTO countryDTO = countryMapper.toDto(country);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountryMockMvc.perform(put("/api/countries").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Country in the database
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCountry() throws Exception {
        // Initialize the database
        countryRepository.saveAndFlush(country);

        int databaseSizeBeforeDelete = countryRepository.findAll().size();

        // Delete the country
        restCountryMockMvc.perform(delete("/api/countries/{id}", country.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Country> countryList = countryRepository.findAll();
        assertThat(countryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
