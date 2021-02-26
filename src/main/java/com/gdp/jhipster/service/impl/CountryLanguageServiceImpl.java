package com.gdp.jhipster.service.impl;

import com.gdp.jhipster.service.CountryLanguageService;
import com.gdp.jhipster.domain.CountryLanguage;
import com.gdp.jhipster.repository.CountryLanguageRepository;
import com.gdp.jhipster.service.dto.CountryLanguageDTO;
import com.gdp.jhipster.service.mapper.CountryLanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CountryLanguage}.
 */
@Service
@Transactional
public class CountryLanguageServiceImpl implements CountryLanguageService {

    private final Logger log = LoggerFactory.getLogger(CountryLanguageServiceImpl.class);

    private final CountryLanguageRepository countryLanguageRepository;

    private final CountryLanguageMapper countryLanguageMapper;

    public CountryLanguageServiceImpl(CountryLanguageRepository countryLanguageRepository, CountryLanguageMapper countryLanguageMapper) {
        this.countryLanguageRepository = countryLanguageRepository;
        this.countryLanguageMapper = countryLanguageMapper;
    }

    @Override
    public CountryLanguageDTO save(CountryLanguageDTO countryLanguageDTO) {
        log.debug("Request to save CountryLanguage : {}", countryLanguageDTO);
        CountryLanguage countryLanguage = countryLanguageMapper.toEntity(countryLanguageDTO);
        countryLanguage = countryLanguageRepository.save(countryLanguage);
        return countryLanguageMapper.toDto(countryLanguage);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CountryLanguageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CountryLanguages");
        return countryLanguageRepository.findAll(pageable)
            .map(countryLanguageMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CountryLanguageDTO> findOne(Long id) {
        log.debug("Request to get CountryLanguage : {}", id);
        return countryLanguageRepository.findById(id)
            .map(countryLanguageMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CountryLanguage : {}", id);
        countryLanguageRepository.deleteById(id);
    }
}
