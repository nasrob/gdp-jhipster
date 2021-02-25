package com.gdp.jhipster.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.gdp.jhipster.domain.enumeration.EducationType;

/**
 * A DTO for the {@link com.gdp.jhipster.domain.School} entity.
 */
public class SchoolDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private EducationType eduType;

    @NotNull
    @Min(value = 5)
    @Max(value = 99)
    private Integer noOfRooms;

    
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

    public EducationType getEduType() {
        return eduType;
    }

    public void setEduType(EducationType eduType) {
        this.eduType = eduType;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SchoolDTO)) {
            return false;
        }

        return id != null && id.equals(((SchoolDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SchoolDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", eduType='" + getEduType() + "'" +
            ", noOfRooms=" + getNoOfRooms() +
            "}";
    }
}
