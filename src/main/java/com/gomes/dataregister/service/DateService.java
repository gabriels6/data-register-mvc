package com.gomes.dataregister.service;

import com.gomes.dataregister.utils.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class DateService {

    DateUtils dateUtils = new DateUtils();

    public String getCurrentDate() {
        return dateUtils.getCurrentDate();
    }

}
