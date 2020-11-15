package org.uta.rental.carsInformation;

public class CarsInformation {

    private int carNumber;
    private String carName;
    private String capacity;
    private int weekdayRate;
    private int weekendRate;
    private int weekRate;
    private int GPSRate;
    private int OnStarRate;
    private int SiriusXMRate;


    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

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



}