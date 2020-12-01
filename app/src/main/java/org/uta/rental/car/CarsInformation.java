package org.uta.rental.car;

import java.io.Serializable;

public class CarsInformation implements Serializable  {

    private int carNumber;
    private String carName;

    private CarStatus carStatus;
    private int capacity;
    private double weekdayRate;
    private double weekendRate;
    private double weekRate;
    private double GPSRate;
    private double OnStarRate;
    private double SiriusXMRate;


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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getWeekdayRate() {
        return weekdayRate;
    }

    public void setWeekdayRate(double weekdayRate) {
        this.weekdayRate = weekdayRate;
    }

    public double getWeekendRate() {
        return weekendRate;
    }

    public void setWeekendRate(double weekendRate) {
        this.weekendRate = weekendRate;
    }

    public double getWeekRate() {
        return weekRate;
    }

    public void setWeekRate(double weekRate) {
        this.weekRate = weekRate;
    }

    public double getGPSRate() {
        return GPSRate;
    }

    public void setGPSRate(double GPSRate) {
        this.GPSRate = GPSRate;
    }

    public double getOnStarRate() {
        return OnStarRate;
    }

    public void setOnStarRate(double onStarRate) {
        OnStarRate = onStarRate;
    }

    public double getSiriusXMRate() {
        return SiriusXMRate;
    }

    public void setSiriusXMRate(double siriusXMRate) {
        SiriusXMRate = siriusXMRate;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }
}
