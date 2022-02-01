package com.gomes.dataregister.finance.repository;

import com.gomes.dataregister.finance.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Integer> {
}
