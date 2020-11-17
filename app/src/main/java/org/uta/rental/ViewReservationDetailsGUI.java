package org.uta.rental;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ViewReservationDetailsGUI extends AppCompatActivity {
    private Button deleteButton;
    private Text reservationNumerText;
    private Text carNumber;
    private Text carCapacity;
    private Text startDate;
    private Text startTime;
    private Text endDate;
    private Text endTime;
    private Text totalCost;
    private Text aacMemberStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details_g_u_i);

        deleteButton = findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        DeleteReservationDialog dialog = new DeleteReservationDialog();
        dialog.show(getSupportFragmentManager(), "delete reservation dialog");
    }
}