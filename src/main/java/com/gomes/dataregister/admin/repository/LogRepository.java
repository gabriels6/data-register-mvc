package com.gomes.dataregister.admin.repository;

import com.gomes.dataregister.admin.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}
