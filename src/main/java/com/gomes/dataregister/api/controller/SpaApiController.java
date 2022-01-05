package com.gomes.dataregister.api.controller;

import com.gomes.dataregister.core.service.PropertiesService;
import com.gomes.dataregister.core.utils.HttpExecutor;
import net.minidev.json.JSONObject;
import org.apache.coyote.Response;
import org.flywaydb.core.internal.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @GetMapping(value = {"/{page}/{file}/{resource}"})
    public ResponseEntity<String> renderResourceFile(@PathVariable("page") String page, @PathVariable("file") String file, @PathVariable("resource") String resource) {
        HttpExecutor httpExecutor = new HttpExecutor();
        String contentType;
        if (file.equals("css")) contentType = "text/css";
        else if (file.equals("js")) contentType = "text/javascript";
        else contentType = "text/plain";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type",contentType);
        return new ResponseEntity<String>(httpExecutor.get(propertiesService.getSpaApiUrl()+"/api/v1/spa/" + page + "/" + file + "/" + resource),headers, HttpStatus.OK);
    }
}
