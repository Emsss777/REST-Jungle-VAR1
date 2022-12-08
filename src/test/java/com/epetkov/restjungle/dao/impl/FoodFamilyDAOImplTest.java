package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.dao.interfaces.FamilyDAO;
import com.epetkov.restjungle.dao.interfaces.FoodDAO;
import com.epetkov.restjungle.data.dto.FamilyDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@WebAppConfiguration
@Transactional
public class FoodFamilyDAOImplTest {

    @Autowired
    FoodDAO foodDAO;

    @Autowired
    FamilyDAO familyDAO;

    @Test
    public void testGetFoodByID() {

        FoodDTO foodDTO = foodDAO.getOneByID(1).getBody();

        assertNotNull(foodDTO);
        assertEquals("leaves", foodDTO.getName());
    }

    @Test
    public void testGetFoodByName() {

        // Todo: impl
    }

    @Test
    public void testGetFamilyByID() {

        FamilyDTO familyDTO = familyDAO.getOneByID(0).getBody();

        assertNotNull(familyDTO);
        assertEquals("mammal", familyDTO.getName());
    }

    @Test
    public void testGetFamilyByName() {

        // Todo: impl
    }
}
