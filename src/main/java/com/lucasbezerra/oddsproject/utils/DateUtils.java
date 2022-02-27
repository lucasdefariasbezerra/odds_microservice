package com.lucasbezerra.oddsproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    public static long convertDateStringToMilli(String dateString, String dateFormat) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return format.parse(dateString).getTime();
    }

    public static String convertDateToString(Long milli, String dateFormat) {
        Date date = getDateFromMilli(milli);
        final SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return format.format(date);
    }
}
