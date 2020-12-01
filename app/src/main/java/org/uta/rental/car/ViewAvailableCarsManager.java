package org.uta.rental.car;

import org.uta.rental.CarSummary;

public class ViewAvailableCarsManager extends CarSummary {


    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    private float rate;
    private String carStatus;

}
