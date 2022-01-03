package com.gomes.dataregister.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    @Value("${spa.api.url}")
    private String spaApiUrl;

    public String getSpaApiUrl() {
        return spaApiUrl;
    }
}
