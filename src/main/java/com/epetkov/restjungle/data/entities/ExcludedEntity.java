package com.epetkov.restjungle.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "study_excluded", schema = "jungle")
public class ExcludedEntity {

    @Id
    @Column(name = "id_animal_excluded")
    private Integer idAnimalExcluded;

    public ExcludedEntity() {
    }

    public Integer getIdAnimalExcluded() {
        return idAnimalExcluded;
    }

    public void setIdAnimalExcluded(Integer idAnimalExcluded) {
        this.idAnimalExcluded = idAnimalExcluded;
    }
}
