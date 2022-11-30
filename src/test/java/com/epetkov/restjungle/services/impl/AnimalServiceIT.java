package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.services.interfaces.AnimalService;
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
public class AnimalServiceIT {

    public static final Integer ID = 7;
    public static final String ANIMAL = "Koala";
    public static final Integer LEGS = 4;
    public static final String FOOD = "leaves";
    public static final String FAMILY = "mammal";
    public static final String FAMILY_FAIL = "fish";

    @Autowired
    AnimalService animalService;

    @Test
    public void testCreateNewAnimalOK() {

        AnimalDTO savedAnimalDTO = animalService.createNewAnimal(ID, ANIMAL, LEGS, FOOD, FAMILY).getBody();

        assertNotNull(savedAnimalDTO);
        assertEquals(ID, savedAnimalDTO.getId());
        assertEquals(ANIMAL, savedAnimalDTO.getName());
        assertEquals(LEGS, savedAnimalDTO.getLegs());
        assertEquals(FOOD, savedAnimalDTO.getFoodDTO().getName());
        assertEquals(FAMILY, savedAnimalDTO.getFamilyDTO().getName());
    }

    @Test
    public void testCreateNewAnimalFAIL() {

        AnimalDTO savedAnimalDTO = animalService.createNewAnimal(ID, ANIMAL, LEGS, FOOD, FAMILY_FAIL).getBody();

        assertNull(savedAnimalDTO);
    }
}
