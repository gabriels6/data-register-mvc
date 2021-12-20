package com.gomes.dataregister.service;

import com.gomes.dataregister.model.Status;
import com.gomes.dataregister.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Iterable<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status getStatusById(int statusId) {
        return statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("Status não encontrado."));
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatus(Status status) {
        statusRepository.delete(status);
    }

}
