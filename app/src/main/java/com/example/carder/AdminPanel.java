package com.example.carder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AdminPanel extends AppCompatActivity {
    Button addAirport,modAirport,delAirport,addFlight,modFlight,delFlight,back;
    Graph g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        back = findViewById(R.id.back);
        addAirport = findViewById(R.id.Add_Airport);
        modAirport  = findViewById(R.id.Modify_Airport);
        delAirport  = findViewById(R.id.Delete_Airport);
        addFlight  = findViewById(R.id.Add_Flight);
        modFlight  = findViewById(R.id.Modify_Flight);
        delFlight  = findViewById(R.id.Delete_Flight);
        Intent i = getIntent();
        g = (Graph) i.getParcelableExtra("graph");
        addAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.add_airport);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent io =new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(io);
                    }
                });
                Button save = findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText airName=findViewById(R.id.flightName),airID=findViewById(R.id.flight_id);
                        if(airName.getText().toString().compareTo("")!=0 ||
                                airID.getText().toString().compareTo("")!=0){
                            g.addAirport(new Airport(airName.getText().toString(),
                                    airID.getText().toString()));
                        }
                        airID.getText().clear();airName.getText().clear();
                    }
                });
            }
        });

        modAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.mod_airport);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent io =new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(io);
                    }
                });
                Button save = findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText airName=findViewById(R.id.flightName),airID=findViewById(R.id.flight_id);
                        if(airName.getText().toString().compareTo("")!=0 ||
                                airID.getText().toString().compareTo("")!=0){
                            g.modifyAirportName(airName.getText().toString(),
                                    airID.getText().toString());
                        }
                        airID.getText().clear();airName.getText().clear();
                    }
                });
            }
        });

        delAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.del_airport);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent io =new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(io);
                    }
                });
                Button save = findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText airID = findViewById(R.id.flight_id);
                        if(airID.getText().toString().compareTo("")!=0){
                            g.removeAirport(airID.getText().toString());
                        }
                        airID.getText().clear();
                    }
                });
            }
        });
        addFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.add_flight);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent io =new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(io);
                    }
                });
                Button save = findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText e1=findViewById(R.id.flight_id);String fid = e1.getText().toString();
                        EditText e2=findViewById(R.id.fromAirport);String fap = e2.getText().toString();
                        EditText e3=findViewById(R.id.toAirport);String tap = e3.getText().toString();
                        EditText e4=findViewById(R.id.flightName);String fname = e4.getText().toString();
                        EditText e5=findViewById(R.id.cost);String cost = e5.getText().toString();
                        EditText e6=findViewById(R.id.date);String date = e6.getText().toString();
                        EditText e7=findViewById(R.id.flight_duration);String fdu = e7.getText().toString();
                        EditText e8=findViewById(R.id.departure);String dep = e8.getText().toString();

                        g.addFlight(new Flight(g.searchAirport(fap),g.searchAirport(tap)
                                ,fname,fid,Double.parseDouble(cost)
                                ,date,Double.parseDouble(fdu),dep));
                        e1.getText().clear();
                        e2.getText().clear();
                        e3.getText().clear();
                        e4.getText().clear();
                        e4.getText().clear();
                        e5.getText().clear();
                        e6.getText().clear();
                        e7.getText().clear();
                        e8.getText().clear();
                    }
                });

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(it);
            }
        });

        modFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.mod_flight);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent io =new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(io);
                    }
                });
                Button save = findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText e1=findViewById(R.id.flight_id);String fid = e1.getText().toString();
                        EditText e2=findViewById(R.id.fromAirport);String fap = e2.getText().toString();
                        EditText e3=findViewById(R.id.toAirport);String tap = e3.getText().toString();
                        EditText e4=findViewById(R.id.flightName);String fname = e4.getText().toString();
                        EditText e5=findViewById(R.id.cost);String cost = e5.getText().toString();
                        EditText e6=findViewById(R.id.date);String date = e6.getText().toString();
                        EditText e7=findViewById(R.id.duration);String fdu = e7.getText().toString();
                        EditText e8=findViewById(R.id.departure_time);String dep = e8.getText().toString();

                        g.modifyFlight(fid,g.searchAirport(fap),g.searchAirport(tap)
                                ,fname,Double.parseDouble(cost)
                                ,date,Double.parseDouble(fdu),dep);
                        e1.getText().clear();
                        e2.getText().clear();
                        e3.getText().clear();
                        e4.getText().clear();
                        e5.getText().clear();
                        e6.getText().clear();
                        e7.getText().clear();
                        e8.getText().clear();
                    }
                });
            }
        });

        delFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.del_flight);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent io =new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(io);
                    }
                });
                Button save = findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText fid =findViewById(R.id.flight_id);
                        g.removeFlight(fid.getText().toString());
                        fid.getText().clear();
                    }
                });
            }
        });



    }
}
