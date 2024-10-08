package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.services.interfaces.CountLegsService;
import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import com.epetkov.restjungle.services.interfaces.FoodService;
import com.epetkov.restjungle.utils.URLc;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RequestMapping(URLc.J_ANIMALS_URL)
@RestController
public class JungleController {

    private final AnimalService animalService;
    private final FoodService foodService;
    private final CountLegsService countLegsDao;

    public JungleController(AnimalService animalService,
                            FoodService foodService, CountLegsService countLegsDao) {

        this.animalService = animalService;
        this.foodService = foodService;
        this.countLegsDao = countLegsDao;
    }

    /**
     * Returns a List of Animals with Food and Legs.
     * @return ArrayList (AnimalDTO) ;
     */
    @Operation(summary = "View List of Animals with Food and Legs", description = "These are some API Notes")
    @GetMapping(URLc.FOOD_URL + URLc.LEGS_URL)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsWithFoodLegs() {

        return animalService.getAllAnimalsWithFoodAndLegs();
    }

    /**
     * Returns an Animal found by Name.
     * @param name ;
     * @return Object (AnimalDTO) ;
     */
    @Operation(summary = "Get Animal by Name", description = "These are some API Notes")
    @GetMapping(URLc.NAME_PARAM)
    public ResponseEntity<AnimalDTO> getAnimalByName(@PathVariable("name") String name) {

        return animalService.getAnimalByName(name);
    }

    /**
     * Returns a List of Animals found by Food Name.
     * @param food ;
     * @return ArrayList (AnimalDTO) ;
     */
    @Operation(summary = "Get Animals by Food Name", description = "These are some API Notes")
    @GetMapping(URLc.FOOD_URL + URLc.FOOD_PARAM)
    public ResponseEntity<List<AnimalDTO>> getAnimalsByFoodName(@PathVariable("food") String food) {

        return animalService.getAnimalsByFoodName(food);
    }

    /**
     * Returns a new type of Food.
     * @param id ;
     * @param food ;
     * @return Object (FoodDTO) ;
     */
    @Operation(summary = "Create a New Type of Food", description = "These are some API Notes")
    @PostMapping(URLc.FOOD_URL + URLc.ID_PARAM + URLc.FOOD_PARAM)
    public ResponseEntity<FoodDTO> createNewFood(@PathVariable Integer id, @PathVariable String food) {

        return foodService.createNewFood(id, food);
    }

    /**
     * Returns a new type of Animal.
     * @param id ;
     * @param name ;
     * @param legs ;
     * @param food ;
     * @param family ;
     * @return Object (AnimalDTO) ;
     */
    @Operation(summary = "Create a New Type of Animal", description = "These are some API Notes")
    @PostMapping(URLc.ID_PARAM + URLc.NAME_PARAM + URLc.LEGS_PARAM + URLc.FOOD_PARAM + URLc.FAMILY_PARAM)
    public ResponseEntity<AnimalDTO> createNewAnimal(@PathVariable Integer id, @PathVariable String name,
                                                     @PathVariable Integer legs, @PathVariable String food,
                                                     @PathVariable String family) {

        return animalService.createNewAnimal(id, name, legs, food, family);
    }

    /**
     * Deletes an Animal by Name.
     * @param name ;
     * @return Boolean ;
     */
    @Operation(summary = "Delete an Animal by Name", description = "These are some API Notes")
    @DeleteMapping(URLc.NAME_PARAM)
    public ResponseEntity<Boolean> deleteAnimalByName(@PathVariable String name) {

        return animalService.deleteAnimalByName(name);
    }

    /**
     * Returns a number of Legs calculated by Food and Family Names.
     * @param food ;
     * @param family ;
     * @return HashMap ;
     */
    @Operation(summary = "Count the Number of Legs by Food and Family Names", description = "These are some API Notes")
    @GetMapping(URLc.FOOD_PARAM + URLc.FAMILY_PARAM + URLc.COUNT_LEGS_URL)
    public ResponseEntity<List<Map<String, Integer>>> countLegsByFoodAndFamilyNames(@PathVariable String food,
                                                                                    @PathVariable String family) {

        List<Map<String, Integer>> mapList = new ArrayList<>();

        mapList.add(countLegsDao.countLegsByFoodAndFamilyNames(food).getBody());
        mapList.add(countLegsDao.countLegsByFoodAndFamilyNames(family).getBody());

        return new ResponseEntity<>(mapList, HttpStatus.OK);
    }
}
