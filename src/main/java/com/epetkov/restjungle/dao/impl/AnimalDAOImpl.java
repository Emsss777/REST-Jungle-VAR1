package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.interfaces.*;
import com.epetkov.restjungle.dao.mappers.*;
import com.epetkov.restjungle.data.dto.*;
import com.epetkov.restjungle.utils.SQLs;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class AnimalDAOImpl implements AnimalDAO {

    private final JdbcTemplate jdbcTemplate;
    private final FoodDAO foodDAO;
    private final FamilyDAO familyDAO;

    public AnimalDAOImpl(JdbcTemplate jdbcTemplate, FoodDAO foodDAO, FamilyDAO familyDAO) {

        this.jdbcTemplate = jdbcTemplate;
        this.foodDAO = foodDAO;
        this.familyDAO = familyDAO;
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsWithFoodAndLegs() {

        try {
            List<AnimalDTO> animalDTOList =
                    jdbcTemplate.query(SQLs.SELECT_ANIMALS_FOOD_LEGS, getAnimalFoodLegsRowMapper());

            return new ResponseEntity<>(animalDTOList, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<AnimalDTO> getOneByID(Integer id) {

        try {
            AnimalDTO animalDTO =
                    jdbcTemplate.queryForObject(SQLs.SELECT_ANIMAL_BY_ID, getAnimalRowMapper(), id);

            return new ResponseEntity<>(animalDTO, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<AnimalDTO> getOneByName(String name) {

        try {
            AnimalDTO animalDTO =
                    jdbcTemplate.queryForObject(SQLs.SELECT_ANIMAL_BY_NAME, getAnimalRowMapper(), name);

            return new ResponseEntity<>(animalDTO, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food) {

        try {
            List<AnimalDTO> animalDTOList =
                    jdbcTemplate.query(SQLs.SELECT_ANIMALS_BY_FOOD, getAnimalRowMapper(), food);

            return new ResponseEntity<>(animalDTOList, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<AnimalDTO> createOrUpdateAnimal(AnimalDTO animalDTO, String createOrUpdate) {

        String animalName = animalDTO.getName();
        String foodName = animalDTO.getFoodDTO().getName();
        String familyName = animalDTO.getFamilyDTO().getName();

        FoodDTO confirmFood = foodDAO.getOneByName(foodName).getBody();
        if (confirmFood == null) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        FamilyDTO confirmFamily = familyDAO.getOneByName(familyName).getBody();
        if (confirmFamily == null) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        AnimalDTO confirmAnimal = this.getOneByName(animalName).getBody();
        if (confirmAnimal != null) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        String sqlQuery = null;
        try {
            if (Objects.equals(createOrUpdate, "create")) {

                sqlQuery = String.format(SQLs.INSERT_NEW_ANIMAL, animalDTO.getId(), animalDTO.getName(),
                                         animalDTO.getLegs(), confirmFood.getId(), confirmFamily.getId());

            } else if (Objects.equals(createOrUpdate, "update")) {

                sqlQuery = String.format(SQLs.UPDATE_ANIMAL, animalDTO.getName(), animalDTO.getLegs(),
                                         confirmFood.getId(), confirmFamily.getId(), animalDTO.getId());
            }

            jdbcTemplate.execute(Objects.requireNonNull(sqlQuery));

            AnimalDTO savedAnimal = jdbcTemplate.queryForObject(SQLs.SELECT_LAST_ANIMAL, getAnimalRowMapper());

            return new ResponseEntity<>(savedAnimal, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteAnimalByName(String name) {

        AnimalDTO confirmAnimal = this.getOneByName(name).getBody();
        if (confirmAnimal == null) {

            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        try {
            String sqlQuery = String.format(SQLs.DELETE_ANIMAL_BY_NAME, name);

            jdbcTemplate.execute(sqlQuery);

            return new ResponseEntity<>(true, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Map<String, Integer>> countLegsByFoodAndFamilyNames(String name) {

        String sqlQuery = null;
        if (Objects.equals(name, "food")) {

            sqlQuery = SQLs.COUNT_LEGS_BY_FOOD;

        } else if (Objects.equals(name, "family")) {

            sqlQuery = SQLs.COUNT_LEGS_BY_FAMILY;
        }

        try {
            Map<String, Integer> resultMap =
                    jdbcTemplate.queryForObject(Objects.requireNonNull(sqlQuery), getCountLegsRowMapper());

            return new ResponseEntity<>(resultMap, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private RowMapper<AnimalDTO> getAnimalFoodLegsRowMapper() {

        return new AnimalFoodLegsMapper(foodDAO);
    }

    private RowMapper<AnimalDTO> getAnimalRowMapper() {

        return new AnimalMapper(foodDAO, familyDAO);
    }

    private static RowMapper<Map<String, Integer>> getCountLegsRowMapper() {

        return new CountLegsMapper();
    }
}
