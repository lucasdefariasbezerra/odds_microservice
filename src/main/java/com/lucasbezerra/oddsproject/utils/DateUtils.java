package com.lucasbezerra.oddsproject.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static Date getDateFromMilli(Long milli) {
       return new Date(milli);
    }

    public static int getYearFromDate(Date date) {
        LocalDate localDate = date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.getYear();
    }
}
