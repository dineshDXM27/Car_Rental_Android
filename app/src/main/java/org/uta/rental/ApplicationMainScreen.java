package org.uta.rental;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.register.RegistrationScreen;
import org.uta.rental.user.UserType;

public class ApplicationMainScreen extends AppCompatActivity {

    private EditText username, password;

    private String userNamestr = "itsTempValue";

    public String getUserNamestr() {
        return userNamestr;
    }

    public void setUserNamestr(String userNamestr) {
        this.userNamestr = userNamestr;
    }

    private Session session;//global variable


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

        //EditText tempUsrName = findViewById(R.id.editTextTextPersonName);
        String userNamestrtemp = username.getText().toString();
        setUserNamestr(userNamestrtemp);
        System.out.println("line no 32 - " + userNamestr);

        DBManager dbManager = DBManager.getInstance(this);
        LoginController lgc = new LoginController();
        UserType ut = lgc.loginFunction(username.getText().toString(),
                password.getText().toString(),dbManager);

        session = new Session(getApplicationContext()); //in oncreate
//and now we set sharedpreference then use this like

        session.setValue(StringConstants.USERNAME, userNamestrtemp );

        if(ut == UserType.USER)
        {
            Intent intent = new Intent(this,UserHomeScreen.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG,Toast.LENGTH_SHORT).show();
        }else if (ut == UserType.ADMIN)
        {
            startActivity(new Intent(this, AdminMainScreen.class));
            Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG, Toast.LENGTH_SHORT).show();
        }else if(ut == UserType.RENTAL_MANAGER)
        {
            startActivity(new Intent(this, RentalManagerScreen.class));
            Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG, Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(),"please recheck your user name and password as they do not match",Toast.LENGTH_SHORT).show();
        }



    }

    public void registerFunc(View view) {
        Intent intent = new Intent(this, RegistrationScreen.class);
        startActivity(intent);
    }


}