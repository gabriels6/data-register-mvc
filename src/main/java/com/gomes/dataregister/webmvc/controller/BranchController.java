package com.gomes.dataregister.webmvc.controller;

import com.gomes.dataregister.dataobject.BranchDataObject;
import com.gomes.dataregister.model.Branch;
import com.gomes.dataregister.service.BranchService;
import com.gomes.dataregister.service.ProjectRepoService;
import com.gomes.dataregister.service.StatusService;
import com.gomes.dataregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/branch")
public class BranchController implements BasicController{

    @Autowired
    BranchService branchService;

    @Autowired
    UserService userService;

    @Autowired
    ProjectRepoService repoService;

    @Autowired
    StatusService statusService;

    @Override
    @GetMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("branches", branchService.getAllBranches());
        return "branch/index";
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("repos", repoService.getAllRepos());
        model.addAttribute("statuses", statusService.getAllStatus());
        model.addAttribute("branchDto",new BranchDataObject());
        return "branch/create";
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
            return "branch/edit";
        } catch (Exception ex) {
            BranchDataObject branchDto = new BranchDataObject();
            model.addAttribute("branchDto",branchDto);
            return "branch/edit";
        }
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            branchService.deleteBranch(branchService.getBranchById(Id));
            model.addAttribute("branches",branchService.getAllBranches());
            model.addAttribute("message","Sucesso ao remover branch.");
            model.addAttribute("messageType","success");
            return "branch/index";
        } catch (Exception ex) {
            model.addAttribute("branches",branchService.getAllBranches());
            model.addAttribute("message","Erro ao remover branch. " + ex.getLocalizedMessage());
            model.addAttribute("messageType","error");
            return "branch/indexs";
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
            model.addAttribute("messageType","success");
            model.addAttribute("branch",branch);
            return "branch/show";
        } catch (Exception ex) {
            model.addAttribute("branchDto",branchDto);
            model.addAttribute("message","Erro ao criar/editar branch. " + ex.getLocalizedMessage());
            model.addAttribute("messageType","success");
            return "branch/create";
        }
    }
}
