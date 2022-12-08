package com.epetkov.restjungle.utils;

public final class SQLs {

    public static final String SELECT_ANIMALS_FOOD_LEGS = "SELECT name AS \"Name\", id_food AS \"FOOD\", " +
            "legs AS \"LEGS\" FROM jungle.animals";
    public static final String SELECT_ANIMAL_BY_NAME = "SELECT * FROM jungle.animals WHERE name = ?";
    public static final String SELECT_FOOD_BY_ID = "SELECT * FROM jungle.foods WHERE id = ?";
    public static final String SELECT_FAMILY_BY_ID = "SELECT * FROM jungle.animal_family WHERE id = ?";
    public static final String SELECT_ANIMALS_BY_FOOD =
            "SELECT * FROM jungle.animals a WHERE ? IN (SELECT name FROM jungle.foods f WHERE a.id_food = f.id)";

    public SQLs() {
    }
}
