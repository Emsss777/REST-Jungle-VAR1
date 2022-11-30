package com.epetkov.restjungle.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "foods", schema = "jungle")
public class FoodEntity extends BaseEntity {

    public FoodEntity() {
    }
}
