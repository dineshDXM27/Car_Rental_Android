package org.uta.rental.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.R;
import org.uta.rental.ViewProfile;


public class AdminEditProfile extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private EditText utaID;
    private EditText lastName;
    private EditText firstName;
    private EditText phone;
    private EditText role;
    private EditText email;
    private EditText address;
    private EditText city;
    private EditText state;
    private EditText zip;
    private EditText rentalStatus;
    private Button updateBtn;

    private AdminSearchUserDetailsController searchUsersController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);
        searchUsersController = new AdminSearchUserDetailsController(AdminEditProfile.this);
        ViewProfile userDetails = searchUsersController.userDetails();

        userName = (EditText) findViewById(R.id.usrNameAdmin);
        password = (EditText) findViewById(R.id.pwdAdmin);
        utaID = (EditText) findViewById(R.id.utaIDAdmin);
        lastName = (EditText) findViewById(R.id.firstNameAdmin);
        firstName = (EditText) findViewById(R.id.phoneAdmin);
        role = (EditText) findViewById(R.id.roleAdmin);
        email = (EditText) findViewById(R.id.emailAdmin);
        address = (EditText) findViewById(R.id.addressAdmin);
        city = (EditText) findViewById(R.id.cityAdmin);
        state = (EditText) findViewById(R.id.stateAdmin);
        zip = (EditText) findViewById(R.id.zipAdmin);
        rentalStatus = (EditText) findViewById(R.id.rentalStatusAdmin);

        setFieldValues(userDetails);

        updateBtn = (Button) findViewById(R.id.updateAdminBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call update function here
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.vrUserBackButton4);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminEditProfile.this,
                        ViewUserDetailsAdminScreen.class));
            }
        });
    }


    //set user details to corresponding text fields
    private void setFieldValues(ViewProfile user){
        userName.setText(user.getUserName());
        password.setText(user.getPassword());
        utaID.setText(user.getUtaID());
        lastName.setText(user.getLastName());
        firstName.setText(user.getFirstName());
        role.setText(user.getRole());
        email.setText(user.getEmail());
        address.setText(user.getStreetAddress());
        city.setText(user.getCity());
        state.setText(user.getState());
        zip.setText(user.getZipCode());
        rentalStatus.setText(user.getRentalprivilegeStatus());
    }
}