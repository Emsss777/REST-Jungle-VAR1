package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.utils.URLc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@WebAppConfiguration
@Transactional
public class JungleControllerTest {

    public static final String ANIMAL = "Ape";
    public static final String ANIMAL_NEW = "Koala";
    public static final Integer LEGS = 2;
    public static final String FOOD = "leaves";
    public static final String FOOD_NEW = "nuts";
    public static final String FAMILY = "mammal";
    public static final String FAMILY_FAIL = "fish";

    @Autowired
    protected WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;

    @Before
    public void setUp()  {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllAnimalsWithFoodLegs() throws Exception {

        mockMvc.perform(
                        get(URLc.J_ANIMALS_URL + URLc.FOOD_URL + URLc.LEGS_URL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[1].name").value(ANIMAL))
                .andExpect(jsonPath("$[1].food").value(FOOD))
                .andExpect(jsonPath("$[1].legs").value(LEGS));
    }

    @Test
    public void testGetAnimalByName() throws Exception  {

        mockMvc.perform(
                        get(URLc.J_ANIMALS_URL + URLc.NAME_PARAM, ANIMAL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(ANIMAL))
                .andExpect(jsonPath("$.legs").value(LEGS))
                .andExpect(jsonPath("$.foodDTO.id").value(1))
                .andExpect(jsonPath("$.foodDTO.name").value(FOOD))
                .andExpect(jsonPath("$.familyDTO.id").value(0))
                .andExpect(jsonPath("$.familyDTO.name").value(FAMILY));
    }

    @Test
    public void testGetAnimalsByFoodName() throws Exception  {

        mockMvc.perform(
                        get(URLc.J_ANIMALS_URL + URLc.FOOD_URL + URLc.FOOD_PARAM, FOOD)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value(ANIMAL))
                .andExpect(jsonPath("$[0].legs").value(2))
                .andExpect(jsonPath("$[0].foodDTO.id").value(1))
                .andExpect(jsonPath("$[0].foodDTO.name").value(FOOD))
                .andExpect(jsonPath("$[0].familyDTO.id").value(0))
                .andExpect(jsonPath("$[0].familyDTO.name").value(FAMILY));
    }

    @Test
    public void testCreateNewFoodOK() throws Exception {

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.FOOD_URL + URLc.ID_PARAM +
                                URLc.FOOD_PARAM, 5, FOOD_NEW)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value(FOOD_NEW));
    }

    @Test
    public void testCreateNewFoodFAIL() throws Exception {

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.FOOD_URL + URLc.ID_PARAM +
                                URLc.FOOD_PARAM, 3, FOOD_NEW)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateNewAnimalOK() throws Exception {

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.ID_PARAM + URLc.NAME_PARAM +
                                        URLc.LEGS_PARAM + URLc.FOOD_PARAM + URLc.FAMILY_PARAM,
                                7, ANIMAL_NEW, 4, FOOD, FAMILY)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.name").value(ANIMAL_NEW))
                .andExpect(jsonPath("$.legs").value(4))
                .andExpect(jsonPath("$.foodDTO.id").value(1))
                .andExpect(jsonPath("$.foodDTO.name").value(FOOD))
                .andExpect(jsonPath("$.familyDTO.id").value(0))
                .andExpect(jsonPath("$.familyDTO.name").value(FAMILY));
    }

    @Test
    public void testCreateNewAnimalFAIL() throws Exception {

        mockMvc.perform(
                        post(URLc.J_ANIMALS_URL + URLc.ID_PARAM + URLc.NAME_PARAM +
                                        URLc.LEGS_PARAM + URLc.FOOD_PARAM + URLc.FAMILY_PARAM,
                                7, ANIMAL_NEW, 4, FOOD, FAMILY_FAIL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
