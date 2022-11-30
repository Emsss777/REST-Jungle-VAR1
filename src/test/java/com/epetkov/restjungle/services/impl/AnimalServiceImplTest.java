package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.AnimalDtoToAnimalEntity;
import com.epetkov.restjungle.data.converters.AnimalEntityToAnimalDTO;
import com.epetkov.restjungle.data.converters.FamilyEntityToFamilyDTO;
import com.epetkov.restjungle.data.converters.FoodEntityToFoodDTO;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.data.entities.FoodEntity;
import com.epetkov.restjungle.repositories.AnimalRepository;
import com.epetkov.restjungle.repositories.FamilyRepository;
import com.epetkov.restjungle.repositories.FoodRepository;
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

        when(animalRepository.findAnimalByName(ANIMAL)).thenReturn(animalEntity);

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setName(animalEntity.getName());

        when(animalEntityToAnimalDTO.convert(animalEntity)).thenReturn(animalDTO);

        AnimalDTO foundAnimal = animalService.getAnimalByName(ANIMAL).getBody();

        assertNotNull(foundAnimal);
        verify(animalRepository, times(1)).findAnimalByName(anyString());
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
}
