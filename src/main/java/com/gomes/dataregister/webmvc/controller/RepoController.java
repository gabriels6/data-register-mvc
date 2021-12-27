package com.gomes.dataregister.webmvc.controller;

import com.gomes.dataregister.model.ProjectRepo;
import com.gomes.dataregister.service.DateService;
import com.gomes.dataregister.service.LogService;
import com.gomes.dataregister.service.ProjectRepoService;
import com.gomes.dataregister.service.SessionService;
import com.gomes.dataregister.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/repo")
public class RepoController implements BasicController {

    @Autowired
    ProjectRepoService repoService;

    @Autowired
    LogService logService;

    @Autowired
    DateService dateService;

    @Autowired
    SessionService sessionService;

    MessageUtils messageUtils = new MessageUtils();

    @Override
    @GetMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("repos",repoService.getAllRepos());
        return "repo/index";
    }

    @GetMapping("/{id}")
    public String indexById(@PathVariable("id") int Id, Model model) {
        try {
            model.addAttribute("repo", repoService.getRepoById(Id));
            return "repo/show";
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao buscar repositório. " + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            model.addAttribute("repos",repoService.getAllRepos());
            return "repo/index";
        }
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("repo",new ProjectRepo());
        return "repo/create";
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        model.addAttribute("repo",repoService.getRepoById(Id));
        return "repo/edit";
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            repoService.deleteRepo(repoService.getRepoById(Id));
            model.addAttribute("message","Repositório de projeto deletado com sucesso.");
            model.addAttribute("messageType",messageUtils.SUCCESS_MESSAGE_TYPE);
            model.addAttribute("repos",repoService.getAllRepos());
            return "repo/index";
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao deletar repositório de projeto." + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            repoService.deleteRepo(repoService.getRepoById(Id));
            model.addAttribute("repos",repoService.getAllRepos());
            return "repo/index";
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("repo") ProjectRepo repo, Model model) {
        try {
            ProjectRepo currRepo = repoService.saveRepo(repo);
            model.addAttribute("message","Repositório de projeto cadastrado com sucesso.");
            model.addAttribute("messageType",messageUtils.SUCCESS_MESSAGE_TYPE);
            model.addAttribute("repo", currRepo);
            return "repo/show";
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao cadastrar/editar repositório de projeto." + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return "repo/create";
        }

    }
}
