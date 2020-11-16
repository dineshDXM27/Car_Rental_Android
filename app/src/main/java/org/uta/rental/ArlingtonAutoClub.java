package org.uta.rental;
import java.time.LocalDateTime;

public class ArlingtonAutoClub {
    LocalDateTime OpenTime;
    LocalDateTime CloseTime;
    int dayOfWeek;

    public LocalDateTime getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        OpenTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        CloseTime = closeTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
