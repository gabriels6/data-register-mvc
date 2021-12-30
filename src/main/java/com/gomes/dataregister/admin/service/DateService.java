package com.gomes.dataregister.admin.service;

import com.gomes.dataregister.core.utils.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class DateService {

    DateUtils dateUtils = new DateUtils();

    public String getCurrentDate() {
        return dateUtils.getCurrentDate();
    }

}
