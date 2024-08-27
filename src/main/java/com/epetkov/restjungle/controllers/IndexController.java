package com.epetkov.restjungle.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class IndexController {

    @Operation(summary = "Redirect to Swagger Page", description = "These are some API Notes")
    @RequestMapping("/")
    public RedirectView index() {

        return new RedirectView("swagger-ui.html");
    }
}
