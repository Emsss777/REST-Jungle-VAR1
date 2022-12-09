package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.mappers.FoodMapper;
import com.epetkov.restjungle.dao.interfaces.FoodDAO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.utils.SQLs;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class FoodDAOImpl implements FoodDAO {

    private final JdbcTemplate jdbcTemplate;

    public FoodDAOImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseEntity<FoodDTO> getOneByID(Integer id) {

        try {
            FoodDTO foodDTO =
                    jdbcTemplate.queryForObject(SQLs.SELECT_FOOD_BY_ID, getFoodRowMapper(), id);

            return new ResponseEntity<>(foodDTO, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<FoodDTO> getOneByName(String name) {

        try {
            FoodDTO foodDTO =
                    jdbcTemplate.queryForObject(SQLs.SELECT_FOOD_BY_NAME, getFoodRowMapper(), name);

            return new ResponseEntity<>(foodDTO, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<FoodDTO> createNewFood(FoodDTO foodDTO) {

        FoodDTO confirmFood = this.getOneByName(foodDTO.getName()).getBody();
        if (confirmFood != null) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            String sqlQuery = String.format(SQLs.INSERT_NEW_FOOD, foodDTO.getId(), foodDTO.getName());

            jdbcTemplate.execute(sqlQuery);

            FoodDTO savedFood = jdbcTemplate.queryForObject(SQLs.SELECT_LAST_FOOD, getFoodRowMapper());

            return new ResponseEntity<>(savedFood, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteFoodByName(String name) {

        FoodDTO confirmFood = this.getOneByName(name).getBody();
        if (confirmFood == null) {

            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        try {
            String sqlQuery = String.format(SQLs.DELETE_FOOD_BY_NAME, name);

            jdbcTemplate.execute(sqlQuery);

            return new ResponseEntity<>(true, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    private RowMapper<FoodDTO> getFoodRowMapper() {

        return new FoodMapper();
    }
}
