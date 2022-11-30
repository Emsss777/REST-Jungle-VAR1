package com.epetkov.restjungle.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "animals", schema = "jungle")
public class AnimalEntity extends BaseEntity {

    @Column(name = "legs")
    private Integer legs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_food", referencedColumnName = "id")
    private FoodEntity food;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_family", referencedColumnName = "id")
    private FamilyEntity family;

    public AnimalEntity() {
    }

    public Integer getLegs() {
        return legs;
    }

    public void setLegs(Integer legs) {
        this.legs = legs;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public FamilyEntity getFamily() {
        return family;
    }

    public void setFamily(FamilyEntity family) {
        this.family = family;
    }
}
