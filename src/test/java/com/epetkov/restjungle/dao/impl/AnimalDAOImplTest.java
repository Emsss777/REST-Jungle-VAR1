package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.dao.interfaces.AnimalDAO;
import com.epetkov.restjungle.data.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@WebAppConfiguration
@Transactional
public class AnimalDAOImplTest {

    @Autowired
    AnimalDAO animalDAO;

    @Test
    public void testGetAllAnimalsWithFoodAndLegs() {

        List<AnimalDTO> animalDTOList = animalDAO.getAllAnimalsWithFoodAndLegs().getBody();

        assertNotNull(animalDTOList);
        assertEquals(7, animalDTOList.size());
    }

    @Test
    public void testGetAnimalByID() {

        AnimalDTO animalDTO = animalDAO.getOneByID(2).getBody();

        assertNotNull(animalDTO);
        assertEquals("Deer", animalDTO.getName());
        assertEquals((Integer) 4, animalDTO.getLegs());
        assertEquals("leaves", animalDTO.getFoodDTO().getName());
        assertEquals("mammal", animalDTO.getFamilyDTO().getName());
    }

    @Test
    public void testGetAnimalByName() {

        AnimalDTO animalDTO = animalDAO.getOneByName("Deer").getBody();

        assertNotNull(animalDTO);
        assertEquals((Integer) 2, animalDTO.getId());
        assertEquals((Integer) 4, animalDTO.getLegs());
        assertEquals("leaves", animalDTO.getFoodDTO().getName());
        assertEquals("mammal", animalDTO.getFamilyDTO().getName());
    }

    @Test
    public void testGetAnimalsByFoodName() {

        List<AnimalDTO> animalDTOList = animalDAO.getAnimalsByFoodName("leaves").getBody();

        assertNotNull(animalDTOList);
        assertEquals(2, animalDTOList.size());
    }

    @Test
    public void testCreateNewAnimalOK() {

        AnimalDTO animalDTO =
                new AnimalDTO(7, "Donkey", 4, new FoodDTO("leaves"), new FamilyDTO("mammal"));

        AnimalDTO savedAnimal = animalDAO.createNewAnimal(animalDTO).getBody();

        assertNotNull(savedAnimal);
        assertEquals("Donkey", savedAnimal.getName());
        assertEquals((Integer) 1, savedAnimal.getFoodDTO().getId());
        assertEquals((Integer) 0, savedAnimal.getFamilyDTO().getId());

        animalDAO.deleteAnimalByName(Objects.requireNonNull(savedAnimal).getName()).getBody();
    }

    @Test
    public void testCreateNewAnimalFAIL() {

        AnimalDTO animalDTO =
                new AnimalDTO(7, "Deer", 4, new FoodDTO("leaves"), new FamilyDTO("mammal"));

        AnimalDTO savedAnimal = animalDAO.createNewAnimal(animalDTO).getBody();

        assertNull(savedAnimal);
    }

    @Test
    public void testDeleteAnimalByNameOK() {

        AnimalDTO animalDTO =
                new AnimalDTO(7, "Donkey", 4, new FoodDTO("leaves"), new FamilyDTO("mammal"));

        AnimalDTO savedAnimal = animalDAO.createNewAnimal(animalDTO).getBody();

        Boolean result = animalDAO.deleteAnimalByName(Objects.requireNonNull(savedAnimal).getName()).getBody();

        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testDeleteAnimalByNameFAIL() {

        Boolean result = animalDAO.deleteAnimalByName("Monkey").getBody();

        assertEquals(Boolean.FALSE, result);
    }
}
