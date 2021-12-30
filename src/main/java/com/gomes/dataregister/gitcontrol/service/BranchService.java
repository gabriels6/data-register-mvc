package com.gomes.dataregister.gitcontrol.service;

import com.gomes.dataregister.gitcontrol.model.Branch;
import com.gomes.dataregister.gitcontrol.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;

    public Iterable<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(int branchId) {
        return branchRepository.findById(branchId).orElseThrow(() -> new ResourceNotFoundException("Branch não encontrada"));
    }

    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public void deleteBranch(Branch branch) {
        branchRepository.delete(branch);
    }

}
