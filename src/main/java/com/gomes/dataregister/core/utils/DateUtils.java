package com.gomes.dataregister.core.utils;

import com.gomes.dataregister.core.exceptions.DateUtilsException;

import java.time.LocalDateTime;
import java.util.Arrays;

public class DateUtils {

    public String[] months = {
            "Jan", "Feb", "Mar",
            "Apr", "May", "Jun",
            "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"
    };

    public String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
    }

    public String getMonthByNumber(int monthNumber) throws DateUtilsException {
        if( monthNumber < 1 || monthNumber > 13) throw new DateUtilsException("Month out of range! Number must be between 1 and 12");
        return months[monthNumber-1];
    }

    public int[] getMonthNumbers() {
        int[] monthNumbers = new int[12];
        for(int i = 1; i <= 12; i++) {
            monthNumbers[i-1] = i;
        }
        return monthNumbers;
    }

}
