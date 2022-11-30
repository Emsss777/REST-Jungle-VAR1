package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.AnimalEntityToAnimalDTO;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.repositories.AnimalRepository;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("animalService")
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final AnimalRepository animalRepository;
    private final AnimalEntityToAnimalDTO animalEntityToAnimalDTO;

    public AnimalServiceImpl(AnimalRepository animalRepository,
                             AnimalEntityToAnimalDTO animalEntityToAnimalDTO) {

        this.animalRepository = animalRepository;
        this.animalEntityToAnimalDTO = animalEntityToAnimalDTO;
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

        AnimalEntity animalEntity = animalRepository.findAnimalByName(name);
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

    private List<AnimalEntity> getAnimalEntities() {

        return animalRepository.findAll();
    }
}
