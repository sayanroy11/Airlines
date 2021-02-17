package com.example.carder;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
Button goBack,returnTicket;
ImageView imgV;
TextView t;
Graph g;
User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        goBack  = findViewById(R.id.button);
        goBack.setVisibility(View.GONE);
        returnTicket  = findViewById(R.id.button3);
        t=findViewById(R.id.textView);
        imgV=findViewById(R.id.imageView);
        Intent it =new Intent();
        g = (Graph) (it.getParcelableExtra("graph"));
        currentUser = (User) it.getParcelableExtra("user");

        Intent i = getIntent();
        String printit="";
        String str = i.getStringExtra("c");
        String seats = i.getStringExtra("seats");
        String names = i.getStringExtra("booked");
        if(str!=null && names!=null && seats!=null && str.equals("confirm")) {
            String[] name = names.split(":");
            String[] seat = seats.split(" ");
            for (int l = 0; l < name.length; l++) {
                printit += (name[l] + " : " + seat[l] + "\n");
            }
            t.setText("BOOKING CONFIRMED!\n" +
                    "THANKS FOR DOING BUSINESS WITH US.\n" +
                    "Find your seat numbers below and expect an email and message" +
                    "from us for the ticket.\n "+printit);

            imgV.setImageResource(R.drawable.tick2);
//            Toast.makeText(getApplicationContext(),"hola!",Toast.LENGTH_LONG).show();
        }
        else{
            imgV.setImageResource(R.drawable.cross2);
            t.setText("BOOKING FAILED!\nENTERED NUMBER OF SEATS ARE NOT AVAILABLE!");
        }


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),LoginActivity.class);
                in.putExtra("graph",(Parcelable)g);
                in.putExtra("user",(Parcelable)currentUser);
                startActivity(in);
//                finish();
            }
        });

        returnTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),LoginActivity.class);
                in.putExtra("graph",(Parcelable)g);
                in.putExtra("user",(Parcelable)currentUser);
                in.putExtra("end","end");
                startActivity(in);
//                finish();
            }
        });
    }
}
