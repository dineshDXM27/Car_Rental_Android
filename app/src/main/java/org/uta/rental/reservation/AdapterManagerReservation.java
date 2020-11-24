package org.uta.rental.reservation;

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

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdapterManagerReservation extends RecyclerView.Adapter<AdapterManagerReservation.ViewHolder> {
    private List<Reservation> reservations;

    private Context context;

    private RecyclerView rv;

    private ViewReservationsManagerController controller;

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

    public AdapterManagerReservation(RecyclerView rv, List<Reservation> reservations, ViewReservationsManagerController controller,
                                     Context context) {
        this.rv = rv;
        this.reservations = reservations;
        this.controller = controller;
        this.context = context;
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
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(reservationToString(reservations.get(position)));
        viewHolder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    ViewReservationDetailsManagerController.setReservation(reservations.get(position));
                    Intent intent = new Intent(context, ViewReservationDetailsManagerScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return reservations.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String reservationToString(Reservation reservation) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String startTime = timeFormatter.format(reservation.getStartDateTime());
        String startDate = dateFormatter.format(reservation.getStartDateTime());
        String endTime = timeFormatter.format(reservation.getEndDateTime());
        String endDate = dateFormatter.format(reservation.getEndDateTime());

        String guiString = "Reservation Number: %d\nCar Number: %d\nCar Name: %s\n" +
                "Capacity: %d\nStart Date: %s\nStart Time: %s\nEnd Date: %s\nEnd Time: %s\n" +
                "Total Cost: %.2f\n";
        return String.format(guiString, reservation.getReservationNumber(), reservation.getCarNumber(),
                reservation.getCarName(), reservation.getCapacity(), startDate, startTime, endDate,
                endTime, reservation.calculateTotalCost());
    }
}