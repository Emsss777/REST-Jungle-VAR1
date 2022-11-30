package com.epetkov.restjungle;

import com.epetkov.restjungle.data.entities.AnimalEntity;
import com.epetkov.restjungle.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PrintAnimals implements CommandLineRunner {

    private AnimalRepository animalRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {

        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<AnimalEntity> animalList = animalRepository.findAll();

        System.out.println("***********************************");
        System.out.println("*****  Animals in the Jungle  *****");
        System.out.println("===================================");

        if (animalList.isEmpty()) {

            throw new Exception("Nо Animals Found in the DATABASE!");
        }

        animalList.forEach(animal ->
                System.out.println("Animal ID: " + animal.getId() + " --> " + "Name: " + animal.getName()));

        System.out.println("===================================");
    }
}
