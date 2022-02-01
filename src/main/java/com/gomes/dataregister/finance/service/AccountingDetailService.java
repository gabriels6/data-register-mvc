package com.gomes.dataregister.finance.service;

import com.gomes.dataregister.finance.model.Accounting;
import com.gomes.dataregister.finance.model.AccountingDetail;
import com.gomes.dataregister.finance.repository.AccountingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AccountingDetailService {

    @Autowired
    AccountingDetailRepository accountingDetailRepository;

    public Iterable<AccountingDetail> getAllAccountingDetails() {
        return accountingDetailRepository.findAll();
    }

    public AccountingDetail getAccountingDetailById(int id) {
        return accountingDetailRepository.getOne(id);
    }

    public AccountingDetail saveAccountingDetail(AccountingDetail accountingDetail) {
        return accountingDetailRepository.save(accountingDetail);
    }

    public void deleteAccountingDetail(AccountingDetail accountingDetail) {
        accountingDetailRepository.delete(accountingDetail);
    }

}
