package org.uta.rental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.uta.rental.user.AdminEditProfile;
import org.uta.rental.user.AdminSearchUserDetailsController;

public class AdminChangeRentalPriviledge extends Activity {

    private AdminSearchUserDetailsController searchUsersController;
    RadioButton radioButton_renter;
    RadioButton radioButton_non_renter;

    RadioButton CheckradioButton;
    RadioGroup radioGroup;
    Button update_button;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_rental_priviledge);
        searchUsersController = new AdminSearchUserDetailsController(AdminChangeRentalPriviledge.this);
        ViewProfile userDetails = searchUsersController.userDetails();

        username = userDetails.getUserName();

        radioGroup = findViewById(R.id.radioGroup);
        update_button = findViewById(R.id.button_apply);

        radioButton_renter = findViewById(R.id.radio_renter);
        radioButton_non_renter = findViewById(R.id.radio_nonRenter);

        EditText userName = (EditText) findViewById(R.id.userNameET);
        userName.setText(username);

        String status = userDetails.getRentalprivilegeStatus();

        if(status.equals("renter"))
            radioButton_renter.setChecked(true);
        else if(status.equals("non_renter"))
            radioButton_non_renter.setChecked(true);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewProfile viewProfile = new ViewProfile();

                int radioId = radioGroup.getCheckedRadioButtonId();
                CheckradioButton = findViewById(radioId);
                String newStatus = CheckradioButton.getText().toString();
                System.out.println("So the role clicked is "+newStatus);

                viewProfile.setUserName(username);
                viewProfile.setRentalprivilegeStatus(newStatus);

                DBManager dbManager = DBManager.getInstance(getApplicationContext());
                dbManager.admin_change_rental_priviledge(viewProfile);

                startActivity(new Intent(AdminChangeRentalPriviledge.this, AdminMainScreen.class));
                Toast.makeText(getApplicationContext(),"Rental Priviledge Updated!",Toast.LENGTH_SHORT).show();
            }
        });


    }

}

