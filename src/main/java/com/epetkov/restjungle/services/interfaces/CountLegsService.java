package com.epetkov.restjungle.services.interfaces;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface CountLegsService {

    ResponseEntity<Map<String, Integer>> countLegsByFoodAndFamilyNames(String name);
}
