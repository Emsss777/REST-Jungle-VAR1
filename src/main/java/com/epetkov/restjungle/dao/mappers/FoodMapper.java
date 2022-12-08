package com.epetkov.restjungle.dao.mappers;

import com.epetkov.restjungle.data.dto.FoodDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodMapper implements RowMapper<FoodDTO> {

    @Override
    public FoodDTO mapRow(ResultSet rs, int numRow) throws SQLException {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(rs.getInt(1));
        foodDTO.setName(rs.getString(2));

        return foodDTO;
    }
}
