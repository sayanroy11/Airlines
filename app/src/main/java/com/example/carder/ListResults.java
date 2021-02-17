package com.example.carder;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListResults extends AppCompatActivity {
    TextView fromT,toT,dateT;
    User currentUser;
    Graph g;String from,to,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);

        fromT = findViewById(R.id.headtext);
        toT = findViewById(R.id.headtext2);
        dateT = findViewById(R.id.headtext3);
        Intent i = getIntent();
        g = (Graph) i.getParcelableExtra("graph");

        ArrayList<Airport>  airports = g.airportlist();
        ArrayList<Flight>  flights = g.Flightlist();
//        Toast.makeText(getApplicationContext()," "+flights.size()+" "+" ",Toast.LENGTH_SHORT).show();
        for(int q=0;q<flights.size();q++){
//            Toast.makeText(getApplicationContext()," "+flights.get(q).getName(),Toast.LENGTH_SHORT).show();
        }

        currentUser = (User) i.getParcelableExtra("user");
        from = i.getStringExtra("fromV");
        to = i.getStringExtra("toV");
        date = i.getStringExtra("date");
        fromT.setText("|From: "+from+"|");
        toT.setText("|To: "+to+"|");
        dateT.setText("|Date: "+date+"|");
//        Toast.makeText(getApplicationContext(),currentUser.username+" "+from+" "+to+" "+date,Toast.LENGTH_SHORT).show();
//        view.setText(date);

//        for(int q=0;q<qflights.size();q++){
//            Toast.makeText(getApplicationContext()," "+qflights.size(),Toast.LENGTH_SHORT).show();
//        }

//        String[] flightArray = {"Kolkata - > Mumbai \n Departure 5:41 PM \t\t Arrival 7:02 PM " +
//                "Fare : Rs. 5443/-",
//                "Hyderabad - > Delhi \n Departure 7:51 AM \t\t Arrival 9:03 AM " +
//                        "Fare : Rs. 5443/-",
//                "Kolkata - > Delhi \n Departure 12:01 PM \t\t Arrival 2:00 PM " +
//                        "Fare : Rs. 5443/-"};
        ArrayList<Flight> qflights = g.showFlights(from,to);
        String[] flightArray = new String[qflights.size()] ;
        for(int q=0;q<qflights.size();q++){
            flightArray[q]=(qflights.get(q).getName()+"\nDeparture: " +
                    ""+qflights.get(q).getTime().substring(0,1)+":" +
                    qflights.get(q).getTime().substring(2)+"UTC\t\t\tDuration: "+
                    qflights.get(q).getDuration()+
                    " hr\nFare: Rs."+qflights.get(q).getCost()+"" +
                    "");
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.activity_list_view, flightArray);

        ListView listView = findViewById(R.id.flight_list);
        listView.setAdapter(adapter);
        listView.setPadding(40,20,40,50);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getApplicationContext(),Booking.class);
                in.putExtra("graph",(Parcelable)g);
                in.putExtra("user",(Parcelable)currentUser);
                in.putExtra("from",from);
                in.putExtra("to",to);
                in.putExtra("date",date);
                in.putExtra("flight",parent.getItemAtPosition(position).toString());
                startActivity(in);
            }
        });
    }
}
