package com.gomes.dataregister.service;

import com.gomes.dataregister.model.Branch;
import com.gomes.dataregister.repository.BranchRepository;
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
