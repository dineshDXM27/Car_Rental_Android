package org.uta.rental.reservation;

import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
    private long reservationNumber;

    private String OwningUsername;

    private long carNumber;

    private String carName;

    private int capacity;

    private boolean gps;

    private boolean onStar;

    private boolean siriusXm;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private  String aaMemberId;

    private double totalCost;

    public String getOwningUsername() {
        return OwningUsername;
    }

    public void setOwningUsername(String owningUsername) {
        OwningUsername = owningUsername;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(long carNumber) {
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

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isOnStar() {
        return onStar;
    }

    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    public boolean isSiriusXm() {
        return siriusXm;
    }

    public void setSiriusXm(boolean siriusXm) {
        this.siriusXm = siriusXm;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getAaMemberId() {
        return aaMemberId;
    }

    public void setAaMemberId(String aaMemberId) {
        this.aaMemberId = aaMemberId;
    }

    public double calculateTotalCost() {
        return 0.0;
    }
}
