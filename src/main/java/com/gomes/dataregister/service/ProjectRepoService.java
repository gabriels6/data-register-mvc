package com.gomes.dataregister.service;

import com.gomes.dataregister.model.ProjectRepo;
import com.gomes.dataregister.repository.ProjectRepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectRepoService {

    @Autowired
    ProjectRepoRepository projectRepoRepository;

    @Autowired
    DateService dateService;

    public Iterable<ProjectRepo> getAllRepos() {
        return projectRepoRepository.findAll();
    }

    public ProjectRepo getRepoById(int repoId) {
        return projectRepoRepository.findById(repoId).orElseThrow(() -> new ResourceNotFoundException("Repositorio de projeto não encontrado"));
    }

    public ProjectRepo saveRepo(ProjectRepo repo) {
        if (repo.getCreatedAt() == null) {
            repo.setCreatedAt(dateService.getCurrentDate());
        }
        return projectRepoRepository.save(repo);
    }

    public void deleteRepo(ProjectRepo repo) {
        projectRepoRepository.delete(repo);
    }

}
