package com.epetkov.restjungle.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {

    private Integer id;
    private String name;
    private Integer legs;
    private String food;
    private FoodDTO foodDTO;
    private String family;
    private FamilyDTO familyDTO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLegs() {
        return legs;
    }

    public void setLegs(Integer legs) {
        this.legs = legs;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public FoodDTO getFoodDTO() {
        return foodDTO;
    }

    public void setFoodDTO(FoodDTO foodDTO) {
        this.foodDTO = foodDTO;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public FamilyDTO getFamilyDTO() {
        return familyDTO;
    }

    public void setFamilyDTO(FamilyDTO familyDTO) {
        this.familyDTO = familyDTO;
    }
}
