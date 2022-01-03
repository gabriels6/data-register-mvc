package com.gomes.dataregister.gitcontrol.controller;

import com.gomes.dataregister.gitcontrol.model.ProjectRepo;
import com.gomes.dataregister.admin.service.DateService;
import com.gomes.dataregister.admin.service.LogService;
import com.gomes.dataregister.gitcontrol.service.ProjectRepoService;
import com.gomes.dataregister.core.service.SessionService;
import com.gomes.dataregister.core.utils.MessageUtils;
import com.gomes.dataregister.core.controller.BasicController;
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

    private static final String INDEX_PAGE = "gitcontrol/repo/index";
    private static final String CREATE_PAGE = "gitcontrol/repo/create";
    private static final String EDIT_PAGE = "gitcontrol/repo/edit";
    private static final String SHOW_PAGE = "gitcontrol/repo/show";
    private static final String ERROR_PAGE = "core/error/index";

    @Override
    @GetMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("repos",repoService.getAllRepos());
        return INDEX_PAGE;
    }

    @GetMapping("/{id}")
    public String indexById(@PathVariable("id") int Id, Model model) {
        try {
            model.addAttribute("repo", repoService.getRepoById(Id));
            return SHOW_PAGE;
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao buscar repositório. " + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            model.addAttribute("repos",repoService.getAllRepos());
            return INDEX_PAGE;
        }
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("repo",new ProjectRepo());
        return CREATE_PAGE;
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        model.addAttribute("repo",repoService.getRepoById(Id));
        return EDIT_PAGE;
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            repoService.deleteRepo(repoService.getRepoById(Id));
            model.addAttribute("message","Repositório de projeto deletado com sucesso.");
            model.addAttribute("messageType",messageUtils.SUCCESS_MESSAGE_TYPE);
            model.addAttribute("repos",repoService.getAllRepos());
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao deletar repositório de projeto." + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            repoService.deleteRepo(repoService.getRepoById(Id));
            model.addAttribute("repos",repoService.getAllRepos());
            return INDEX_PAGE;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("repo") ProjectRepo repo, Model model) {
        try {
            ProjectRepo currRepo = repoService.saveRepo(repo);
            model.addAttribute("message","Repositório de projeto cadastrado com sucesso.");
            model.addAttribute("messageType",messageUtils.SUCCESS_MESSAGE_TYPE);
            model.addAttribute("repo", currRepo);
            return SHOW_PAGE;
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao cadastrar/editar repositório de projeto." + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return CREATE_PAGE;
        }

    }
}
