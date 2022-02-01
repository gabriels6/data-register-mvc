package com.gomes.dataregister.finance.service;

import com.gomes.dataregister.finance.model.Accounting;
import com.gomes.dataregister.finance.model.AccountingType;
import com.gomes.dataregister.finance.repository.AccountingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AccountingService {

    @Autowired
    AccountingRepository accountingRepository;

    public Iterable<Accounting> getAllAcountings() {
        return accountingRepository.findAll();
    }

    public Iterable<Accounting> getAllAccountingsByUserId(int userId) {
        return accountingRepository.findByUserId(userId);
    }

    public Accounting getAccountingById(int id) {
        return accountingRepository.getOne(id);
    }

    public Accounting saveAccounting(Accounting accounting) {
        return accountingRepository.save(accounting);
    }

    public void deleteAccounting(Accounting accounting) {
        accountingRepository.delete(accounting);
    }

}
