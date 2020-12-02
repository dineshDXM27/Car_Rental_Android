package org.uta.rental.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;
import org.uta.rental.RentalManagerScreen;
import org.uta.rental.car.SearchCarsManagerScreen;
import org.uta.rental.user.RegisterUser;
import org.uta.rental.user.UserType;

public class RegistrationScreen extends AppCompatActivity {

    private RegisterController registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        ImageButton backBtn = (ImageButton) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        Button regButton = (Button) findViewById(R.id.regButton);
        registerController = new RegisterController(RegistrationScreen.this);
        regButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = ((EditText) findViewById(R.id.editTextTextPersonName2)).getText()
                        .toString();
                String password = ((EditText) findViewById(R.id.editTextTextPassword2)).getText()
                        .toString();
                String utaId = ((EditText) findViewById(R.id.editTextNumber3)).getText()
                        .toString();
                String lastName = ((EditText) findViewById(R.id.editTextTextPersonName4)).getText()
                        .toString();
                String firstName = ((EditText) findViewById(R.id.editTextTextPersonName5)).getText()
                        .toString();
                String aacMemberId = ((EditText) findViewById(R.id.editTextNumber)).getText()
                        .toString();
                String phone = ((EditText) findViewById(R.id.editTextPhone)).getText()
                        .toString();
                String role = ((EditText) findViewById(R.id.editTextTextPersonName6)).getText()
                        .toString();
                String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText()
                        .toString();
                String address = ((EditText) findViewById(R.id.editTextTextPersonName7)).getText()
                        .toString();
                String city = ((EditText) findViewById(R.id.editTextTextPersonName8)).getText()
                        .toString();
                String state = ((EditText) findViewById(R.id.editTextTextPersonName9)).getText()
                        .toString();
                String zip = ((EditText) findViewById(R.id.editTextNumber2)).getText()
                        .toString();

                UserType userType = UserType
                        .valueOf(role.replaceAll(" ", "_").toUpperCase());
                RegisterUser registerUser = new RegisterUser();
                registerUser.setUserName(userName);
                registerUser.setPassword(password);
                registerUser.setUtaId(utaId);
                registerUser.setLastName(lastName);
                registerUser.setFirstName(firstName);
                if (aacMemberId == null) {
                    aacMemberId = "";
                }
                registerUser.setAacMemberId(aacMemberId);
                registerUser.setPhoneNumber(phone);
                registerUser.setRole(userType);
                registerUser.setPhoneNumber(phone);
                registerUser.setEmail(email);
                registerUser.setStreetAddress(address);
                registerUser.setCity(city);
                registerUser.setZipCode(zip);
                registerUser.setState(state);
                registerUser.setRentalPrivilegeStatus("1");

                registerController.register(registerUser);
            }
        }));
    }
}