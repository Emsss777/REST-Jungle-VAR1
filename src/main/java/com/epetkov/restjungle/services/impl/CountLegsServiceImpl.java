package com.epetkov.restjungle.services.impl;

import com.epetkov.restjungle.data.converters.AnimalEntityToAnimalDTO;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.data.entities.ExcludedEntity;
import com.epetkov.restjungle.repositories.AnimalRepository;
import com.epetkov.restjungle.repositories.ExcludedRepository;
import com.epetkov.restjungle.services.interfaces.CountLegsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service("countLegsDao")
@Transactional
public class CountLegsServiceImpl implements CountLegsService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ExcludedRepository excludedRepository;
    private final AnimalRepository animalRepository;
    private final AnimalEntityToAnimalDTO animalEntityToAnimalDTO;

    public CountLegsServiceImpl(ExcludedRepository excludedRepository,
                            AnimalRepository animalRepository,
                            AnimalEntityToAnimalDTO animalEntityToAnimalDTO) {

        this.excludedRepository = excludedRepository;
        this.animalRepository = animalRepository;
        this.animalEntityToAnimalDTO = animalEntityToAnimalDTO;
    }

    @Override
    public ResponseEntity<Map<String, Integer>> countLegsByFoodAndFamilyNames(String name) {

        List<AnimalEntity> animalList = animalRepository.findAll();
        List<ExcludedEntity> excludedAnimals = excludedRepository.findAll();
        List<AnimalDTO> animalDTOList = new ArrayList<>();
        Map<String, Integer> resultMap = new HashMap<>();

        if (animalList.isEmpty()) {

            LOG.error("Nо Animals Found in the DATABASE!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        getAllNonExcludedAnimals(animalList, animalDTOList, excludedAnimals);

        String getName = null;
        for (AnimalDTO animalDTO: animalDTOList) {

            if (Objects.equals(name, "food")) {

                getName = animalDTO.getFoodDTO().getName();

            } else if (Objects.equals(name, "family")) {

                getName = animalDTO.getFamilyDTO().getName();
            }

            countLegs(getName, animalDTO, resultMap);
        }

        LOG.info("Case " + "'" + name + "' : " + resultMap.size() + " Elements");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    private void getAllNonExcludedAnimals(List<AnimalEntity> animalList, List<AnimalDTO> animalDTOList,
                                          List<ExcludedEntity> excludedAnimals) {

        for (AnimalEntity animalEntity : animalList) {

            AnimalDTO animalDTO = animalEntityToAnimalDTO.convert(animalEntity);
            animalDTOList.add(animalDTO);

            for (ExcludedEntity excludedAnimal : excludedAnimals) {

                if (Objects.equals(animalEntity.getId(), excludedAnimal.getIdAnimalExcluded())) {

                    animalDTOList.remove(animalDTO);
                }
            }
        }
    }

    public void countLegs(String name, AnimalDTO animal, Map<String, Integer> resultMap) {

        if (resultMap.containsKey(name)) {

            resultMap.put(name, resultMap.get(name) + animal.getLegs());

        } else {

            resultMap.put(name, animal.getLegs());
        }
    }
}
