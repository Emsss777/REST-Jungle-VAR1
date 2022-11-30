package com.epetkov.restjungle.controllers;

import com.epetkov.restjungle.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Api(tags = {SwaggerConfig.SWAGGER_TAG})
@RestController
public class IndexController {

    @ApiOperation(value = "Redirect to Swagger Page", notes = "These are some API Notes")
    @RequestMapping("/")
    public RedirectView index() {

        return new RedirectView("swagger-ui.html");
    }
}
