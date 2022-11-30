package com.epetkov.restjungle.data.converters;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AnimalEntityToAnimalDTO implements Converter<AnimalEntity, AnimalDTO> {

    private final FoodEntityToFoodDTO foodEntityToFoodDTO;
    private final FamilyEntityToFamilyDTO familyEntityToFamilyDTO;

    public AnimalEntityToAnimalDTO(FoodEntityToFoodDTO foodEntityToFoodDTO,
                                   FamilyEntityToFamilyDTO familyEntityToFamilyDTO) {

        this.foodEntityToFoodDTO = foodEntityToFoodDTO;
        this.familyEntityToFamilyDTO = familyEntityToFamilyDTO;
    }

    @Nullable
    @Override
    public AnimalDTO convert(AnimalEntity animalEntity) {

        final AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animalEntity.getId());
        animalDTO.setName(animalEntity.getName());
        animalDTO.setLegs(animalEntity.getLegs());
        animalDTO.setFoodDTO(foodEntityToFoodDTO.convert(animalEntity.getFood()));
        animalDTO.setFamilyDTO(familyEntityToFamilyDTO.convert(animalEntity.getFamily()));

        return animalDTO;
    }
}
