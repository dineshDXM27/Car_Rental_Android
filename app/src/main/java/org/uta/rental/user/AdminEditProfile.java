package org.uta.rental.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.AdminMainScreen;
import org.uta.rental.DBManager;
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
    private EditText aacMemberid;

    private AdminSearchUserDetailsController searchUsersController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);
        searchUsersController = new AdminSearchUserDetailsController(AdminEditProfile.this);
        ViewProfile userDetails = searchUsersController.userDetails();

        userName = (EditText) findViewById(R.id.userNameUpdateProfileET);
        password = (EditText) findViewById(R.id.passwordUpdateProfileET);
        utaID = (EditText) findViewById(R.id.utaidUpdateProfileET);
        lastName = (EditText) findViewById(R.id.lastNameUpdateProfileET);
        firstName = (EditText) findViewById(R.id.firstNameUpdateProfileET);
        phone=(EditText) findViewById(R.id.phoneUpdateProfileET);
        role = (EditText) findViewById(R.id.roleUpdateProfileET);
        email = (EditText) findViewById(R.id.emailUpdateProfileET);
        address = (EditText) findViewById(R.id.streetAddressUpdateProfileET);
        city = (EditText) findViewById(R.id.cityUpdateProfileET);
        zip = (EditText) findViewById(R.id.zipcodeUpdateProfileET);
        state = (EditText) findViewById(R.id.stateUpdateProfileET);
        aacMemberid = (EditText) findViewById(R.id.aacMemberIdUpdateProfileET);


        setFieldValues(userDetails);

        updateBtn = (Button) findViewById(R.id.updateProfileButton);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    UpdateProfileFunc();

                } catch (Exception e){
                    e.printStackTrace();
                }
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

        phone.setText(user.getPhone());
        role.setText(user.getRole());
        email.setText(user.getEmail());
        address.setText(user.getStreetAddress());
        city.setText(user.getCity());
        state.setText(user.getState());
        zip.setText(user.getZipCode());
        aacMemberid.setText(user.getAacMemberid());
    }

    public  void UpdateProfileFunc() {

        ViewProfile viewProfile = new ViewProfile();
        viewProfile.setUserName(userName.getText().toString());

        viewProfile.setPassword(password.getText().toString());
        viewProfile.setUtaID(utaID.getText().toString());
        viewProfile.setLastName(lastName.getText().toString());
        viewProfile.setFirstName(firstName.getText().toString());
        viewProfile.setPhone(phone.getText().toString());
        viewProfile.setRole(role.getText().toString());
        viewProfile.setEmail(email.getText().toString());
        viewProfile.setStreetAddress(address.getText().toString());
        viewProfile.setCity(city.getText().toString());
        viewProfile.setState(state.getText().toString());
        viewProfile.setZipCode(zip.getText().toString());
        viewProfile.setAacMemberid(aacMemberid.getText().toString());


        //System.err.println(passwordUpdateProfileETups.getText().toString());

        DBManager dbManager = DBManager.getInstance(getApplicationContext());
        dbManager.admin_update_profile(viewProfile);

        startActivity(new Intent(AdminEditProfile.this, AdminMainScreen.class));
        Toast.makeText(getApplicationContext(),"Profile Update Successful.",Toast.LENGTH_SHORT).show();
    }
}