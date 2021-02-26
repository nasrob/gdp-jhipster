package com.gdp.jhipster.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.gdp.jhipster.domain.enumeration.Continent;

/**
 * A DTO for the {@link com.gdp.jhipster.domain.Country} entity.
 */
public class CountryDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 3)
    private String code;

    @NotNull
    @Size(max = 52)
    private String name;

    @NotNull
    private Continent continent;

    @NotNull
    @Size(max = 26)
    private String region;

    @NotNull
    private Float surfaceArea;

    @NotNull
    private Integer population;

    private Float lifeExpectancy;

    @NotNull
    @Size(max = 45)
    private String localName;

    @NotNull
    @Size(max = 45)
    private String governmentForm;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountryDTO)) {
            return false;
        }

        return id != null && id.equals(((CountryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountryDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", continent='" + getContinent() + "'" +
            ", region='" + getRegion() + "'" +
            ", surfaceArea=" + getSurfaceArea() +
            ", population=" + getPopulation() +
            ", lifeExpectancy=" + getLifeExpectancy() +
            ", localName='" + getLocalName() + "'" +
            ", governmentForm='" + getGovernmentForm() + "'" +
            "}";
    }
}
