package com.gdp.jhipster.domain.converter;

import javax.persistence.AttributeConverter;

import com.gdp.jhipster.domain.enumeration.Continent;

public class ContinentEnumConverter implements AttributeConverter<Continent, String>{

	@Override
	public String convertToDatabaseColumn(Continent continent) {
		return continent.getName();
	}

	@Override
	public Continent convertToEntityAttribute(String continentValue) {
		return Continent.getContinent(continentValue);
	}
}
