package com.epetkov.restjungle.dao.impl;

import com.epetkov.restjungle.Application;
import com.epetkov.restjungle.dao.interfaces.AnimalDAO;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

        // Todo: impl
    }

    @Test
    public void testGetAnimalByName() {

        AnimalDTO animalDTO = animalDAO.getOneByName("Deer").getBody();

        assertThat(animalDTO).isNotNull();
        assertEquals((Integer) 2, animalDTO.getId());
        assertEquals((Integer) 4, animalDTO.getLegs());
        assertEquals("leaves", animalDTO.getFoodDTO().getName());
        assertEquals("mammal", animalDTO.getFamilyDTO().getName());
    }

    @Test
    public void testGetAnimalsByFoodName() {

        List<AnimalDTO> animalDTOList = animalDAO.getAnimalsByFoodName("leaves").getBody();

        assertThat(animalDTOList).isNotNull();
        assertEquals(2, animalDTOList.size());
    }
}
