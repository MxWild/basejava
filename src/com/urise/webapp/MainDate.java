package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class MainDate {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Date date = new Date();
        System.out.println("Class Date: " + date);

        Calendar cal = Calendar.getInstance();
        System.out.println("Class Calendar: " + cal.getTime());

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        System.out.println("ld: " + ld);
        System.out.println("lt: " + lt);
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("ldt now(): " + ldt);
        LocalDateTime ldt1 = LocalDateTime.of(ld, lt);
        System.out.println("ldt1 of: "+ ldt1);

        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
        System.out.println(sdf.format(date));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YY/MM/dd");
        System.out.println(dtf.format(ldt));

        System.out.println("Прошло времени: " + (System.currentTimeMillis() - start) + " ms");
    }

}

