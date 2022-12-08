package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.dao.interfaces.FamilyDAO;
import com.epetkov.restjungle.dao.mappers.FamilyMapper;
import com.epetkov.restjungle.data.dto.FamilyDTO;
import com.epetkov.restjungle.utils.SQLs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class FamilyDAOImpl implements FamilyDAO {

    private final JdbcTemplate jdbcTemplate;

    public FamilyDAOImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseEntity<FamilyDTO> getOneByID(Integer id) {

        try {
            FamilyDTO familyDTO =
                    jdbcTemplate.queryForObject(SQLs.SELECT_FAMILY_BY_ID, getFamilyRowMapper(), id);

            return new ResponseEntity<>(familyDTO, HttpStatus.OK);

        } catch (Exception ex) {

            ex.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<FamilyDTO> getOneByName(String name) {

        // Todo: impl
        return null;
    }

    private RowMapper<FamilyDTO> getFamilyRowMapper() {

        return new FamilyMapper();
    }
}
