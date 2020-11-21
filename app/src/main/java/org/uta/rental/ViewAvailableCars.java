package org.uta.rental;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ViewAvailableCars extends AppCompatActivity {
    private Button searchButton;
    private Text dateText;
    private Text timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_available_cars);

        searchButton = findViewById(R.id.searchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search cars
            }
        });
    }
}