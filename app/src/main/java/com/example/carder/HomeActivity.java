package com.example.carder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    TextView apanel;
    EditText edittext;
    TextView helloUser;
    Button b,pBook;
    Spinner from,to;
    User currentUser;
    Graph g=new Graph();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        helloUser = findViewById(R.id.flightName);
        apanel = findViewById(R.id.link);
        b = findViewById(R.id.Search);
        edittext = findViewById(R.id.date);
        from=findViewById(R.id.spinnerto);
        to=findViewById(R.id.spinnerfrom);
        pBook = findViewById(R.id.PBook);
        apanel.setEnabled(false);
        apanel.setVisibility(View.GONE);
        Intent i = getIntent();
        currentUser = (User) i.getParcelableExtra("user");
//        Toast.makeText(getApplicationContext(),currentUser.getPassword()+" kkk",Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), message + " fdcujh ", Toast.LENGTH_LONG).show();
        if (currentUser.getUsername().compareTo("")!=0)
            helloUser.setText(helloUser.getText()+" "+currentUser.getUsername()+"!");
        else {
            helloUser.setText(helloUser.getText() + " user!");
            pBook.setText("LOGIN");
        }
        if (currentUser.adminFlag) {
            apanel.setVisibility(View.VISIBLE);
            apanel.setLinksClickable(true);
            apanel.setEnabled(true);
//            Toast.makeText(getApplicationContext(), "True Admin", Toast.LENGTH_LONG).show();
        }

        g = new Graph();
        g.addAirport(new Airport("DEL","ID1"));
        g.addAirport(new Airport("BOM","ID2"));
        g.addAirport(new Airport("GOI","ID3"));
        g.addAirport(new Airport("MAA","ID4"));
        g.addAirport(new Airport("CCU","ID5"));
        g.addAirport(new Airport("COK","ID6"));
        g.addAirport(new Airport("AMD","ID7"));
        g.addAirport(new Airport("CSK","ID8"));
        g.addAirport(new Airport("BLR","ID9"));
        g.addAirport(new Airport("CNN","ID0"));

        g.addFlight(new Flight(g.searchAirport("DEL"),g.searchAirport("BOM"),"FLIGHT1","ID1",2500.00,"20191201",2,"1500"));
        g.addFlight(new Flight(g.searchAirport("DEL"),g.searchAirport("BOM"),"FLIGHT2","IF2",2000.00,"20191130",2,"2000"));

        g.addFlight(new Flight(g.searchAirport("DEL"),g.searchAirport("GOI"),"FLIGHT3","IF3",2750.00,"20191201",3,"1600"));
        g.addFlight(new Flight(g.searchAirport("GOI"),g.searchAirport("DEL"),"FLIGHT4","IF4",2750.00,"201912022",3,"1600"));

        g.addFlight(new Flight(g.searchAirport("AMD"),g.searchAirport("COK"),"FLIGHT5","IF5",2000.00,"20191201",3.5,"1100"));
        g.addFlight(new Flight(g.searchAirport("COK"),g.searchAirport("GOI"),"FLIGHT6","IF6",1500.00,"20191202",1.5,"1900"));
        g.addFlight(new Flight(g.searchAirport("GOI"),g.searchAirport("AMD"),"FLIGHT7","IF7",1500.00,"20191201",2.5,"2200"));

        g.addFlight(new Flight(g.searchAirport("CSK"),g.searchAirport("CCU"),"FLIGHT8","IF8",3500.00,"20191203",3.5,"1400"));
        g.addFlight(new Flight(g.searchAirport("CCU"),g.searchAirport("MAA"),"FLIGHT9","IF9",3100.00,"20191204",4,"0900"));
        g.addFlight(new Flight(g.searchAirport("MAA"),g.searchAirport("BLR"),"FLIGHT10","IF10",1500.00,"20191201",2,"0500"));
        g.addFlight(new Flight(g.searchAirport("BLR"),g.searchAirport("CSK"),"FLIGHT11","IF11",1000.00,"20191203",1.5,"0300"));
        g.addFlight(new Flight(g.searchAirport("CSK"),g.searchAirport("MAA"),"FLIGHT12","IF12",1400.00,"20191205",1,"0100"));

        ArrayList<Airport> airports = g.airportlist();
        ArrayList<Flight> flights = g.Flightlist();
        String[] spinnerBatchlist = new String[airports.size()];
        for (int q = 0; q < airports.size(); q++) {
            spinnerBatchlist[q] = airports.get(q).getAirportName();
        }

        ArrayAdapter<String> spinnerBatchAdapter= new ArrayAdapter<>(HomeActivity.this, android.R.layout.simple_list_item_1,spinnerBatchlist);
        spinnerBatchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to.setAdapter(spinnerBatchAdapter);
        ArrayAdapter<String> spinnerBatchAdapter2= new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1,spinnerBatchlist);
        spinnerBatchAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(spinnerBatchAdapter);

        final Intent searchIntent = new Intent(getApplicationContext(), ListResults.class);
        apanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(getApplicationContext(), AdminPanel.class);
                open.putExtra("graph",(Parcelable)g);
                startActivity(open);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchIntent.putExtra("graph",(Parcelable)g);
                searchIntent.putExtra("date",edittext.getText().toString());
                searchIntent.putExtra("user",(Parcelable)currentUser);
//                Intent open = new Intent(getApplicationContext(), ListResults.class);
                startActivity(searchIntent);
//                startActivity(searchIntent);
            }
        });

        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
                searchIntent.putExtra("fromV",parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchIntent.putExtra("toV",parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent booked = new Intent(getApplicationContext(),LoginActivity.class);
//                booked.putExtra("graph",(Parcelable)g);
//                booked.putExtra("user",(Parcelable)currentUser);
                startActivity(booked);
            }
        });

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyyMMdd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }
}
