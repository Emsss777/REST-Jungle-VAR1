package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.data.dto.AnimalDTO;
import com.epetkov.restjungle.services.interfaces.AnimalService;
import com.epetkov.restjungle.utils.URLc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(URLc.J_ANIMALS_URL)
@RestController
public class JungleController {

    private final AnimalService animalService;

    public JungleController(AnimalService animalService) {

        this.animalService = animalService;
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
}
