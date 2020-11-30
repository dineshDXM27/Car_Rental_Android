package org.uta.rental.reservation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class TotalCostUtility {

    private static final double TAX = 1.0825;

    public static class Extras {
        private final boolean gps;
        private final boolean onStar;
        private final boolean siriusXm;

        public Extras(boolean gps, boolean onStar, boolean siriusXm) {
            this.gps = gps;
            this.onStar = onStar;
            this.siriusXm = siriusXm;
        }

        public boolean isGps() {
            return gps;
        }

        public boolean isOnStar() {
            return onStar;
        }

        public boolean isSiriusXm() {
            return siriusXm;
        }
    }

    public enum CarType {
        SMART("Smart"),
        ECONOMY("Economy"),
        COMPACT("Compact"),
        INTERMEDIATE("Intermediate"),
        STANDARD("Standard"),
        FULL_SIZE("Full Size"),
        SUV("SUV"),
        MINIVAN("MiniVan"),
        ULTRA_SPORTS("Ultra Sports");

        private String type;

        CarType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public static double getWeekdayRate(CarType carType) {
        double rate = 0.0;
        switch(carType) {
            case SMART:
                rate = 32.99;
                break;
            case ECONOMY:
                rate = 39.99;
                break;
            case COMPACT:
                rate = 44.99;
                break;
            case INTERMEDIATE:
                rate = 45.99;
                break;
            case STANDARD:
                rate = 48.99;
                break;
            case FULL_SIZE:
                rate = 52.99;
                break;
            case SUV:
                rate = 59.99;
                break;
            case MINIVAN:
                rate = 59.99;
                break;
            case ULTRA_SPORTS:
                rate = 199.99;
                break;
        }

        return rate;
    }

    public static double getWeekendRate(CarType carType) {
        double rate = 0.0;
        switch(carType) {
            case SMART:
                rate = 37.99;
                break;
            case ECONOMY:
                rate = 44.99;
                break;
            case COMPACT:
                rate = 49.99;
                break;
            case INTERMEDIATE:
                rate = 50.99;
                break;
            case STANDARD:
                rate = 53.99;
                break;
            case FULL_SIZE:
                rate = 57.99;
                break;
            case SUV:
                rate = 64.99;
                break;
            case MINIVAN:
                rate = 64.99;
                break;
            case ULTRA_SPORTS:
                rate = 204.99;
                break;
        }

        return rate;
    }

    public static double getSiriusXmDayRate(CarType carType) {
        double rate = 0.0;
        switch(carType) {
            case SMART:
                rate = 7.00;
                break;
            case ECONOMY:
                rate = 7.00;
                break;
            case COMPACT:
                rate = 7.00;
                break;
            case INTERMEDIATE:
                rate = 7.00;
                break;
            case STANDARD:
                rate = 7.00;
                break;
            case FULL_SIZE:
                rate = 7.00;
                break;
            case SUV:
                rate = 7.00;
                break;
            case MINIVAN:
                rate = 7.00;
                break;
            case ULTRA_SPORTS:
                rate = 9.00;
                break;
        }

        return rate;
    }

    public static double getOnStarDayRate(CarType carType) {
        double rate = 0.0;
        switch(carType) {
            case SMART:
                rate = 5.00;
                break;
            case ECONOMY:
                rate = 5.00;
                break;
            case COMPACT:
                rate = 5.00;
                break;
            case INTERMEDIATE:
                rate = 5.00;
                break;
            case STANDARD:
                rate = 5.00;
                break;
            case FULL_SIZE:
                rate = 5.00;
                break;
            case SUV:
                rate = 5.00;
                break;
            case MINIVAN:
                rate = 5.00;
                break;
            case ULTRA_SPORTS:
                rate = 7.00;
                break;
        }

        return rate;
    }

    public static double getGpsDayRate(CarType carType) {
        double rate = 0.0;
        switch(carType) {
            case SMART:
                rate = 3.00;
                break;
            case ECONOMY:
                rate = 3.00;
                break;
            case COMPACT:
                rate = 3.00;
                break;
            case INTERMEDIATE:
                rate = 3.00;
                break;
            case STANDARD:
                rate = 3.00;
                break;
            case FULL_SIZE:
                rate = 3.00;
                break;
            case SUV:
                rate = 3.00;
                break;
            case MINIVAN:
                rate = 3.00;
                break;
            case ULTRA_SPORTS:
                rate = 5.00;
                break;
        }

        return rate;
    }

    public static double getWeekRate(CarType carType) {
        double rate = 0.0;
        switch(carType) {
            case SMART:
                rate = 230.93;
                break;
            case ECONOMY:
                rate = 279.93;
                break;
            case COMPACT:
                rate = 314.93;
                break;
            case INTERMEDIATE:
                rate = 321.93;
                break;
            case STANDARD:
                rate = 342.93;
                break;
            case FULL_SIZE:
                rate = 370.93;
                break;
            case SUV:
                rate = 419.93;
                break;
            case MINIVAN:
                rate = 419.93;
                break;
            case ULTRA_SPORTS:
                rate = 1399.93;
                break;
        }

        return rate;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static double calculateBaseCostWithoutDiscount(LocalDateTime from, LocalDateTime to, CarType type, Extras extras) {
        // Rounds to days
        double cost = 0.0;
        int days = (int) Math.ceil(from.until(to, ChronoUnit.HOURS) / 24);
        int weeks = (int) Math.floor(days / 7);

        cost += (weeks * getWeekRate(type));
        LocalDateTime afterWeeks = from.plusWeeks(weeks);
        int chargableDays = (int) Math.ceil(afterWeeks.until(to, ChronoUnit.DAYS));
        for (int i = 0; i < chargableDays; ++i) {
            LocalDateTime day = afterWeeks.plusDays(i);
            if (day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY) {
                cost += getWeekendRate(type);
            } else {
                cost += getWeekdayRate(type);
            }
        }

        if (extras.isGps()) {
            cost += (days * getGpsDayRate(type));
        }

        if (extras.isOnStar()) {
            cost += (days * getOnStarDayRate(type));
        }

        if (extras.isSiriusXm()) {
            cost += (days * getSiriusXmDayRate(type));
        }

        return Math.floor((cost * TAX) * 100) / 100;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static double calculateBaseCostWithDiscount(LocalDateTime from, LocalDateTime to, CarType type, Extras extras) {
        return Math.floor((0.9 * (calculateBaseCostWithoutDiscount(from, to, type, extras) * 100))) / 100;
    }
}
