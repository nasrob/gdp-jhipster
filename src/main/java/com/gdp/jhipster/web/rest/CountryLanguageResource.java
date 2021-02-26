package com.gdp.jhipster.web.rest;

import com.gdp.jhipster.service.CountryLanguageService;
import com.gdp.jhipster.web.rest.errors.BadRequestAlertException;
import com.gdp.jhipster.service.dto.CountryLanguageDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gdp.jhipster.domain.CountryLanguage}.
 */
@RestController
@RequestMapping("/api")
public class CountryLanguageResource {

    private final Logger log = LoggerFactory.getLogger(CountryLanguageResource.class);

    private static final String ENTITY_NAME = "countryLanguage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CountryLanguageService countryLanguageService;

    public CountryLanguageResource(CountryLanguageService countryLanguageService) {
        this.countryLanguageService = countryLanguageService;
    }

    /**
     * {@code POST  /country-languages} : Create a new countryLanguage.
     *
     * @param countryLanguageDTO the countryLanguageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new countryLanguageDTO, or with status {@code 400 (Bad Request)} if the countryLanguage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/country-languages")
    public ResponseEntity<CountryLanguageDTO> createCountryLanguage(@Valid @RequestBody CountryLanguageDTO countryLanguageDTO) throws URISyntaxException {
        log.debug("REST request to save CountryLanguage : {}", countryLanguageDTO);
        if (countryLanguageDTO.getId() != null) {
            throw new BadRequestAlertException("A new countryLanguage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountryLanguageDTO result = countryLanguageService.save(countryLanguageDTO);
        return ResponseEntity.created(new URI("/api/country-languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /country-languages} : Updates an existing countryLanguage.
     *
     * @param countryLanguageDTO the countryLanguageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated countryLanguageDTO,
     * or with status {@code 400 (Bad Request)} if the countryLanguageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the countryLanguageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/country-languages")
    public ResponseEntity<CountryLanguageDTO> updateCountryLanguage(@Valid @RequestBody CountryLanguageDTO countryLanguageDTO) throws URISyntaxException {
        log.debug("REST request to update CountryLanguage : {}", countryLanguageDTO);
        if (countryLanguageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CountryLanguageDTO result = countryLanguageService.save(countryLanguageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, countryLanguageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /country-languages} : get all the countryLanguages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of countryLanguages in body.
     */
    @GetMapping("/country-languages")
    public ResponseEntity<List<CountryLanguageDTO>> getAllCountryLanguages(Pageable pageable) {
        log.debug("REST request to get a page of CountryLanguages");
        Page<CountryLanguageDTO> page = countryLanguageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /country-languages/:id} : get the "id" countryLanguage.
     *
     * @param id the id of the countryLanguageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the countryLanguageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/country-languages/{id}")
    public ResponseEntity<CountryLanguageDTO> getCountryLanguage(@PathVariable Long id) {
        log.debug("REST request to get CountryLanguage : {}", id);
        Optional<CountryLanguageDTO> countryLanguageDTO = countryLanguageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(countryLanguageDTO);
    }

    /**
     * {@code DELETE  /country-languages/:id} : delete the "id" countryLanguage.
     *
     * @param id the id of the countryLanguageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/country-languages/{id}")
    public ResponseEntity<Void> deleteCountryLanguage(@PathVariable Long id) {
        log.debug("REST request to delete CountryLanguage : {}", id);
        countryLanguageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
