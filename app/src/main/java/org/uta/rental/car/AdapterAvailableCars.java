package org.uta.rental.car;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.uta.rental.R;
import org.uta.rental.reservation.TotalCostUtility;

import java.util.List;



public class AdapterAvailableCars extends RecyclerView.Adapter<AdapterAvailableCars.ViewHolder> {
    private  List<CarsInformation> carsInformations;

    private RecyclerView rv;

    private ViewAvailableCarsControllerManager controller;

    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.reservation);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public AdapterAvailableCars(Context context, RecyclerView rv, java.util.List<CarsInformation> carsInformations, ViewAvailableCarsControllerManager controller) {
        this.context = context;
        this.rv = rv;
        this.carsInformations = carsInformations;
        this.controller = controller;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_reservation, viewGroup, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(AdapterAvailableCars.ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(carInformationToString(carsInformations.get(position)));
        viewHolder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    Intent intent = new Intent(context, ViewCarDetailsManagerScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CarsInformation carsInformation = carsInformations.get(position);
                    intent.putExtra("CarsInformation", carsInformation);
                    context.startActivity(intent);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return carsInformations.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String carInformationToString(CarsInformation carsInformation) {
        long carNumber = carsInformation.getCarNumber();
        String carName = carsInformation.getCarName();
        int capacity = carsInformation.getCapacity();
        TotalCostUtility.CarType carType = TotalCostUtility.CarType.valueOf(carName
                .replaceAll(" ", "_").toUpperCase());
        double weekendRate = TotalCostUtility.getWeekRate(carType);
        CarStatus carStatus = carsInformation.getCarStatus();

        return String.format("Car Number: %d\nCar Name: %s\nCapacity: %d\nWeekend Rate: $%.2f\n" +
                "Car Status: %s", carNumber, carName, capacity, weekendRate, carStatus.getStatus());
    }
}

