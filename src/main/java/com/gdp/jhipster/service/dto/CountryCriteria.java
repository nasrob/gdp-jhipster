package com.gdp.jhipster.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.gdp.jhipster.domain.enumeration.Continent;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.gdp.jhipster.domain.Country} entity. This class is used
 * in {@link com.gdp.jhipster.web.rest.CountryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /countries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CountryCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Continent
     */
    public static class ContinentFilter extends Filter<Continent> {

        public ContinentFilter() {
        }

        public ContinentFilter(ContinentFilter filter) {
            super(filter);
        }

        @Override
        public ContinentFilter copy() {
            return new ContinentFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private ContinentFilter continent;

    private StringFilter region;

    private FloatFilter surfaceArea;

    private IntegerFilter population;

    private FloatFilter lifeExpectancy;

    private StringFilter localName;

    private StringFilter governmentForm;

    private LongFilter cityId;

    private LongFilter countryLanguageId;

    public CountryCriteria() {
    }

    public CountryCriteria(CountryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.continent = other.continent == null ? null : other.continent.copy();
        this.region = other.region == null ? null : other.region.copy();
        this.surfaceArea = other.surfaceArea == null ? null : other.surfaceArea.copy();
        this.population = other.population == null ? null : other.population.copy();
        this.lifeExpectancy = other.lifeExpectancy == null ? null : other.lifeExpectancy.copy();
        this.localName = other.localName == null ? null : other.localName.copy();
        this.governmentForm = other.governmentForm == null ? null : other.governmentForm.copy();
        this.cityId = other.cityId == null ? null : other.cityId.copy();
        this.countryLanguageId = other.countryLanguageId == null ? null : other.countryLanguageId.copy();
    }

    @Override
    public CountryCriteria copy() {
        return new CountryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public ContinentFilter getContinent() {
        return continent;
    }

    public void setContinent(ContinentFilter continent) {
        this.continent = continent;
    }

    public StringFilter getRegion() {
        return region;
    }

    public void setRegion(StringFilter region) {
        this.region = region;
    }

    public FloatFilter getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(FloatFilter surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public IntegerFilter getPopulation() {
        return population;
    }

    public void setPopulation(IntegerFilter population) {
        this.population = population;
    }

    public FloatFilter getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(FloatFilter lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public StringFilter getLocalName() {
        return localName;
    }

    public void setLocalName(StringFilter localName) {
        this.localName = localName;
    }

    public StringFilter getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(StringFilter governmentForm) {
        this.governmentForm = governmentForm;
    }

    public LongFilter getCityId() {
        return cityId;
    }

    public void setCityId(LongFilter cityId) {
        this.cityId = cityId;
    }

    public LongFilter getCountryLanguageId() {
        return countryLanguageId;
    }

    public void setCountryLanguageId(LongFilter countryLanguageId) {
        this.countryLanguageId = countryLanguageId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CountryCriteria that = (CountryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(name, that.name) &&
            Objects.equals(continent, that.continent) &&
            Objects.equals(region, that.region) &&
            Objects.equals(surfaceArea, that.surfaceArea) &&
            Objects.equals(population, that.population) &&
            Objects.equals(lifeExpectancy, that.lifeExpectancy) &&
            Objects.equals(localName, that.localName) &&
            Objects.equals(governmentForm, that.governmentForm) &&
            Objects.equals(cityId, that.cityId) &&
            Objects.equals(countryLanguageId, that.countryLanguageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        name,
        continent,
        region,
        surfaceArea,
        population,
        lifeExpectancy,
        localName,
        governmentForm,
        cityId,
        countryLanguageId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (continent != null ? "continent=" + continent + ", " : "") +
                (region != null ? "region=" + region + ", " : "") +
                (surfaceArea != null ? "surfaceArea=" + surfaceArea + ", " : "") +
                (population != null ? "population=" + population + ", " : "") +
                (lifeExpectancy != null ? "lifeExpectancy=" + lifeExpectancy + ", " : "") +
                (localName != null ? "localName=" + localName + ", " : "") +
                (governmentForm != null ? "governmentForm=" + governmentForm + ", " : "") +
                (cityId != null ? "cityId=" + cityId + ", " : "") +
                (countryLanguageId != null ? "countryLanguageId=" + countryLanguageId + ", " : "") +
            "}";
    }

}
