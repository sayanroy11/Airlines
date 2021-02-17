package com.example.carder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyBookings extends AppCompatActivity {
ArrayAdapter<String> arrayAdapter;
ListView listView;
Graph g;
User currentUser;
Button signout,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        back  = findViewById(R.id.button6);
        signout  = findViewById(R.id.button5);
        Intent i = getIntent();
        currentUser = (User) i.getParcelableExtra("user");
        if(currentUser.getUsername().equals("")){
            TextView e = findViewById(R.id.headtext);
            e.setText("Please login to see your booked tickets!");
        }
        else {
//            listView = findViewById(R.id.flight_list);
//            ArrayList<String> passengers = new ArrayList<>();
//            arrayAdapter = new ArrayAdapter<String>(
//                    this,
//                    android.R.layout.simple_list_item_1,
//                    passengers);
//            listView.setPadding(40, 20, 40, 50);
//            String s[] = currentUser.getHistory().split(":");
//            for (int k = 0; k < s.length; k++) {
//                passengers.add(s[k]);
//                listView.setAdapter(arrayAdapter);
//            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(in);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }
}
