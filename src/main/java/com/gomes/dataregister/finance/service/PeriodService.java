package com.gomes.dataregister.finance.service;

import com.gomes.dataregister.finance.model.Period;
import com.gomes.dataregister.finance.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {

    @Autowired
    PeriodRepository periodRepository;

    public Iterable<Period> getAllPeriods() {
        return periodRepository.findAll();
    }

    public Period getPeriodById(int id) {
        return periodRepository.getOne(id);
    }

    public Period savePeriod(Period period) {
        return periodRepository.save(period);
    }

    public void deletePeriod(Period period) {
        periodRepository.delete(period);
    }

}
