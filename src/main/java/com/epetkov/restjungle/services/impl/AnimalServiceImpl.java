package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.*;
import com.epetkov.restjungle.data.dto.*;
import com.epetkov.restjungle.data.entities.*;
import com.epetkov.restjungle.repositories.*;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service("animalService")
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final AnimalRepository animalRepository;
    private final FoodRepository foodRepository;
    private final FamilyRepository familyRepository;
    private final FoodEntityToFoodDTO foodEntityToFoodDTO;
    private final FamilyEntityToFamilyDTO familyEntityToFamilyDTO;
    private final AnimalEntityToAnimalDTO animalEntityToAnimalDTO;
    private final AnimalDtoToAnimalEntity animalDtoToAnimalEntity;

    public AnimalServiceImpl(AnimalRepository animalRepository,
                             FoodRepository foodRepository,
                             FamilyRepository familyRepository,
                             FoodEntityToFoodDTO foodEntityToFoodDTO,
                             FamilyEntityToFamilyDTO familyEntityToFamilyDTO,
                             AnimalEntityToAnimalDTO animalEntityToAnimalDTO,
                             AnimalDtoToAnimalEntity animalDtoToAnimalEntity) {

        this.animalRepository = animalRepository;
        this.foodRepository = foodRepository;
        this.familyRepository = familyRepository;
        this.foodEntityToFoodDTO = foodEntityToFoodDTO;
        this.familyEntityToFamilyDTO = familyEntityToFamilyDTO;
        this.animalEntityToAnimalDTO = animalEntityToAnimalDTO;
        this.animalDtoToAnimalEntity = animalDtoToAnimalEntity;
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsWithFoodAndLegs() {

        List<AnimalEntity> animalEntityList = getAnimalEntities();
        if (animalEntityList.isEmpty()) {

            LOG.error("Nо Animals Found in the DATABASE!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<AnimalDTO> animalDTOList = new ArrayList<>();
        for (AnimalEntity animalEntity : animalEntityList) {

            AnimalDTO animalDTO = new AnimalDTO();
            animalDTO.setName(animalEntity.getName());
            animalDTO.setFood(animalEntity.getFood().getName());
            animalDTO.setLegs(animalEntity.getLegs());
            animalDTOList.add(animalDTO);
        }
        
        LOG.info("Number of Animals in the DATABASE: " + animalDTOList.size());
        return new ResponseEntity<>(animalDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AnimalDTO> getAnimalByName(String name) {

        AnimalEntity animalEntity = animalRepository.findOneByName(name);
        if (animalEntity != null) {

            AnimalDTO animalDTO = animalEntityToAnimalDTO.convert(animalEntity);

            LOG.info("Animal Found: " + Objects.requireNonNull(animalDTO).getName());
            return new ResponseEntity<>(animalDTO, HttpStatus.OK);
        }

        LOG.error("Expected Animal NOT Found!");
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(String food) {

        List<AnimalEntity> animalList = getAnimalEntities();
        if (animalList.isEmpty()) {

            LOG.error("Nо Animals Found in the DATABASE!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<AnimalDTO> animalDTOList = new ArrayList<>();
        for (AnimalEntity animalEntity : animalList) {

            if (Objects.equals(animalEntity.getFood().getName(), food)) {

                AnimalDTO animalDTO = animalEntityToAnimalDTO.convert(animalEntity);
                animalDTOList.add(animalDTO);
            }
        }

        LOG.info("Number of Animals Found: " + animalDTOList.size());
        return new ResponseEntity<>(animalDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AnimalDTO> createNewAnimal(Integer id, String name,
                                                     Integer legs, String food, String family) {

        // Check if this Food Exists in the DATABASE;
        FoodEntity confirmFood = foodRepository.findOneByName(food);
        if (confirmFood == null) {

            LOG.error("Expected Food NOT Found!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        FoodEntity foundFood = foodRepository.findOneById(confirmFood.getId());
        FoodDTO returnedFood = foodEntityToFoodDTO.convert(foundFood);

        // Check if this Family Exists in the DATABASE;
        FamilyEntity confirmFamily = familyRepository.findOneByName(family);
        if (confirmFamily == null) {

            LOG.error("Expected Family NOT Found!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        FamilyEntity savedFamily = familyRepository.findOneById(confirmFamily.getId());
        FamilyDTO returnedFamily = familyEntityToFamilyDTO.convert(savedFamily);

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(id);
        animalDTO.setName(name);
        animalDTO.setLegs(legs);
        animalDTO.setFoodDTO(returnedFood);
        animalDTO.setFamilyDTO(returnedFamily);

        AnimalEntity detachedAnimal = animalDtoToAnimalEntity.convert(animalDTO);
        try {
            // Check if this Animal NOT Exists in the DATABASE;
            AnimalEntity confirmAnimal = animalRepository.findOneById(id);
            if (confirmAnimal == null) {

                AnimalEntity savedAnimal = animalRepository.save(Objects.requireNonNull(detachedAnimal));
                AnimalDTO returnedAnimal = animalEntityToAnimalDTO.convert(savedAnimal);

                LOG.info("Saved Animal ID: " + savedAnimal.getId() + " --> " + "Name: " + savedAnimal.getName());
                return new ResponseEntity<>(returnedAnimal, HttpStatus.OK);
            }
        } catch (Exception ex) {

            LOG.error("There is Already an Animal with that Name!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> deleteAnimalByName(String name) {

        AnimalEntity foundAnimal = animalRepository.findOneByName(name);
        if (foundAnimal != null) {

            AnimalEntity savedAnimal = animalRepository.findOneById(foundAnimal.getId());
            animalRepository.deleteById(savedAnimal.getId());
        }

        return getAllAnimalsWithFoodAndLegs();
    }

    private List<AnimalEntity> getAnimalEntities() {

        return animalRepository.findAll();
    }
}
