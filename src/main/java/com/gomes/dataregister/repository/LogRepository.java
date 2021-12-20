package com.gomes.dataregister.repository;

import com.gomes.dataregister.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}
