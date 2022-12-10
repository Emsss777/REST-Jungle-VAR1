package com.epetkov.restjungle.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {

    private Integer id;

    @ApiModelProperty(value = "This is the Animal Name", required = true)
    private String name;

    @ApiModelProperty(value = "This is the Number of Legs", required = true)
    private Integer legs;

    @ApiModelProperty(value = "This is the Food Name", required = true)
    private String food;

    private FoodDTO foodDTO;

    @ApiModelProperty(value = "This is the Family Name", required = true)
    private String family;

    private FamilyDTO familyDTO;

    public AnimalDTO() {
    }

    public AnimalDTO(Integer id, String name, Integer legs, FoodDTO foodDTO, FamilyDTO familyDTO) {
        this.id = id;
        this.name = name;
        this.legs = legs;
        this.foodDTO = foodDTO;
        this.familyDTO = familyDTO;
    }

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
