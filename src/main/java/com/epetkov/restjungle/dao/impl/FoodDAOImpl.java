package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.mappers.FoodMapper;
import com.epetkov.restjungle.dao.interfaces.FoodDAO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.utils.SQLs;
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

        } catch (Exception ex) {

            ex.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<FoodDTO> getOneByName(String name) {

        // Todo: impl
        return null;
    }

    private RowMapper<FoodDTO> getFoodRowMapper() {

        return new FoodMapper();
    }
}
