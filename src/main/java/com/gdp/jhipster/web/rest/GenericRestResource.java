package com.gdp.jhipster.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gdp.jhipster.service.CountryQueryService;
import com.gdp.jhipster.service.CountryService;
import com.gdp.jhipster.service.dto.CountryCriteria;
import com.gdp.jhipster.service.dto.CountryDTO;

import io.github.jhipster.web.util.PaginationUtil;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/api/open")
public class GenericRestResource {

	private final Logger log = LoggerFactory.getLogger(GenericRestResource.class);
	private final CountryQueryService countryQueryService;
	private final CountryService countryService;
	
	public GenericRestResource( CountryService countryService, CountryQueryService countryQueryService) {
		this.countryQueryService = countryQueryService;
		this.countryService = countryService;
	}
	
	@GetMapping("/search-countries")
	@Timed
	public ResponseEntity<List<CountryDTO>> getAllCountriesForGdp(CountryCriteria criteria, Pageable pageable) {
		log.debug("REST request to get a page of Countries");
		Page<CountryDTO> page = countryQueryService.findByCriteria(criteria, pageable);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(httpHeaders).body(page.getContent());		
	}
	
	@GetMapping("/show-gdp/{id}")
	public ResponseEntity<CountryDTO> getCountryDetails(@PathVariable Long id) {
		log.debug("Get Country Details to Show GDP Information");
		CountryDTO countryDto = new CountryDTO();
		Optional<CountryDTO> countryData = countryService.findOne(id);
		return ResponseEntity.ok().body(countryData.orElse(countryDto));
	}
}
