package org.uta.rental;

public class SearchCarRentalManager extends CarSummary {

    private int weekdayRate;
    private int weekendRate;
    private int weekRate;
    private int GPSRate;
    private int OnStarRate;
    private int SiriusXMRate;
    private String carStatus;

    public int getWeekdayRate() {
        return weekdayRate;
    }

    public void setWeekdayRate(int weekdayRate) {
        this.weekdayRate = weekdayRate;
    }

    public int getWeekendRate() {
        return weekendRate;
    }

    public void setWeekendRate(int weekendRate) {
        this.weekendRate = weekendRate;
    }

    public int getWeekRate() {
        return weekRate;
    }

    public void setWeekRate(int weekRate) {
        this.weekRate = weekRate;
    }

    public int getGPSRate() {
        return GPSRate;
    }

    public void setGPSRate(int GPSRate) {
        this.GPSRate = GPSRate;
    }

    public int getOnStarRate() {
        return OnStarRate;
    }

    public void setOnStarRate(int onStarRate) {
        OnStarRate = onStarRate;
    }

    public int getSiriusXMRate() {
        return SiriusXMRate;
    }

    public void setSiriusXMRate(int siriusXMRate) {
        SiriusXMRate = siriusXMRate;
    }


    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }






}
