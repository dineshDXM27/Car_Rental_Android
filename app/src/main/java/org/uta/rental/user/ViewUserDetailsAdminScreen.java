package org.uta.rental.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;
import org.uta.rental.SearchUsers_AdminScreen;
import org.uta.rental.ViewProfileAdmin;

public class ViewUserDetailsAdminScreen extends AppCompatActivity {

    private AdminSearchUserDetailsController searchUsersController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_details_admin_screen);
        searchUsersController = new AdminSearchUserDetailsController(ViewUserDetailsAdminScreen.this);
        ViewProfileAdmin userDetails = searchUsersController.userDetails();

        TextView textView = (TextView) findViewById(R.id.userList);
        textView.setText(userDetailsToString(userDetails));
        Button logoutBtn = (Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewUserDetailsAdminScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.vrUserBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewUserDetailsAdminScreen.this,
                        SearchUsers_AdminScreen.class));
            }
        });
    }


    private String userDetailsToString(ViewProfileAdmin user){
        String userName = user.getUserName();
        String password = user.getPassword();
        String utaID = user.getUtaID();
        String lastName = user.getLastName();
        String firstName = user.getFirstName();
        String phone = user.getPhone();
        String role = user.getRole();
        String email = user.getEmail();
        String address = user.getStreetAddress();
        String city = user.getCity();
        String state = user.getState();
        String zip = user.getZipCode();
        String rentalPrivilege = user.getRentalprivilegeStatus();

        String guiString = "UserName: %d\nPassword: %d\nUTA ID: %s\n" +
                           "LastName: %d\nFirstName: %s\nPhone: %s\nRole: %s\n" +
                           "Email: %s\nAddress: %s\nCity: %s\nState: %s\n" +
                           "Zip: %s\nRental Privilege Status: %s\n";

        return String.format(guiString, userName, password, utaID, lastName, firstName, phone, role, email, address, city, state, zip, rentalPrivilege);
    }
}