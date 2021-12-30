package com.gomes.dataregister.gitcontrol.controller;

import com.gomes.dataregister.core.dataobject.BranchDataObject;
import com.gomes.dataregister.gitcontrol.model.Branch;
import com.gomes.dataregister.gitcontrol.service.BranchService;
import com.gomes.dataregister.gitcontrol.service.ProjectRepoService;
import com.gomes.dataregister.gitcontrol.service.StatusService;
import com.gomes.dataregister.admin.service.UserService;
import com.gomes.dataregister.core.utils.MessageUtils;
import com.gomes.dataregister.core.controller.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/branch")
public class BranchController implements BasicController {

    @Autowired
    BranchService branchService;

    @Autowired
    UserService userService;

    @Autowired
    ProjectRepoService repoService;

    @Autowired
    StatusService statusService;

    MessageUtils messageUtils = new MessageUtils();

    private static final String INDEX_PAGE = "gitcontrol/branch/index";
    private static final String CREATE_PAGE = "gitcontrol/branch/create";
    private static final String EDIT_PAGE = "gitcontrol/branch/edit";
    private static final String SHOW_PAGE = "gitcontrol/branch/show";
    private static final String ERROR_PAGE = "core/error/index";

    @Override
    @GetMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("branches", branchService.getAllBranches());
        return INDEX_PAGE;
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("repos", repoService.getAllRepos());
        model.addAttribute("statuses", statusService.getAllStatus());
        model.addAttribute("branchDto",new BranchDataObject());
        return CREATE_PAGE;
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        try {
            Branch branch = branchService.getBranchById(Id);
            BranchDataObject branchDto = new BranchDataObject(branch);
            model.addAttribute("branchDto", branchDto);
            model.addAttribute("branches",branchService.getAllBranches());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("repos", repoService.getAllRepos());
            model.addAttribute("statuses", statusService.getAllStatus());
            return EDIT_PAGE;
        } catch (Exception ex) {
            BranchDataObject branchDto = new BranchDataObject();
            model.addAttribute("branchDto",branchDto);
            return EDIT_PAGE;
        }
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            branchService.deleteBranch(branchService.getBranchById(Id));
            model.addAttribute("branches",branchService.getAllBranches());
            model.addAttribute("message","Sucesso ao remover branch.");
            model.addAttribute("messageType",messageUtils.SUCCESS_MESSAGE_TYPE);
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("branches",branchService.getAllBranches());
            model.addAttribute("message","Erro ao remover branch. " + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return INDEX_PAGE;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("branchDto") BranchDataObject branchDto, Model model) {
        try {
            Branch branch = new Branch();
            if ( branchDto.getId() != 0) branch.setId(branchDto.getId());
            branch.setName(branchDto.getName());
            branch.setUser(userService.getUserById(branchDto.getUser_id()));
            branch.setRepository(repoService.getRepoById(branchDto.getRepo_id()));
            branch.setStatus(statusService.getStatusById(branchDto.getStatus_id()));
            model.addAttribute("repo",branchService.saveBranch(branch));
            model.addAttribute("message","Branch criada/editada com sucesso.");
            model.addAttribute("messageType",messageUtils.SUCCESS_MESSAGE_TYPE);
            model.addAttribute("branch",branch);
            return SHOW_PAGE;
        } catch (Exception ex) {
            model.addAttribute("branchDto",branchDto);
            model.addAttribute("message","Erro ao criar/editar branch. " + ex.getLocalizedMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return CREATE_PAGE;
        }
    }
}
