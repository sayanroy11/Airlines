package com.example.carder;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

public class LoginActivity extends AppCompatActivity {
    static Map<String,User> users =new TreeMap<>();
    EditText uname,pass;
    User currentUser;
    String c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button logButton = findViewById(R.id.logIn);
        Button signButton = findViewById(R.id.signUp);
        TextView later = findViewById(R.id.sLater);
        uname = findViewById(R.id.takeUname);
        pass = findViewById(R.id.takePass);
        later.setLinksClickable(true);
        Intent p = getIntent();
        currentUser = new RegUser();
//        currentUser = (User) p.getParcelableExtra("user");
        later.setEnabled(false);
        Intent i = getIntent();
        c = i.getStringExtra("c");
//        Toast.makeText(getApplicationContext(),c+" is it "
//                +currentUser.adminFlag,Toast.LENGTH_LONG).show();
        later.setVisibility(View.GONE);
        if(c==null) {
            later.setVisibility(View.VISIBLE);
            later.setLinksClickable(true);
            later.setEnabled(true);
        }
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(users.containsKey(uname.getText().toString()) &&
                        users.get(uname.getText().toString()).password.compareTo
                                (pass.getText().toString())==0){
                        currentUser = users.get(uname.getText().toString());
                        Intent closeLogin = new Intent(getApplicationContext(), HomeActivity.class);
//                    closeLogin.putExtra("finaluser", (Parcelable)currentUser);
                        closeLogin.putExtra("user", (Parcelable)currentUser);
                        startActivity(closeLogin);
                }
                else if(uname.getText().toString().compareTo("root")==0 &&
                        pass.getText().toString().compareTo("pass123")==0    ){
                    currentUser =new AdminUser();
                    Intent closeLogin = new Intent(getApplicationContext(), HomeActivity.class);
                    closeLogin.putExtra("user", (Parcelable)currentUser);
                    startActivity(closeLogin);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Username or Password " +
                            "unmatched",Toast.LENGTH_LONG).show();
                }
            }
        });

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(users.containsKey(uname.getText().toString())){
                    Toast.makeText(getApplicationContext(),"" +
                            "Username already exists",Toast.LENGTH_LONG).show();
                }
                else {
                    if (uname.getText().toString().compareTo("root") == 0) {
                        Toast.makeText(getApplicationContext(), "Admin" +
                                " already present!", Toast.LENGTH_LONG).show();
                    } else {
                        currentUser = new RegUser();
                        currentUser.username = uname.getText().toString();
                        currentUser.password = pass.getText().toString();
                        users.put(uname.getText().toString(), currentUser);
                        uname.getText().clear();
                        pass.getText().clear();
//                    if(c.compareTo("book")==0){
//                        finish();
//                    }
//                    Intent closeLogin = new Intent(getApplicationContext(), HomeActivity.class);
//                    closeLogin.putExtra("user", (Parcelable)currentUser);
//                    startActivity(closeLogin);
                    }
                }
            }
        });
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closeLogin = new Intent(getApplicationContext(), HomeActivity.class);
                closeLogin.putExtra("user", currentUser);
                startActivity(closeLogin);
            }
        });

    }
}
