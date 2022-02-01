package com.gomes.dataregister.finance.repository;

import com.gomes.dataregister.finance.model.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingRepository extends JpaRepository<Accounting, Integer> {

    Iterable<Accounting> findByUserId(int UserId);

}
