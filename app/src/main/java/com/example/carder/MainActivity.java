package com.example.carder;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
//    User user = new AdminUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        permission();
//        user = new AdminUser();
        login();
    }

    private void permission() {
        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", getPackageName(), null)));
    }
    private void login() {
        Intent openLogin = new Intent(getApplicationContext(), LoginActivity.class);
//        openLogin.putExtra("user",(Parcelable)user);
        startActivity(openLogin);
    }





}
