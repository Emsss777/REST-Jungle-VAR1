package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.interfaces.*;
import com.epetkov.restjungle.dao.mappers.AnimalMapper;
import com.epetkov.restjungle.dao.mappers.AnimalFoodLegsMapper;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.utils.SQLs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.util.List;

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

        } catch (Exception ex) {

            ex.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<AnimalDTO> getOneByID(Integer id) {

        // Todo: impl
        return null;
    }

    @Override
    public ResponseEntity<AnimalDTO> getOneByName(String name) {

        try {
            AnimalDTO animalDTO =
                    jdbcTemplate.queryForObject(SQLs.SELECT_ANIMAL_BY_NAME, getAnimalRowMapper(), name);

            return new ResponseEntity<>(animalDTO, HttpStatus.OK);

        } catch (Exception ex) {

            ex.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food) {

        try {
            List<AnimalDTO> animalDTOList =
                    jdbcTemplate.query(SQLs.SELECT_ANIMALS_BY_FOOD, getAnimalRowMapper(), food);

            return new ResponseEntity<>(animalDTOList, HttpStatus.OK);

        } catch (Exception ex) {

            ex.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private RowMapper<AnimalDTO> getAnimalFoodLegsRowMapper() {

        return new AnimalFoodLegsMapper(foodDAO);
    }

    private RowMapper<AnimalDTO> getAnimalRowMapper() {

        return new AnimalMapper(foodDAO, familyDAO);
    }
}
