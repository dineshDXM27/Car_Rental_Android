package org.uta.rental.car;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.uta.rental.DBManager;
import org.uta.rental.reservation.TotalCostUtility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViewAvailableCarsControllerManager {

    private final Context context;

    public ViewAvailableCarsControllerManager(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<CarsInformation> search(LocalDateTime selectedTime) {
        DBManager dbManager = DBManager.getInstance(context);
        List<Car> cars = dbManager.findCarsByAvailabilityDateAndTime(selectedTime);
        List<CarsInformation> informationList = new ArrayList<>();
        for (Car car : cars) {
            CarsInformation carsInformation = new CarsInformation();
            carsInformation.setCarNumber((int) car.getCarNumber());
            carsInformation.setCarName(car.getCarName());
            carsInformation.setCapacity(car.getCapacity());
            carsInformation.setWeekRate(TotalCostUtility.getWeekRate(TotalCostUtility.CarType.valueOf(car.getCarName()
                    .replaceAll(" ", "_").toUpperCase())));
            carsInformation.setCarStatus(car.getCarStatus());

            informationList.add(carsInformation);
        }

        return informationList;
    }
}


