package com.epetkov.restjungle.utils;

public final class SQLs {

    public static final String SELECT_ANIMALS_FOOD_LEGS = "SELECT name AS \"Name\", id_food AS \"FOOD\", " +
            "legs AS \"LEGS\" FROM jungle.animals";
    public static final String SELECT_ANIMAL_BY_ID = "SELECT * FROM jungle.animals WHERE id = ?";
    public static final String SELECT_ANIMAL_BY_NAME = "SELECT * FROM jungle.animals WHERE name = ?";
    public static final String SELECT_ANIMALS_BY_FOOD =
            "SELECT * FROM jungle.animals a WHERE ? IN (SELECT name FROM jungle.foods f WHERE a.id_food = f.id)";
    public static final String SELECT_LAST_ANIMAL = "SELECT TOP 1 * FROM jungle.animals ORDER BY id DESC";
    public static final String SELECT_FOOD_BY_ID = "SELECT * FROM jungle.foods WHERE id = ?";
    public static final String SELECT_FOOD_BY_NAME = "SELECT * FROM jungle.foods WHERE name = ?";
    public static final String SELECT_LAST_FOOD = "SELECT TOP 1 * FROM jungle.foods ORDER BY id DESC";
    public static final String SELECT_FAMILY_BY_ID = "SELECT * FROM jungle.animal_family WHERE id = ?";
    public static final String SELECT_FAMILY_BY_NAME = "SELECT * FROM jungle.animal_family WHERE name = ?";
    public static final String INSERT_NEW_FOOD = "INSERT INTO jungle.foods (id, name) VALUES ('%d', '%s')";
    public static final String INSERT_NEW_ANIMAL =
            "INSERT INTO jungle.animals (id, name, legs, id_food, id_family) VALUES ('%d', '%s', '%d', '%d', '%d')";
    public static final String DELETE_FOOD_BY_NAME = "DELETE FROM jungle.foods WHERE name = '%s'";
    public static final String DELETE_ANIMAL_BY_NAME = "DELETE FROM jungle.animals WHERE name = '%s'";

    public SQLs() {
    }
}
