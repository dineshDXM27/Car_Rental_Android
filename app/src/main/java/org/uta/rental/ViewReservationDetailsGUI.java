package org.uta.rental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ViewReservationDetailsGUI extends AppCompatActivity {
    private Button logoutButton;
    private Text reservationNumberText;
    private Text carNumber;
    private Text carName;
    private Text carCapacity;
    private Text startDate;
    private Text startTime;
    private Text endDate;
    private Text endTime;
    private Text totalCost;
    private Text duration;
    private Text siriusXM;
    private Text onStar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details_g_u_i);

        logoutButton = findViewById(R.id.logout_viewResDetails);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewReservationDetailsGUI.this, ApplicationMainScreen.class));
            }
        });
    }
}