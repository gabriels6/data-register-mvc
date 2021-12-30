package com.gomes.dataregister.gitcontrol.repository;

import com.gomes.dataregister.gitcontrol.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}
