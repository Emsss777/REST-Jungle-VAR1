package com.epetkov.restjungle.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CountLegsMapper implements RowMapper<Map<String, Integer>> {

    @Override
    public Map<String, Integer> mapRow(ResultSet rs, int numRow) throws SQLException {

        Map<String, Integer> resultMap = new HashMap<>();
        do {
            resultMap.put(rs.getString(1), rs.getInt("SUM_OF_LEGS"));

        } while (rs.next());

        return resultMap;
    }
}
