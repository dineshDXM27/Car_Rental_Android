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

import org.uta.rental.profile.ViewProfile;
import org.uta.rental.user.AdminEditProfile;
import org.uta.rental.user.AdminSearchUserDetailsController;

public class AdminChangeUserRole extends Activity {

    private AdminSearchUserDetailsController searchUsersController;
    RadioButton radioButton_user;
    RadioButton radioButton_manager;
    RadioButton radioButton_admin;

    RadioButton CheckradioButton;
    RadioGroup radioGroup;
    Button update_button;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_user_role);
        searchUsersController = new AdminSearchUserDetailsController(AdminChangeUserRole.this);
        ViewProfile userDetails = searchUsersController.userDetails();

        username = userDetails.getUserName();

        radioGroup = findViewById(R.id.radioGroup);
        update_button = findViewById(R.id.button_apply);
        radioButton_user = findViewById(R.id.radio_user);
        radioButton_manager = findViewById(R.id.radio_manager);
        radioButton_admin = findViewById(R.id.radio_admin);

        EditText userName = (EditText) findViewById(R.id.userNameET);
        userName.setText(username);

        String role = userDetails.getRole();

        if(role.equals("user"))
            radioButton_user.setChecked(true);
        else if(role.equals("admin"))
            radioButton_admin.setChecked(true);
        else
            radioButton_manager.setChecked(true);



        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewProfile viewProfile = new ViewProfile();

                int radioId = radioGroup.getCheckedRadioButtonId();
                CheckradioButton = findViewById(radioId);
                String newRole = CheckradioButton.getText().toString();
                System.out.println("So the role clicked is "+newRole);

                viewProfile.setUserName(username);
                viewProfile.setRole(newRole);

                DBManager dbManager = DBManager.getInstance(getApplicationContext());
                dbManager.admin_change_user_role(viewProfile);

                startActivity(new Intent(AdminChangeUserRole.this, AdminMainScreen.class));
                Toast.makeText(getApplicationContext(),"User Role Update!",Toast.LENGTH_SHORT).show();
            }
        });


    }

}

