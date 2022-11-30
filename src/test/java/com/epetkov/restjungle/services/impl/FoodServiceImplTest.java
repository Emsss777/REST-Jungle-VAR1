package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.services.interfaces.FoodService;
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
public class FoodServiceImplTest {

    public static final Integer ID = 5;
    public static final String FOOD = "nuts";

    @Autowired
    FoodService foodService;

    @Test
    public void testCreateNewFoodOK() {

        FoodDTO savedFoodDTO = foodService.createNewFood(ID, FOOD).getBody();

        assertNotNull(savedFoodDTO);
        assertEquals(ID, savedFoodDTO.getId());
        assertEquals(FOOD, savedFoodDTO.getName());
    }

    @Test
    public void testCreateNewFoodFAIL() {

        FoodDTO savedFoodDTO = foodService.createNewFood(3, FOOD).getBody();

        assertNull(savedFoodDTO);
    }
}