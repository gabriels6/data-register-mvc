package com.gomes.dataregister.finance.service;

import com.gomes.dataregister.finance.model.Accounting;
import com.gomes.dataregister.finance.model.AccountingType;
import com.gomes.dataregister.finance.repository.AccountingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class AccountingTypeService {

    @Autowired
    AccountingTypeRepository accountingTypeRepository;

    public Iterable<AccountingType> getAllAccountingTypes() {
        return accountingTypeRepository.findAll();
    }

    public AccountingType getAccountingTypeById(int id) {
        return accountingTypeRepository.getOne(id);
    }

    public AccountingType saveAccountingType(AccountingType accountingType) {
        return accountingTypeRepository.save(accountingType);
    }

    public void deleteAccountingType(AccountingType accountingType) {
        accountingTypeRepository.delete(accountingType);
    }

}
