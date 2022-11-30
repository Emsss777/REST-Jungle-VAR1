package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.data.dto.FoodDTO;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import com.epetkov.restjungle.services.interfaces.FoodService;
import com.epetkov.restjungle.utils.URLc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(URLc.J_ANIMALS_URL)
@RestController
public class JungleController {

    private final AnimalService animalService;
    private final FoodService foodService;

    public JungleController(AnimalService animalService, FoodService foodService) {

        this.animalService = animalService;
        this.foodService = foodService;
    }

    /**
     * Returns a List of Animals with Food and Legs.
     * @return ArrayList (AnimalDTO) ;
     */
    @GetMapping(URLc.FOOD_URL + URLc.LEGS_URL)
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsWithFoodLegs() {

        return animalService.getAllAnimalsWithFoodAndLegs();
    }

    /**
     * Returns an Animal found by Name.
     * @param name ;
     * @return Object (AnimalDTO) ;
     */
    @GetMapping(URLc.NAME_PARAM)
    public ResponseEntity<AnimalDTO> getAnimalByName(@PathVariable("name") String name) {

        return animalService.getAnimalByName(name);
    }

    /**
     * Returns a List of Animals found by Food Name.
     * @param food ;
     * @return ArrayList (AnimalDTO) ;
     */
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
    @PostMapping(URLc.ID_PARAM + URLc.NAME_PARAM + URLc.LEGS_PARAM + URLc.FOOD_PARAM + URLc.FAMILY_PARAM)
    public ResponseEntity<AnimalDTO> createNewAnimal(@PathVariable Integer id, @PathVariable String name,
                                                     @PathVariable Integer legs, @PathVariable String food,
                                                     @PathVariable String family) {

        return animalService.createNewAnimal(id, name, legs, food, family);
    }

    /**
     * Deletes an Animal by ID and returns a List of the remaining Animals.
     * @param name ;
     * @return ArrayList (AnimalDTO) ;
     */
    @DeleteMapping(URLc.NAME_PARAM)
    public ResponseEntity<List<AnimalDTO>> deleteAnimalByName(@PathVariable String name) {

        return animalService.deleteAnimalByName(name);
    }
}
