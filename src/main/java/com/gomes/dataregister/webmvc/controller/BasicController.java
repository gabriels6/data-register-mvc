package com.gomes.dataregister.webmvc.controller;

import org.springframework.ui.Model;

public interface BasicController {

    String index(Model model);

    String create(Model model);

    String edit(int Id,Model model);

    String delete(int Id, Model model);
}
