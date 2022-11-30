package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.data.entities.FoodEntity;
import com.epetkov.restjungle.repositories.AnimalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class AnimalServiceImplTest {

    public static final String ANIMAL = "Koala";
    public static final Integer LEGS = 4;
    public static final String FOOD = "leaves";

    AnimalServiceImpl animalService;

    @Mock
    AnimalRepository animalRepository;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        animalService = new AnimalServiceImpl(animalRepository);
    }

    @Test
    public void testGetAllAnimalsWithFoodAndLegs() {

        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName(FOOD);

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setName(ANIMAL);
        animalEntity.setFood(foodEntity);
        animalEntity.setLegs(LEGS);

        List<AnimalEntity> animals = Arrays.asList(animalEntity, animalEntity, animalEntity);

        when(animalRepository.findAll()).thenReturn(animals);

        List<AnimalDTO> allAnimalDTOs = animalService.getAllAnimalsWithFoodAndLegs().getBody();

        assertNotNull(allAnimalDTOs);
        assertEquals(3, allAnimalDTOs.size());
    }
}