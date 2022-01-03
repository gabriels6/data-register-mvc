package com.gomes.dataregister.api.controller;

import com.gomes.dataregister.core.service.PropertiesService;
import com.gomes.dataregister.core.utils.HttpExecutor;
import net.minidev.json.JSONObject;
import org.flywaydb.core.internal.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/v1/spa")
public class SpaApiController {

    @Autowired
    PropertiesService propertiesService;

    @GetMapping(value = {"/{page}/{resource}"})
    public String renderResource(@PathVariable("page") String page, @PathVariable("resource") String resource) {
        HttpExecutor httpExecutor = new HttpExecutor();
        return httpExecutor.get(propertiesService.getSpaApiUrl()+"/api/v1/spa/" + page + "/" + resource);
    }

    @GetMapping(value = {"/{page}/{file}/{resource}"}, produces = {"application/javascript","text/stylesheet"})
    public String renderResourceFile(@PathVariable("page") String page, @PathVariable("file") String file, @PathVariable("resource") String resource) {
        HttpExecutor httpExecutor = new HttpExecutor();
        return httpExecutor.get(propertiesService.getSpaApiUrl()+"/api/v1/spa/" + page + "/" + file + "/" + resource);

    }
}
