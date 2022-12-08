package com.epetkov.restjungle.dao.mappers;

import com.epetkov.restjungle.data.dto.FamilyDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyMapper implements RowMapper<FamilyDTO> {

    @Override
    public FamilyDTO mapRow(ResultSet rs, int numRow) throws SQLException {

        FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setId(rs.getInt(1));
        familyDTO.setName(rs.getString(2));

        return familyDTO;
    }
}
