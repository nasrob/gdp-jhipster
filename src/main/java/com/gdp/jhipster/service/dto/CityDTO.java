package com.gdp.jhipster.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.gdp.jhipster.domain.City} entity.
 */
public class CityDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 35)
    private String name;

    @NotNull
    @Size(max = 20)
    private String district;

    @NotNull
    private Integer population;


    private Long countryId;

    private String countryName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CityDTO)) {
            return false;
        }

        return id != null && id.equals(((CityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", district='" + getDistrict() + "'" +
            ", population=" + getPopulation() +
            ", countryId=" + getCountryId() +
            ", countryName='" + getCountryName() + "'" +
            "}";
    }
}
