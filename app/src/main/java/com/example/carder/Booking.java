package com.example.carder;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Booking extends AppCompatActivity {
Button submit,addP,back;
EditText name,age,email,phone;
TextView path,date,time,fare;
ListView listView;
Graph g;
User currentUser;
String names="";
ArrayAdapter<String> arrayAdapter;
int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        submit = findViewById(R.id.book);
        addP = findViewById(R.id.button4);
        back = findViewById(R.id.button2);
        name = findViewById(R.id.name);
        email = findViewById(R.id.editText2);
        age = findViewById(R.id.editText4);
//        ISD = findViewById(R.id.editText5);
        phone = findViewById(R.id.editText3);
        path = findViewById(R.id.textView9);
        date = findViewById(R.id.textView8);
        time = findViewById(R.id.textView12);
        fare = findViewById(R.id.textView11);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
//                startActivity(i);
                finish();
            }
        });
        listView = findViewById(R.id.list_customers);
        final ArrayList<String> passengers=new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                passengers );
        listView.setPadding(40,20,40,50);
//        listView.setAdapter(adapter);

        addP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().equals("") &&
                    !age.getText().toString().equals("") &&
                    !email.getText().toString().equals("")&&
//                    !ISD.getText().toString().equals("")&&
                    !phone.getText().toString().equals("")
                    )
                {
                    count++;
                    String str = "";
                    str += ("Name : " + name.getText().toString());
                    str += ("\t\t\t\tAge : " + age.getText().toString());
                    str += ("\nEmail : " + email.getText().toString());
                    str += ("\nPhone Number : ");
                    str += (phone.getText().toString());

//                flightArray[count-1]=str;
                    passengers.add(str);
                    names+=(name.getText().toString());
                    names+=":";
                    listView.setAdapter(arrayAdapter);

                    name.getText().clear();
                    age.getText().clear();
                    email.getText().clear();
                    phone.getText().clear();
//                    ISD.getText().clear();
                }
            }
        });

        Intent i = getIntent();
        String info[] =i.getStringExtra("flight").split("\n");
        fare.setText(info[2]);
        path.setText("\t\t\t\t"+i.getStringExtra("from")+ " -> " +i.getStringExtra("to"));
        date.setText(i.getStringExtra("date"));
        time.setText(info[1]);
        g = (Graph) (i.getParcelableExtra("graph"));
        currentUser = (User) i.getParcelableExtra("user");
//        Toast.makeText(getApplicationContext(),currentUser.username,Toast.LENGTH_LONG).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser.getUsername().compareTo("")==0){
                    String stri ="book";
                    Intent log = new Intent(getApplicationContext(),LoginActivity.class);
                    log.putExtra("c",stri);
                    startActivity(log);
//                    Intent i2 = new Intent();
//                    currentUser = (User) i2.getParcelableExtra("finaluser");
                    Toast.makeText(getApplicationContext(),"Please login to continue",Toast.LENGTH_LONG).show();
                    }
                else{
                    Intent t = getIntent();
                    String got =t.getStringExtra("flight");
                    String[] recV = got.split("\n");
                    Flight f = g.searchFlightbyName(recV[0]);
                    String seats = Book(f,count);
                    String[] bookings = seats.split(" ");
                    String str="";
                    if(bookings.length !=0 ) {
                        str = "confirm";
//                        Toast.makeText(getApplicationContext(),count+" "+bookings[0],Toast.LENGTH_LONG).show();
                    }
                    Intent log = new Intent(getApplicationContext(), PaymentActivity.class);
                    log.putExtra("c", str);
                    log.putExtra("booked",names);
                    log.putExtra("seats",seats);
                    currentUser.addHistory(got+':');
                    startActivity(log);
                }
            }
        });
    }

    public String Book(Flight f, int noPass){
        if(f.getCapacity()==f.totalPassenger()
                || f.getCapacity()-f.totalPassenger()<noPass){
            return "";
        }
        String res = "";
        for(int k=0;k<noPass;k++){
            for(int j=0;j<6;j++){
                int fl =0;
                for(int i=0;i<30;i++){

                    System.out.println(""+f.getSeatMatrix()[i][j]);
                    if(f.getSeatMatrix()[i][j]==0){
                        f.getSeatMatrix()[i][j]=1;
                        String row = Integer.toString(i);
                        String col;
                        switch (j) {
                            case 0:
                                col="A";
                                break;
                            case 1:
                                col="B";
                                break;
                            case 2:
                                col="C";
                                break;
                            case 3:
                                col="D";
                                break;
                            case 4:
                                col="E";
                                break;
                            default:
                                col = "F";
                                break;
                        }
                        f.setTotal(f.totalPassenger()+1);
                        res += (row+col);
                        fl =1;
                        break;
                    }

                }
                if(fl==1)   break;
            }
            res += " ";
        }
        return res;
    }

}
