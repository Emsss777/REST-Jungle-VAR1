package com.epetkov.restjungle.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "animal_family", schema = "jungle")
public class FamilyEntity extends BaseEntity {

    public FamilyEntity() {
    }
}
