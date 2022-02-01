package com.gomes.dataregister.finance.repository;

import com.gomes.dataregister.finance.model.AccountingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingDetailRepository extends JpaRepository<AccountingDetail, Integer> {
}
