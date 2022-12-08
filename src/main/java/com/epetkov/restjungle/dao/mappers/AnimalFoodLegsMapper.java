package com.epetkov.restjungle.dao.mappers;

import com.epetkov.restjungle.dao.interfaces.FoodDAO;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalFoodLegsMapper implements RowMapper<AnimalDTO> {

    private final FoodDAO foodDAO;

    public AnimalFoodLegsMapper(FoodDAO foodDAO) {

        this.foodDAO = foodDAO;
    }

    @Override
    public AnimalDTO mapRow(ResultSet rs, int numRow) throws SQLException {

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setName(rs.getString(1));
        animalDTO.setFoodDTO(foodDAO.getOneByID(rs.getInt(2)).getBody());
        animalDTO.setLegs(rs.getInt(3));

        return animalDTO;
    }
}
