package com.gomes.dataregister.core.config;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
public class ThymeleafConfig {


    public ThymeleafConfig(TemplateEngine templateEngine) {
        templateEngine.addTemplateResolver(new UrlTemplateResolver());
    }

}
