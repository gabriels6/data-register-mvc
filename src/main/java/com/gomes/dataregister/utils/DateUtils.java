package com.gomes.dataregister.utils;

import java.time.LocalDateTime;

public class DateUtils {

    public String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
    }

}
