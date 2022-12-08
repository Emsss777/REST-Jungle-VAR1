package com.epetkov.restjungle.dao.mappers;

import com.epetkov.restjungle.dao.interfaces.FamilyDAO;
import com.epetkov.restjungle.dao.interfaces.FoodDAO;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalMapper implements RowMapper<AnimalDTO> {

    private final FoodDAO foodDAO;
    private final FamilyDAO familyDAO;

    public AnimalMapper(FoodDAO foodDAO, FamilyDAO familyDAO) {

        this.foodDAO = foodDAO;
        this.familyDAO = familyDAO;
    }

    @Override
    public AnimalDTO mapRow(ResultSet rs, int numRow) throws SQLException {

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(rs.getInt(1));
        animalDTO.setName(rs.getString(2));
        animalDTO.setLegs(rs.getInt(3));
        animalDTO.setFoodDTO(foodDAO.getOneByID(rs.getInt(4)).getBody());
        animalDTO.setFamilyDTO(familyDAO.getOneByID(rs.getInt(5)).getBody());

        return animalDTO;
    }
}
