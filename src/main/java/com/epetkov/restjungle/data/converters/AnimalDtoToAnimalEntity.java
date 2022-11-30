package com.epetkov.restjungle.data.converters;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.entities.AnimalEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AnimalDtoToAnimalEntity implements Converter<AnimalDTO, AnimalEntity> {

    public final FoodDtoToFoodEntity foodDtoToFoodEntity;
    public final FamilyDtoToFamilyEntity familyDtoToFamilyEntity;

    public AnimalDtoToAnimalEntity(FoodDtoToFoodEntity foodDtoToFoodEntity,
            FamilyDtoToFamilyEntity familyDtoToFamilyEntity) {

        this.foodDtoToFoodEntity = foodDtoToFoodEntity;
        this.familyDtoToFamilyEntity = familyDtoToFamilyEntity;
    }

    @Nullable
    @Override
    public AnimalEntity convert(AnimalDTO animalDTO) {

        final AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setId(animalDTO.getId());
        animalEntity.setName(animalDTO.getName());
        animalEntity.setLegs(animalDTO.getLegs());
        animalEntity.setFood(foodDtoToFoodEntity.convert(animalDTO.getFoodDTO()));
        animalEntity.setFamily(familyDtoToFamilyEntity.convert(animalDTO.getFamilyDTO()));

        return animalEntity;
    }
}
