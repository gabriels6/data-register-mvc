package com.gomes.dataregister.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
public class ThymeleafConfig {


    public ThymeleafConfig(TemplateEngine templateEngine) {
        templateEngine.addTemplateResolver(new UrlTemplateResolver());
    }

}
