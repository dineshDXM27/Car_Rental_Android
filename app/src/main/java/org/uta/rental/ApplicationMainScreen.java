package org.uta.rental;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.reservation.Reservation;

import java.time.LocalDateTime;
import java.util.Random;

public class ApplicationMainScreen extends AppCompatActivity {

    private EditText username, password;

    public static final String SUCCESSFUL_LOGIN_MSG = "You have successfully logged in";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DBManager.getInstance(this);
        setContentView(R.layout.activity_application_main_screen);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loginFunc(View view) {
        username = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);

        DBManager dbManager = DBManager.getInstance(this);

        String usrnametemp = username.getText().toString();
        String pswdtemp = password.getText().toString();
        LoginController lgc = new LoginController();
        String str = lgc.loginFunction(usrnametemp,pswdtemp,dbManager);

        if(str == "USER")
        {
            Intent intent = new Intent(this,UserHomeScreen.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG,Toast.LENGTH_SHORT).show();
        }else if (str == "ADMIN")
        {
            startActivity(new Intent(this, AdminMainScreen.class));
            Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG, Toast.LENGTH_SHORT).show();
        }else if(str == "RENTAL_MANAGER")
        {
            startActivity(new Intent(this, RentalManagerScreen.class));
            Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG, Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
        }
    }

    public void registerFunc(View view) {
        Toast.makeText(getApplicationContext(),"This is under process",Toast.LENGTH_LONG).show();
    }
}