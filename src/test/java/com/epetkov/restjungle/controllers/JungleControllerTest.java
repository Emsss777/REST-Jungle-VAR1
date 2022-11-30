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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@WebAppConfiguration
@Transactional
public class JungleControllerTest {

    public static final String ANIMAL = "Ape";
    public static final Integer LEGS = 2;
    public static final String FOOD = "leaves";

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
}