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
import java.util.Objects;

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

        FoodDTO foodDTO = foodDAO.getOneByName("leaves").getBody();

        assertNotNull(foodDTO);
        assertEquals((Integer) 1, foodDTO.getId());
        assertEquals("leaves", foodDTO.getName());
    }

   @Test
    public void testCreateNewFoodOK() {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(5);
        foodDTO.setName("honey");

        FoodDTO savedFood = foodDAO.createNewFood(foodDTO).getBody();

        assertNotNull(savedFood);
        assertEquals((Integer) 5, savedFood.getId());
        assertEquals("honey", savedFood.getName());

       foodDAO.deleteFoodByName(savedFood.getName()).getBody();
    }

    @Test
    public void testCreateNewFoodFAIL() {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(1);
        foodDTO.setName("leaves");

        FoodDTO savedFood = foodDAO.createNewFood(foodDTO).getBody();

        assertNull(savedFood);
    }

    @Test
    public void testDeleteFoodByNameOK() {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(5);
        foodDTO.setName("honey");

        FoodDTO savedFood = foodDAO.createNewFood(foodDTO).getBody();

        Boolean result = foodDAO.deleteFoodByName(Objects.requireNonNull(savedFood).getName()).getBody();

        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testDeleteFoodByNameFAIL() {

        Boolean result = foodDAO.deleteFoodByName("mouse").getBody();

        assertEquals(Boolean.FALSE, result);
    }

    @Test
    public void testGetFamilyByID() {

        FamilyDTO familyDTO = familyDAO.getOneByID(0).getBody();

        assertNotNull(familyDTO);
        assertEquals("mammal", familyDTO.getName());
    }

    @Test
    public void testGetFamilyByName() {

        FamilyDTO familyDTO = familyDAO.getOneByName("mammal").getBody();

        assertNotNull(familyDTO);
        assertEquals((Integer) 0, familyDTO.getId());
        assertEquals("mammal", familyDTO.getName());
    }
}
