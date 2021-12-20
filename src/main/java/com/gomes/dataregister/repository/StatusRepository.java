package com.gomes.dataregister.repository;

import com.gomes.dataregister.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}
