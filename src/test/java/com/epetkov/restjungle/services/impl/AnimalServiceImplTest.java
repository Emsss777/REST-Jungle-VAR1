package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.AnimalEntityToAnimalDTO;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class AnimalServiceImplTest {

    public static final String ANIMAL = "Koala";
    public static final Integer LEGS = 4;
    public static final String FOOD = "leaves";

    AnimalServiceImpl animalService;

    @Mock
    AnimalRepository animalRepository;

    @Mock
    AnimalEntityToAnimalDTO animalEntityToAnimalDTO;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        animalService = new AnimalServiceImpl(animalRepository, animalEntityToAnimalDTO);
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

    @Test
    public void testGetAnimalByName() {

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setName(ANIMAL);

        when(animalRepository.findAnimalByName(ANIMAL)).thenReturn(animalEntity);

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setName(animalEntity.getName());

        when(animalEntityToAnimalDTO.convert(animalEntity)).thenReturn(animalDTO);

        AnimalDTO foundAnimal = animalService.getAnimalByName(ANIMAL).getBody();

        assertNotNull(foundAnimal);
        verify(animalRepository, times(1)).findAnimalByName(anyString());
        verify(animalRepository, never()).findAll();
    }
}
