package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.*;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.data.entities.FoodEntity;
import com.epetkov.restjungle.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceImplTest {

    public static final Integer ID = 1;
    public static final String ANIMAL = "Koala";
    public static final String ANIMAL_FAIL = "Monkey";
    public static final Integer LEGS = 4;
    public static final String FOOD = "leaves";

    AnimalServiceImpl animalService;

    @Mock
    AnimalRepository animalRepository;

    @Mock
    FoodRepository foodRepository;

    @Mock
    FamilyRepository familyRepository;

    @Mock
    FoodEntityToFoodDTO foodEntityToFoodDTO;

    @Mock
    AnimalEntityToAnimalDTO animalEntityToAnimalDTO;

    @Mock
    FamilyEntityToFamilyDTO familyEntityToFamilyDTO;

    @Mock
    AnimalDtoToAnimalEntity animalDtoToAnimalEntity;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        animalService = new AnimalServiceImpl(animalRepository, foodRepository, familyRepository,
                                              foodEntityToFoodDTO, familyEntityToFamilyDTO,
                                              animalEntityToAnimalDTO, animalDtoToAnimalEntity);
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

        when(animalRepository.findOneByName(ANIMAL)).thenReturn(animalEntity);

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setName(animalEntity.getName());

        when(animalEntityToAnimalDTO.convert(animalEntity)).thenReturn(animalDTO);

        AnimalDTO foundAnimal = animalService.getAnimalByName(ANIMAL).getBody();

        assertNotNull(foundAnimal);
        verify(animalRepository, times(1)).findOneByName(anyString());
        verify(animalRepository, never()).findAll();
    }

    @Test
    public void testGetAnimalsByFoodName() {

        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName(FOOD);

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setFood(foodEntity);

        List<AnimalEntity> animals = Arrays.asList(animalEntity, animalEntity);

        when(animalRepository.findAll()).thenReturn(animals);

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(foodEntity.getName());

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setFoodDTO(foodDTO);

        when(animalEntityToAnimalDTO.convert(animalEntity)).thenReturn(animalDTO);

        List<AnimalDTO> animalDtoByFoodName = animalService.getAnimalsByFoodName(FOOD).getBody();

        assertNotNull(animalDtoByFoodName);
        assertEquals(2, animalDtoByFoodName.size());
    }

    @Test
    public void testDeleteAnimalByNameOK() {

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setId(ID);
        animalEntity.setName(ANIMAL);

        when(animalRepository.findOneByName(ANIMAL)).thenReturn(animalEntity);

        Boolean result = animalService.deleteAnimalByName(ANIMAL).getBody();

        verify(animalRepository, times(1)).findOneByName(anyString());
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testDeleteAnimalByNameFAIL() {

        Boolean result = animalService.deleteAnimalByName(ANIMAL_FAIL).getBody();

        verify(animalRepository, times(1)).findOneByName(anyString());
        assertEquals(Boolean.FALSE, result);
    }
}
